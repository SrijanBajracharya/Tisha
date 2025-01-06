package com.gemsansar.tisha.integration;

import com.gemsansar.tisha.TishaApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;

import java.util.List;
import java.util.function.Supplier;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {TishaApplication.class}
)
@Testcontainers
@ActiveProfiles("test")
public class IntegrationBaseTest implements BeforeAllCallback, AfterAllCallback {

    private static final String DB_USER_NAME = "root";
    public static MariaDBContainer<?> mariaDBContainer = new MariaDBContainer<>("mariadb:10.4");

    private static final Supplier<String> DB_URL = () ->
            "jdbc:mariadb://" +
                    mariaDBContainer.getHost() +
                    ":" +
                    mariaDBContainer.getMappedPort(3306) +
                    "/%s?createDatabaseIfNotExist=true&connectionCollation=utf8mb4_unicode_ci&serverTimezone=UTC";

    private static final Supplier<String> DB_PASSWORD = () -> mariaDBContainer.getPassword();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @DynamicPropertySource
    static void dataSourceProperties(DynamicPropertyRegistry registry){
        Startables.deepStart(mariaDBContainer).join();

        registry.add("spring.datasource.primary.jdbc-url", ()->DB_URL.get().formatted("primary"));
        registry.add("spring.datasource.primary.username", ()->DB_USER_NAME);
        registry.add("spring.datasource.primary.password", DB_PASSWORD::get);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {

    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        mariaDBContainer.start();
    }

    @BeforeEach
    void cleanUp(){
        cleanDB();
    }

    private void cleanDB(){
        jdbcTemplate.execute(IntegrationTestDBConstant.DISABLE_FOREIGN_KEY_CHECK);
        List<String> tableNames = jdbcTemplate.queryForList(IntegrationTestDBConstant.SHOW_TABLES, String.class);
        for(String tableName: tableNames){
            if(!IntegrationTestDBConstant.FLY_SCHEMA_HISTORY.equals(tableName)){
                jdbcTemplate.execute(IntegrationTestDBConstant.TRUNCATE_TABLE.formatted(tableName));
            }
        }
        jdbcTemplate.execute(IntegrationTestDBConstant.ENABLE_FOREIGN_KEY_CHECK);
    }
}

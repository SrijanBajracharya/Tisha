package com.gemsansar.tisha.platform.constants;

import com.gemsansar.tisha.platform.enums.AppRole;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PlatformConstant {

    public static final List<AppRole> EDIT_ROLE = List.of(AppRole.ADMIN, AppRole.SUPPLIER);
}

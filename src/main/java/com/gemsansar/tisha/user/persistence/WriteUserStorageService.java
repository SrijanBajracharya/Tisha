package com.gemsansar.tisha.user.persistence;

import com.gemsansar.tisha.user.domain.User;

public interface WriteUserStorageService{

    User create(User user);
}

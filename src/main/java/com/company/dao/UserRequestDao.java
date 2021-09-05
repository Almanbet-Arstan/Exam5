package com.company.dao;

import com.company.model.User;
import com.company.model.UserRequest;

import java.util.List;

public interface UserRequestDao {
    UserRequest setNewRequset(User user);
    List<UserRequest> getAllRequest();
    void deletAllRequest();
}

package com.company.dao;

import com.company.model.User;

import java.util.List;

public interface UserDao {
    User save(User user);
    List<User> getAllUsers();
    User delete(User user);
    User getById(Long id);
    User getByLogin(String login);
    List<User> getByDateBirth(int dateBirth);
    List<User> getByGender(Boolean gender);
}

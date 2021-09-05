package com.company.dao.Impl;

import com.company.config.DbConfig;
import com.company.dao.UserRequestDao;
import com.company.model.User;
import com.company.model.UserRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRequestDaoImpl extends DbConfig implements UserRequestDao {
    @Override
    public UserRequest setNewRequset(User user) {
        try (Connection connection=connect();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO user_request(request_data_time, id_user)" +
                     "values(now(), ?);");
             ResultSet set = statement.getGeneratedKeys()){
            statement.setLong(1, user.getId());
            statement.executeUpdate();
            if(set.next()){
                return new UserRequest(set.getLong("id"),
                        set.getTime("request_data_time"),
                        user);
            }

        }catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<UserRequest> getAllRequest() {

        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT req.id_request, req.request_data_time, u.id_user, u.login, u.dateberth, u.gender\n" +
                            "FROM public.user_request req\n" +
                            "join users u on u.id_user = req.id_user;");
            ResultSet set = statement.executeQuery()){
            List<UserRequest> allRequest = new ArrayList<>();
            while(set.next()){
                UserRequest request = new UserRequest();
                request.setId(set.getLong("id_request"));
                request.setTimeLogs(set.getTime("request_data_time"));
                request.setUserRequest(new User(set.getLong("id_user"),
                        set.getString("login"),
                        set.getInt("dateberth"),
                        set.getBoolean("gender")));
                allRequest.add(request);
            }

            return allRequest;

        }catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    @Override
    public void deletAllRequest() {

        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement("DELETE * FROM user_request;")){
            statement.executeUpdate();

        }catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }
}

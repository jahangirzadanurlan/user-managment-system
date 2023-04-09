package com.example.usermanagmentsystem.dao.impl;

import com.example.usermanagmentsystem.config.DatabaseConfig;
import com.example.usermanagmentsystem.dao.UserDao;
import com.example.usermanagmentsystem.model.User;
import com.example.usermanagmentsystem.query.UserQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl extends DatabaseConfig implements UserDao {

    @Override
    public boolean registerUser(User user) {
        try(Connection connection=connect()){
            PreparedStatement preparedStatement=connection.prepareStatement(UserQueries.REGISTER);

            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getCountry());

            int val=preparedStatement.executeUpdate();
            return val>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public User selectUser(Long id) {
        User user = null;
        try (Connection connection = connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.SHOW_USER_BY_ID);
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = User.builder()
                        .name(name)
                        .email(email)
                        .country(country)
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        try(Connection connection=connect()){
            PreparedStatement preparedStatement=connection.prepareStatement(UserQueries.UPDATE_USER);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getCountry());
            preparedStatement.setLong(4,user.getId());

            int val=preparedStatement.executeUpdate();
            return val>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(Long id) {
        try(Connection connection=connect()) {
            PreparedStatement preparedStatement=connection.prepareStatement(UserQueries.DELETE_USER);
            preparedStatement.setLong(1,id);

            int val=preparedStatement.executeUpdate();
            return val>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> showUser() {
        try(Connection connection=connect()) {
            PreparedStatement preparedStatement=connection.prepareStatement(UserQueries.SHOW_USER);
            List<User> students=new LinkedList<>();

            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Long id =resultSet.getLong("id");
                String name=resultSet.getString("name");
                String email=resultSet.getString("email");
                String country=resultSet.getString("country");


                User student=User.builder()
                        .id(id)
                        .name(name)
                        .email(email)
                        .country(country)
                        .build();

                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            e.getErrorCode();
        }
        return null;
    }
}

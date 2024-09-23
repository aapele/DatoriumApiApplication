package com.datorium.Datorium.API.Repo;

import com.datorium.Datorium.API.DTOs.User;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepo {

    private ArrayList<User> users = new ArrayList<>();//Mocked db

    public void add(User user){
        String url = "jdbc:sqlite:my.db";
        try (var conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                var statement = conn.createStatement();
                //statement.execute("CREATE TABLE people (id INTEGER PRIMARY KEY AUTOINCREMENT, name varchar)");
                statement.execute("INSERT INTO people (name) VALUES ('" + user.name + "')");
                //INSERT INTO people (name) VALUES ('');DROP TABLE people;--')
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public ArrayList<User> get(){
        String url = "jdbc:sqlite:my.db";
        var resultList = new ArrayList<User>();
        try (var conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                var statement = conn.createStatement();
                var result = statement.executeQuery("SELECT name,id FROM people");

                while(result.next()){
                    var user = new User();
                    user.id = result.getInt("id");
                    user.name = result.getString("name");
                    resultList.add(user);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultList;
    }

    public void update(User user){
        String url = "jdbc:sqlite:my.db";
        try (var conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                var statement = conn.createStatement();
                statement.execute("UPDATE people SET name = '" + user.name + "' WHERE id = " + user.id);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void delete(int id){
        String url = "jdbc:sqlite:my.db";
        try (var conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                var statement = conn.createStatement();
                statement.execute("DELETE FROM people WHERE id = " + id);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
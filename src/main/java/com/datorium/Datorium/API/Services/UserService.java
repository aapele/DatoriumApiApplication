package com.datorium.Datorium.API.Services;

import com.datorium.Datorium.API.DTOs.User;
import com.datorium.Datorium.API.Repo.UserRepo;
import org.apache.coyote.BadRequestException;

import java.util.ArrayList;

public class UserService {
    private UserRepo userRepo;

    public UserService(){
        userRepo = new UserRepo();
    }

    public void add(User user) throws BadRequestException {
        if(user.name == null ||user.name.isEmpty()){
            throw new BadRequestException("User name is empty");
        }
        userRepo.add(user);
    }

    public ArrayList<User> get(){
        return userRepo.get();
    }

    public User update(User user){
        userRepo.update(user);
        return user;
    }

    public void delete(int id){
        userRepo.delete(id);
    }
}
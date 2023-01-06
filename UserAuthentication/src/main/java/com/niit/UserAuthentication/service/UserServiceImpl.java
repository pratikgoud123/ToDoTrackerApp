/*
 *Author Name: Nikita Chauhan
 *Date: 02-01-2023
 *Created With: IntelliJ IDEA Community Edition
 */


package com.niit.UserAuthentication.service;

import com.niit.UserAuthentication.domain.User;
import com.niit.UserAuthentication.exception.UserAlreadyExistsException;
import com.niit.UserAuthentication.exception.UserNotFoundException;
//import com.niit.UserAuthentication.proxy.UserProxy;
import com.niit.UserAuthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{


    private UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public User addUser(User user) throws UserAlreadyExistsException {
        if(userRepository.findById(user.getUserId()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(user);
    }

    @Override
    public User loginCheck(String emailId, String password) throws UserNotFoundException {
        User user=userRepository.findByEmailIdAndPassword(emailId,password);
        if(user==null){
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUserById(int userId) {
        userRepository.deleteById(userId);
        return true;
    }
}



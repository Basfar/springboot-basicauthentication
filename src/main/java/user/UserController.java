/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mukhtar
 */
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    
    @PostMapping("/users/register")
    public Status registerUser(@Valid @RequestBody User newUser){
        List<User> users = userRepository.findAll();
        
        System.out.println("New user: " + newUser.toString());
        for(User user : users){
        System.out.println("Register user: " + newUser.toString());
        
        if(user.equals(newUser)){
            System.out.println("User already exist");
            return Status.USER_ALREADY_EXISTS;
        }
    }
        userRepository.save(newUser);
        return Status.SUCCESS;
  }
    
    @PostMapping("/users/login")
    public Status loginUser(@Valid @RequestBody User user){
        List<User> users = userRepository.findAll();
        
        for(User other : users){
            if(other.equals(user)){
                user.setLoggedIn(true);
                userRepository.save(user);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }
    
    @PostMapping("/users/logout")
    public Status logoutUser(@Valid @RequestBody User user){
        List<User> users = userRepository.findAll();
        
        for(User other : users){
            if(other.equals(user)){
                user.setLoggedIn(false);
                userRepository.save(user);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }
    
    @DeleteMapping("/users/all") 
    public Status deleteUsers(){
        userRepository.deleteAll();
        return Status.SUCCESS;
    }
}

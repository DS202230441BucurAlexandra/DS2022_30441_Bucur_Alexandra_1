package com.alexandra.assignment.service;

import com.alexandra.assignment.model.Device;
import com.alexandra.assignment.model.User;
import com.alexandra.assignment.repository.IDeviceRepository;
import com.alexandra.assignment.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    IUserRepository iUserRepository;
    @Autowired
    IDeviceRepository iDeviceRepository;

    @Autowired
    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public List<User> getAllUsers() {
        return (List<User>) iUserRepository.findAll();
    }

    public User getUser(Integer id) {
        Optional<User> user = iUserRepository.findById(id);
        return user.orElse(null);
    }

    public String deleteUser(Integer id) {
        try {
            User user = this.getUser(id);
            Set<Device> devices = user.getDevices();
            for(Device d: devices)
            {
                iDeviceRepository.delete(d);
            }
            iUserRepository.delete(this.getUser(id));
            return "Delete success.";
        }catch (Exception e){
            return "Delete failed.";
        }
    }


    public User saveAdmin(User user){
        user.setRole(1);
        return iUserRepository.save(user);
    }
    public User saveUser(User user){
        user.setRole(2);
        return iUserRepository.save(user);
    }

    public User updateUser(Integer id, User user){
        User initialUser= this.getUser(id);
        initialUser.setName(user.getName());
        initialUser.setUsername(user.getUsername());
        initialUser.setPassword(user.getPassword());
        Set<Device> devices = initialUser.getDevices();
        for(Device d: devices)
        {
          d.setUser(user);
        }
        return iUserRepository.save(initialUser);
    }
    public User login(User user)
    {
        List<User> users = (List<User>) iUserRepository.findAll();
        Optional<User> u = users.stream().filter(us -> us.getUsername().equals(user.getUsername()) && us.getPassword().equals(user.getPassword())).findFirst();
        return u.orElse(null);
    }

    public Set<Device> getDevices(Integer id) {
        Optional<User> user = iUserRepository.findById(id);
        if(user.isPresent())
        {
            User u = user.get();
            return u.getDevices();
        }
        return null;
    }

    public User getAdminUser() {
        List<User> users = getAllUsers();
        for(User u: users){
            if(u.getRole() == 1)
            {
                return u;
            }
        }
       return null;
    }
}


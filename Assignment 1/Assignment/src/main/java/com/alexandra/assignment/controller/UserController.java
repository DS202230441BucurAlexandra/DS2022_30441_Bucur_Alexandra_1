package com.alexandra.assignment.controller;

import com.alexandra.assignment.model.Device;
import com.alexandra.assignment.model.User;
import com.alexandra.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/users")
public class UserController {

    public UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    @ResponseBody
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUser")
    @ResponseBody
    public User getUser(@RequestParam(name = "id") Integer id) {
        return userService.getUser(id);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteUser")
    @ResponseBody
    public String deleteUser(@RequestParam(name = "id") Integer id) {
        return userService.deleteUser(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveUser")
    @ResponseBody
    public User saveUser(@RequestBody  User user) {
        return userService.saveUser(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateUser")
    @ResponseBody
    public User updateUser(@RequestParam(name = "id") Integer id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    @ResponseBody
    public User login(@RequestBody User user) {
        return userService.login(user);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/getDevices")
    @ResponseBody
    public Set<Device> getDevices(@RequestParam(name = "id") Integer id) {
        return userService.getDevices(id);
    }

}

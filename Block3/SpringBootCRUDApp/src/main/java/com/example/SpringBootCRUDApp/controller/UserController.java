package com.example.SpringBootCRUDApp.controller;


import com.example.SpringBootCRUDApp.model.User;
import com.example.SpringBootCRUDApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String allUsers(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/add")
    public String createUserForm(@ModelAttribute("user") User user) {
        return "add";
    }

    @PostMapping("/add")
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "add";
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/user-update")
    public String showFormForUpdate(@RequestParam("id") long id,
                                    Model model) {
        User user = userService.readUser(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(@RequestParam("id") long id,
                             @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "add";
        userService.update(user);
        return "redirect:/users";
    }

    @GetMapping("/user-delete")
    public String delete(@RequestParam("id") int id) {

        User user = userService.delete(id);
        return "redirect:/users";
    }
}

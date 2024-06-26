package com.dev.makov.rl_system.controller;

import com.dev.makov.rl_system.entity.Class;
import com.dev.makov.rl_system.entity.User;
import com.dev.makov.rl_system.service.ClassService;
import com.dev.makov.rl_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClassService classService;

    @GetMapping("/teacher/home")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User currentUser = userService.findByEmail(email);

        model.addAttribute("firstName", currentUser.getFirstName());
        model.addAttribute("lastName", currentUser.getLastName());
        return "teacher/home-teacher";
    }

    @GetMapping("/teacher/viewClass")
    public String viewClass(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User teacher = userService.findByEmail(email);
        Class theClass = classService.findClassByTeacherId(teacher.getId());
        model.addAttribute("class", theClass);
        return "teacher/viewClass";
    }
}

package com.datalink.controller;

import com.datalink.entity.User;
import com.datalink.entity.UserProfile;
import com.datalink.service.UserProfileService;
import com.datalink.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/welcome")
    public String welcome(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("currentUser");
        if (user != null) {

            return "welcome";
        } else {
            return "redirect:/user/login";
        }
    }


    @PostMapping("/login")
    public String login(String username, String password, Model model , HttpServletRequest request ) {
        User user = userService.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            model.addAttribute("user", user);
            session.setAttribute("currentUser",user);
            return welcome(request);
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(String username, String password,String email ,Model model) {
        User existingUser = userService.findByUsername(username);
        if (existingUser != null) {
            model.addAttribute("error", "用户名已存在");
            return "register";
        } else {
            User newUser = new User(username, password);
            newUser.setEmail(email);
            userService.saveUser(newUser);
            model.addAttribute("user", newUser);
            UserProfile userProfile=new UserProfile();
            userProfile.setUsername(newUser.getUsername());
            userProfileService.saveUserProfile(userProfile);
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().setAttribute("currentUser",null);
        return "login";
    }
    @GetMapping("/delete_account")
    public String delete_account(HttpServletRequest request){
        User user=(User) request.getSession().getAttribute("currentUser");
        userService.deleteUser(user.getId());
        return "login";
    }
}

package com.datalink.controller;

import com.datalink.entity.User;
import com.datalink.entity.UserProfile;
import com.datalink.service.UserProfileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/profile")
public class UserProfileController{
    @Autowired
    private UserProfileService userProfileService;

    @GetMapping
    public String profile(Model model,HttpServletRequest request) {

        User user = getCurrentUser(request);
        if (user != null) {
            UserProfile userProfile = userProfileService.findByUsername(user.getUsername());
            model.addAttribute("userProfile", userProfile);
            return "user-profile/profile";
        } else {
            return "redirect:/user/login";
        }
    }

    @GetMapping("/edit")
    public String editProfile(Model model,HttpServletRequest request) {
        // 获取当前登录的用户，需要根据你的实际情况进行修改
        User user = getCurrentUser(request);
        if (user != null) {
            UserProfile userProfile = userProfileService.findByUsername(user.getUsername());
            model.addAttribute("userProfile", userProfile);
            return "user-profile/edit";
        } else {
            return "redirect:/user/login";
        }
    }

    @PostMapping("/update")
    public String updateProfile(@RequestParam("id") Integer id,
                                @RequestParam("avatar") MultipartFile avatar,
                                @RequestParam("fullName") String fullName,
                                @RequestParam("bio") String bio,
                                @RequestParam("location") String location,
                                @RequestParam("email") String email,
                                @RequestParam("phoneNumber") String phoneNumber,
                                Model model, HttpServletRequest request) {
        UserProfile userProfile = userProfileService.findByUsername(getCurrentUser(request).getUsername());

        if (!avatar.isEmpty()) {
            try {
                String rootPath = new File("").getAbsolutePath();
                String folderPath = rootPath + "/src/main/resources/static/uploads/avatars/";
                File folder = new File(folderPath);
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                String originalFilename = avatar.getOriginalFilename();
                String fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.'));
                String uniqueFilename = UUID.randomUUID().toString() + fileExtension;

                byte[] bytes = avatar.getBytes();
                Path path = Paths.get(folderPath + uniqueFilename);
                Files.write(path, bytes);

                userProfile.setAvatar(uniqueFilename);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "An error occurred while uploading the avatar");
                return "profile_edit";
            }
        }


        userProfile.setFullName(fullName);
        userProfile.setBio(bio);
        userProfile.setLocation(location);
        userProfile.setEmail(email);
        userProfile.setPhoneNumber(phoneNumber);
        userProfileService.updateUserProfile(userProfile);

        model.addAttribute("message", "Profile updated successfully");
        return "redirect:/profile";
    }


    // 获取当前登录的用户，这个方法需要根据你的实际情况进行修改
    private User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        return currentUser;

    }


}

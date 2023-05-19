package com.datalink.controller;


import com.datalink.entity.Attraction;
import com.datalink.entity.Culture;
import com.datalink.entity.Food;
import com.datalink.service.AttractionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/attraction")
public class AttractionController {

    @Autowired
    private AttractionService attractionService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Attraction> attractions = attractionService.findAll();
        model.addAttribute("attractions", attractions);
        return "attraction/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("attraction", new Attraction());
        return "attraction/add";
    }

    @PostMapping("/save")
    public String saveAttraction(@RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam("imageFile") MultipartFile imageFile,
                                 @RequestParam("category") String category,
                                 @RequestParam("location") String location,
                                 Model model, HttpServletRequest request) {
        if (imageFile.isEmpty()) {
            model.addAttribute("message", "Please select an image to upload");
            return "attraction/add";
        }

        try {
            // Get the project root path
            String rootPath = new File("").getAbsolutePath();
            // Define the folder path for storing uploaded files
            String folderPath = rootPath + "/src/main/resources/static/uploads/attractions/";
            // Create the folder if it doesn't exist
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Generate a unique file name
            String originalFilename = imageFile.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.'));
            String uniqueFilename = UUID.randomUUID().toString() + fileExtension;

            // Save the file
            byte[] bytes = imageFile.getBytes();
            Path path = Paths.get(folderPath + uniqueFilename);
            Files.write(path, bytes);

            // Create a new Food object and save it to the database
            Attraction attraction = new Attraction();
            attraction.setName(name);
            attraction.setDescription(description);
            attraction.setImage(uniqueFilename);
            attraction.setCategory(category);
            attraction.setLocation(location);
            attractionService.insert(attraction);

            model.addAttribute("message", "Food added successfully");

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "An error occurred while uploading the image");
        }

        return "redirect:/attraction/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Attraction attraction = attractionService.findById(id);
        model.addAttribute("attraction", attraction);
        return "attraction/edit";
    }
    @GetMapping("/details")
    public String details(@RequestParam("id") Integer id, Model model) {
        Attraction attraction=attractionService.findById(id);
        model.addAttribute("attraction", attraction);
        return "attraction/details";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") Integer id,
                         @RequestParam("name") String name,
                         @RequestParam("description") String description,
                         @RequestParam("imageFile") MultipartFile imageFile,
                         @RequestParam("category") String category,
                         @RequestParam("location") String location,
                         Model model, HttpServletRequest request) {
        Attraction attraction = attractionService.findById(id);

        if (!imageFile.isEmpty()) {
            try {
                String rootPath = new File("").getAbsolutePath();
                String folderPath = rootPath + "/src/main/resources/static/uploads/attractions/";
                File folder = new File(folderPath);
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                String originalFilename = imageFile.getOriginalFilename();
                String fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.'));
                String uniqueFilename = UUID.randomUUID().toString() + fileExtension;

                byte[] bytes = imageFile.getBytes();
                Path path = Paths.get(folderPath + uniqueFilename);
                Files.write(path, bytes);

                attraction.setImage(uniqueFilename);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "An error occurred while uploading the image");
                return "attraction/edit";
            }
        }

        attraction.setName(name);
        attraction.setDescription(description);
        attraction.setCategory(category);
        attraction.setLocation(location);
        attractionService.update(attraction);

        model.addAttribute("message", "Attraction updated successfully");
        return "redirect:/attraction/list";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        attractionService.delete(id);
        return "redirect:/attraction/list";
    }
    @GetMapping("/search")
    public String search(@RequestParam("name") String name, Model model) {
        List<Attraction> attractions = attractionService.findByName(name);
        model.addAttribute("attractions", attractions);
        return "attraction/list";
    }

}

package com.datalink.controller;

import com.datalink.entity.Food;
import com.datalink.service.FoodService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Food>foods = foodService.findAll();
        model.addAttribute("foods", foods);
        return "food/list";
    }

    @GetMapping("/add")
    public String add() {
        return "food/add";
    }

    @PostMapping("/save")
    public String save(@RequestParam("name") String name,
                       @RequestParam("description") String description,
                       @RequestParam("imageFile") MultipartFile imageFile,
                       @RequestParam("specialty") String specialty,
                       @RequestParam("origin") String origin,
                       Model model, HttpServletRequest request) {
        if (imageFile.isEmpty()) {
            model.addAttribute("message", "Please select an image to upload");
            return "food/add";
        }

        try {
            // Get the project root path
            String rootPath = new File("").getAbsolutePath();
            // Define the folder path for storing uploaded files
            String folderPath = rootPath + "/src/main/resources/static/uploads/food/";
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
            Food food = new Food();
            food.setName(name);
            food.setDescription(description);
            food.setImage(uniqueFilename);
            food.setSpecialty(specialty);
            food.setOrigin(origin);
            foodService.insert(food);

            model.addAttribute("message", "Food added successfully");

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "An error occurred while uploading the image");
        }

        return "redirect:/food/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Food food = foodService.findById(id);
        model.addAttribute("food", food);
        return "food/edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") Integer id,
                         @RequestParam("name") String name,
                         @RequestParam("description") String description,
                         @RequestParam("imageFile") MultipartFile imageFile,
                         @RequestParam("specialty") String specialty,
                         @RequestParam("origin") String origin,
                         Model model, HttpServletRequest request) {
        Food food = foodService.findById(id);

        if (!imageFile.isEmpty()) {
            try {
                String rootPath = new File("").getAbsolutePath();
                String folderPath = rootPath + "/src/main/resources/static/uploads/food/";
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

                food.setImage(uniqueFilename);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "An error occurred while uploading the image");
                return "food/edit";
            }
        }

        food.setName(name);
        food.setDescription(description);
        food.setSpecialty(specialty);
        food.setOrigin(origin);
        foodService.update(food);

        model.addAttribute("message", "Food updated successfully");
        return "redirect:/food/list";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        foodService.delete(id);
        return "redirect:/food/list";
    }

    @GetMapping("/details")
    public String details(@RequestParam("id") Integer id, Model model) {
        Food food = foodService.findById(id);
        model.addAttribute("food", food);
        return "food/details";
    }
    @GetMapping("/search")
    public String search(@RequestParam("name") String name, Model model) {
        List<Food> foods = foodService.findByName(name);
        model.addAttribute("foods", foods);
        return "food/list";
    }

}
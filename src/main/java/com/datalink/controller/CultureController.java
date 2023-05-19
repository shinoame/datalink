package com.datalink.controller;

import com.datalink.entity.Attraction;
import com.datalink.entity.Culture;
import com.datalink.entity.Food;
import com.datalink.service.CultureService;
import jakarta.servlet.http.HttpServletRequest;
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
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/culture")
public class CultureController {

    @Autowired
    private CultureService cultureService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Culture> cultures = cultureService.findAll();
        model.addAttribute("cultures", cultures);
        return "culture/list";
    }

    // 其他GET和POST方法，例如create、edit、update、delete等
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("culture", new Culture());
        return "culture/create";
    }
    @PostMapping("/save")
    public String save(@RequestParam("name") String name,
                       @RequestParam("description") String description,
                       @RequestParam("imageFile") MultipartFile imageFile,
                       @RequestParam("history") String history,
                       @RequestParam("origin") String origin,
                       Model model, HttpServletRequest request) {
        if (imageFile.isEmpty()) {
            model.addAttribute("message", "Please select an image to upload");
            return "culture/add";
        }

        try {
            // Get the project root path
            String rootPath = new File("").getAbsolutePath();
            // Define the folder path for storing uploaded files
            String folderPath = rootPath + "/src/main/resources/static/uploads/culture/";
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

            // Create a new Culture object and save it to the database
            Culture culture = new Culture();
            culture.setName(name);
            culture.setDescription(description);
            culture.setImage(uniqueFilename);
            culture.setHistory(history);
            culture.setOrigin(origin);
            cultureService.insert(culture);

            model.addAttribute("message", "Culture added successfully");

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "An error occurred while uploading the image");
        }

        return "redirect:/culture/list";
    }
    @GetMapping("/details")
    public String details(@RequestParam("id") Integer id, Model model) {
        Culture culture=cultureService.findById(id);
        model.addAttribute("culture", culture);
        return "culture/details";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        cultureService.delete(id);
        return "redirect:/culture/list";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Culture culture=cultureService.findById(id);
        model.addAttribute("culture", culture);
        return "culture/edit";
    }
    @PostMapping("/update")
    public String update(@RequestParam("id") Integer id,
                         @RequestParam("name") String name,
                         @RequestParam("description") String description,
                         @RequestParam("imageFile") MultipartFile imageFile,
                         @RequestParam("history") String history,
                         @RequestParam("origin") String origin,
                         Model model, HttpServletRequest request) {
        Culture culture = cultureService.findById(id);

        if (!imageFile.isEmpty()) {
            try {
                String rootPath = new File("").getAbsolutePath();
                String folderPath = rootPath + "/src/main/resources/static/uploads/culture/";
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

                culture.setImage(uniqueFilename);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "An error occurred while uploading the image");
                return "culture/edit";
            }
        }

        culture.setName(name);
        culture.setDescription(description);
        culture.setHistory(history);
        culture.setOrigin(origin);
        cultureService.update(culture);

        model.addAttribute("message", "Culture updated successfully");
        return "redirect:/culture/list";
    }
    @GetMapping("/search")
    public String search(@RequestParam("name") String name, Model model) {
        List<Culture> cultures = cultureService.findByName(name);
        model.addAttribute("cultures", cultures);
        return "culture/list";
    }

}

package com.media.social.Social.Media.controller;

import com.media.social.Social.Media.model.User;
import com.media.social.Social.Media.service.impl.FileStorageServiceImpl;
import com.media.social.Social.Media.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/api/image")
public class ImageController {

    private final String FOLDER_PATH = "/assets/img/";


//    @Autowired
//    private UserServiceImpl userService;
//
//    @PostMapping
//    public String saveImage(MultipartFile file, HttpSession session) throws IOException {
//
//        User user = (User) session.getAttribute("user");
//        System.out.println(user.toString());
//        user.setImage(FOLDER_PATH+file.getOriginalFilename());
//        file.transferTo(new File(FOLDER_PATH));
//        userService.save(user);
//
//        return "redirect:/profile";
//    }

    @Autowired
    private FileStorageServiceImpl fileStorageService;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam(value = "image") MultipartFile file) throws IOException {
        String uploadImage = fileStorageService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] imageData = fileStorageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);


    }
}

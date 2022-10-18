package com.media.social.Social.Media.controller;


import com.media.social.Social.Media.service.impl.FileStorageServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Data
@RestController
@RequestMapping("/image")
public class UploadDownloadController {


    @Autowired
    private FileStorageServiceImpl service;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
        String uploadImage = service.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] imageData=service.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }


//    public UploadDownloadController(FileStorageService fileStorageService) {
//        this.fileStorageService = fileStorageService;
//    }
//
//    @PostMapping("single/double")
//    FileUploadResponse singleFileUpload(@RequestParam("file") MultipartFile file){
//
//        String fileName =fileStorageService.storeFile(file);
//        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/download")
//                .path(fileName)
//                .toUriString();
//
//        String contentType = file.getContentType();
//        FileUploadResponse response = new FileUploadResponse(fileName, contentType, url);
//        return response;
//    }

}

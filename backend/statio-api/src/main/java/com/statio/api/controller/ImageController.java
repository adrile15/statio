package com.statio.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/images")
@CrossOrigin
public class ImageController {

    private final String UPLOAD_DIR =
            System.getProperty("user.dir") + "/uploads/";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile file){

        try {

            File uploadDir = new File(UPLOAD_DIR);

            if(!uploadDir.exists()){
                uploadDir.mkdirs();
            }

            String extension =
                    StringUtils.getFilenameExtension(
                            file.getOriginalFilename()
                    );

            String fileName =
                    UUID.randomUUID() + "." + extension;

            Path path = Paths.get(UPLOAD_DIR + fileName);

            Files.write(path, file.getBytes());

            return ResponseEntity.ok("/uploads/" + fileName);

        } catch (Exception e){

            e.printStackTrace();

            return ResponseEntity.badRequest().body("Error");
        }
    }
}
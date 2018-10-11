package com.ted.getajob.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {

    @Autowired
    private UserService userService;

    private final Path rootLocation = Paths.get("src/main/webapp/src/assets/");

    public void storeProfilePhoto(MultipartFile file, String username) {
        try {
            Path filename = this.rootLocation.resolve(file.getOriginalFilename());
            if (!Files.exists(filename)) {
                Files.copy(file.getInputStream(), filename);
            }
            String photo = filename.toString().substring(20);
            String relative = "../../";
            userService.updatePhoto(username,relative.concat(photo));
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public void init() {
        try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectory(rootLocation);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }
}

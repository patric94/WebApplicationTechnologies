package com.ted.getajob;

import com.ted.getajob.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class GetAJobApplication implements CommandLineRunner {

    @Resource
    private StorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(GetAJobApplication.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
        storageService.init();
    }
}

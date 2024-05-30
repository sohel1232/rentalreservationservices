package com.abg.rentalreservationservices.amazonS3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/s3api")
public class S3Controller {
    @Autowired
    private S3ServiceHandler s3ServiceHandler;

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String uploadFile(@RequestParam MultipartFile file) throws IOException {
        return s3ServiceHandler.uploadFile(file);
    }

    @GetMapping("/view/all")
    public ResponseEntity<List<String>> viewAllFiles() {
        return s3ServiceHandler.viewAll();
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<InputStreamResource> viewFile(@PathVariable String fileName) {
        return s3ServiceHandler.viewFile(fileName);
    }
}
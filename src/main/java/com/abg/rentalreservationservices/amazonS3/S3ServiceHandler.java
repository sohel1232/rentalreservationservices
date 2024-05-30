package com.abg.rentalreservationservices.amazonS3;

import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class S3ServiceHandler {

    private final S3Service s3Service;

    public S3ServiceHandler(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    public ResponseEntity<List<String>> viewAll() {
        ListObjectsV2Result res = s3Service.listAllFiles();
        List<String> fileNames = res.getObjectSummaries().stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(fileNames);
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String url = s3Service.uploadFile(file);
        log.info("url is {}",url);
        return "File uploaded";
    }

    public ResponseEntity<InputStreamResource> viewFile(String fileName) {
        var s3Object = s3Service.getFile(fileName);
        var content = s3Object.getObjectContent();
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(content));
    }
}

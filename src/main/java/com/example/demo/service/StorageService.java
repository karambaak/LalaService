package com.example.demo.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.example.demo.entities.Photo;
import com.example.demo.entities.Portfolio;
import com.example.demo.repository.PhotosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageService {
    private final static String ROOT = "https://servicesearchlalaservice.s3.eu-north-1.amazonaws.com/";
    private final PhotosRepository photosRepository;
    private final AmazonS3 s3Client;
    @Value("${application.bucket.name}")
    private String bucketName;

    //needs logic to link photo to portfolio
    public String uploadFile(MultipartFile file) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        photosRepository.save(Photo.builder()
                .portfolio(Portfolio.builder()
                        .id(2L)
                        .build())
                .photoLink(ROOT + fileName)
                .build());
        fileObj.delete();
        System.out.println("File uploaded : " + fileName);
        return "File uploaded : " + fileName;
    }


    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }


    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }

    public List<String> getAllPhotos() {
        List<Photo> photos = photosRepository.findAll();
        List<String> urls = new ArrayList<>();
        if(!photos.isEmpty()) {
            for (Photo p:
                 photos) {
                urls.add(p.getPhotoLink());
            }
        }
        return urls;
    }
}

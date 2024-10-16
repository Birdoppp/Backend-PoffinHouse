package com.novi.poffinhouse.services;
import com.novi.poffinhouse.models.region.Atlas;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;

import com.novi.poffinhouse.repositories.AtlasRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class AtlasService {
    private final Path fileStoragePath;
    private final  String fileStorageLocation;
    private final AtlasRepository atlasRepository;

    public AtlasService(@Value("${my.upload_location}") String fileStorageLocation, AtlasRepository atlasRepository) throws IOException {
        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
        this.fileStorageLocation = fileStorageLocation;
        this.atlasRepository = atlasRepository;
        Files.createDirectories(fileStoragePath);
    }

    public String addImage(MultipartFile file) throws IOException{

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path filePath = Paths.get(fileStoragePath + "\\" + fileName );

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        atlasRepository.save(new Atlas(fileName));
        return fileName;
    }

    public Resource getImage(String fileName) {

        Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);

        Resource resource;

        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Issue in reading the file", e);
        }

        if(resource.exists()&& resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("the file doesn't exist or not readable");
        }
    }
}
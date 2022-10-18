package com.media.social.Social.Media.service.impl;


import com.media.social.Social.Media.model.ImageData;
import com.media.social.Social.Media.repository.StorageRepository;
import com.media.social.Social.Media.service.FileStorageService;
import com.media.social.Social.Media.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileStorageServiceImpl implements FileStorageService
{
    @Autowired
    private StorageRepository storageRepository;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = storageRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build()
        );

        if (imageData != null) {

            return "File was uploaded successfully: " + file.getOriginalFilename();
        }

        else
            return null;

    }

    @Override
    public byte[] downloadImage(String file_name) {

        Optional<ImageData> dbImageData = storageRepository.findByName(file_name);
        byte[] images = ImageUtil.decompressImage(dbImageData.get().getImageData());

        return images;
    }

    @Override
    public String uploadImageToTheFileSystem(MultipartFile file) throws IOException {
        return null;
    }

    @Override
    public byte[] downloadImageFromTheFileSystem(String file_name) {
        return new byte[0];
    }
}
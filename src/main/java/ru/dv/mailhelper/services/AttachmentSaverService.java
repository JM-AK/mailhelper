package ru.dv.mailhelper.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class AttachmentSaverService {
    private static final String UPLOADED_FOLDER = "Store/";

    public String saveCryptedFileName(MultipartFile file) {
        if (file.isEmpty()) {
            return "";
        }
        String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();
        try {
            Path path = Paths.get(UPLOADED_FOLDER + fileName);
            file.transferTo(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public String saveNotCryptedFileName(MultipartFile file) {
        if (file.isEmpty()) {
            return "";
        }
        String fileName = file.getOriginalFilename();
        try {
            Path path = Paths.get(UPLOADED_FOLDER + fileName);
            file.transferTo(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public String getExtension(MultipartFile file) {
        if (file.isEmpty()) {
            return "";
        }
        String fileName = file.getOriginalFilename();
        String extension = new StringBuilder(fileName).substring(fileName.lastIndexOf(".") + 1);
        return extension;
    }

}

package com.hvn.youtubeclone.service;

import org.springframework.web.multipart.MultipartFile;

@FunctionalInterface
public interface FileService {
    String uploadFile(MultipartFile file);
}

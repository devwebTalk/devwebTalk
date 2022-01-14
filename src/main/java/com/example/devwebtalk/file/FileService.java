package com.example.devwebtalk.file;

import com.example.devwebtalk.entity.File;
import com.example.devwebtalk.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FileService {
    private final FileRepository fileRepository;

    public Long upload(FileUploadVO fileUploadVO) {
        File save = fileRepository.save(new File(fileUploadVO));
        return save.getId();
    }
}

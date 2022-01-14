package com.example.devwebtalk.entity;

import com.example.devwebtalk.file.FileUploadVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileTest {
    @Test
    @DisplayName("파일 생성자 테스트(fileUploadVO)")
    void constructorTest(){
        FileUploadVO uploadVO = new FileUploadVO("upload1.txt", 23L);
        File file = new File(uploadVO);
        System.out.println(file);
    }


}
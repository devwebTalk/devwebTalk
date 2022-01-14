package com.example.devwebtalk.file;

import com.example.devwebtalk.file.FileDownloadVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 파일 처리 관련 컨트롤러
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-11
 * Time: 오후 7:46
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class FileController {

    private final FileService fileService;

    @PostMapping("file/upload")
    public Map<String, Long> upload(FileUploadVO fileUploadVO) {
        Map<String, Long> rtnMap = new HashMap<>();
        Long fileId = fileService.upload(fileUploadVO);
        rtnMap.put("fileId", fileId);
        return rtnMap;
    }

    @GetMapping("/file/test")
    public void test() {
        log.info("test");
    }
}

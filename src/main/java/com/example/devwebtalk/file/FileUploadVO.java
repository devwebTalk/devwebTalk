package com.example.devwebtalk.file;

import com.example.devwebtalk.setting.constant.AppProperties;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-11
 * Time: 오후 7:48
 */
@Getter
public class FileUploadVO implements Serializable {
    // 올린 파일 이름
    private String uploadFileName;
    // 확장자
    private String extension;
    // 파일 크기
    private Long size;

    public FileUploadVO(String uploadFileName, Long size) {
        this.uploadFileName = uploadFileName;
        this.size = size;
        this.extension = FilenameUtils.getExtension(uploadFileName);
    }

    // 파일 확장자 검사
    public boolean isAcceptExtension() {
        return AppProperties.fileAcceptExtension.contains(this.extension);
    }
}

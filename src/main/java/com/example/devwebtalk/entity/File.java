package com.example.devwebtalk.entity;

import com.example.devwebtalk.file.FileType;
import com.example.devwebtalk.file.FileUploadVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.io.FilenameUtils;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static javax.persistence.GenerationType.TABLE;

@NoArgsConstructor
@ToString
@Getter
@Entity
@TableGenerator(
        name = "FILE_SEQ_GENERATOR",
        table = "TB_SEQUENCES",
        pkColumnValue = "FILE_SEQ",
        allocationSize = 1) //TODO 운영엔 50으로 하자
public class File extends BaseEntity {
    @Id
    @GeneratedValue(strategy = TABLE, generator = "FILE_SEQ_GENERATOR")
    @Column(name="FILE_ID")
    private Long id;

    // 올린 파일 이름
    private String originFileName;
    // 저장소 파일 경로 (fileType/2022/01/13)
    private String storeFilePath;
    // 저장소 파일 이름 (UUID + 확장자)
    private String storeFileName;
    // IMAGE, VIDEO, ATTACH
    @Enumerated(EnumType.STRING)
    private FileType fileType;
    // 확장자
    private String extension;
    // 파일 크기
    private Long size;

    public File(FileUploadVO fileUploadVO) {
        this.originFileName = fileUploadVO.getUploadFileName();
        this.size = fileUploadVO.getSize();
        this.extension = fileUploadVO.getExtension();

        this.fileType = FileType.getFileType(extension);
        this.storeFilePath = fileType.toString() + LocalDate.now().format(DateTimeFormatter.ofPattern("/yyyy/MM/dd/"));
        this.storeFileName = UUID.randomUUID().toString();
    }
}

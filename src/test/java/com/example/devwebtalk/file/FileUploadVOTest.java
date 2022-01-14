package com.example.devwebtalk.file;

import com.example.devwebtalk.setting.constant.AppProperties;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = AppProperties.class)
@TestPropertySource(properties = "file.accept.extension=jpg,jpeg")
class FileUploadVOTest {
    @Test
    @DisplayName("파일 허용 확장자 테스트")
    void acceptExtensionTest() {
        FileUploadVO uploadVO = new FileUploadVO("test.jpg",153L);
        Assertions.assertThat(uploadVO.isAcceptExtension()).isTrue();
    }
}
package com.example.devwebtalk.file;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@MockBean(value = {JpaMetamodelMappingContext.class, EntityManager.class})
@WebMvcTest(value = FileControllerTest.class)
class FileControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private FileService fileService;

    @Test
    @DisplayName("파일 업로드 테스트")
    void fileUploadTest() throws Exception {
        // 회원 가입 완료되면 ID 1L을 넘겨준다 가정하자
        when(fileService.upload(null)).thenReturn(1L);

        mvc.perform(MockMvcRequestBuilders.post("/file/upload")
                        .contentType(APPLICATION_JSON)
                        .param("uploadFileName","test.jpg")
                        .param("size","500")
                        )
                //.andExpect(status().isOk())
                //.andExpect(jsonPath("$.userId").value(1L))
                .andDo(MockMvcResultHandlers.print());
    }
}
package com.example.devwebtalk.setting.constant;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Getter
public class AppProperties {
    private Set<String> fileAcceptExtensionNonStatic;
    public static Set<String> fileAcceptExtension;

    @Value("${file.accept.extension}")
    public void setFileAcceptExtensionNonStatic(Set<String> fileAcceptExtensionNonStatic) {
        this.fileAcceptExtension = fileAcceptExtensionNonStatic;
    }
}

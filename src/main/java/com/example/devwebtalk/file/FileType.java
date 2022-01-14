package com.example.devwebtalk.file;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-11
 * Time: 오후 7:53
 */
public enum FileType {
    IMAGE(Arrays.asList("jpg","jpeg","bmp","png","gif","tif","tiff","raw"))
    ,VIDEO(Arrays.asList("avi","mp4","mkv","wmv","mov","ts","tp","flv"))
    ,ATTACH(Collections.emptyList());

    private List<String> extensionList;
    FileType(List<String> extensionList) {
        this.extensionList = extensionList;
    }

    public static FileType getFileType(@NotNull String extension) {
        return Arrays.stream(FileType.values())
                .filter(fileType -> fileType.extensionList.contains(extension.toLowerCase()))
                .findAny()
                .orElse(ATTACH);
    }
}
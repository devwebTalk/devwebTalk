package com.example.devwebtalk.file;

import com.example.devwebtalk.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}

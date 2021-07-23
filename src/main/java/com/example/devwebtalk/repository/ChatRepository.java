package com.example.devwebtalk.repository;

import com.example.devwebtalk.entity.Chat;
import com.example.devwebtalk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 2021-07-21
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
public interface ChatRepository extends JpaRepository<Chat, Long> {
}

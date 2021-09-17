package com.example.devwebtalk.service;

import com.example.devwebtalk.entity.ChattingRoom;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.exception.NonExistentUserException;
import com.example.devwebtalk.repository.ChattingRoomRepository;
import com.example.devwebtalk.repository.UserRepository;
import com.example.devwebtalk.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * 2021-07-21
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@RequiredArgsConstructor
@Service
public class ChattingRoomService {
    final ChattingRoomRepository roomRepository;
    final UserRepository userRepository;

    public Long save(ChattingRoom room) {
        room.makeName();
        ChattingRoom c = roomRepository.save(room);
        return c.getId();
    }

    public ChattingRoom getOrCreateRoom(LoginVO loginVo, String friendEmail) {
        List<User> users = selectTwoUsersByLoginAndFriendEmail(loginVo.getEmail(), friendEmail);
        Optional<ChattingRoom> rooms =
                roomRepository.findOneByUserAndFriend(users);
        return rooms.orElseGet(createRoom(users));
    }

    private Supplier<? extends ChattingRoom> createRoom(List<User> users) {
        ChattingRoom room = new ChattingRoom(users);
        room.makeName();
        save(room);
        return () -> room;
    }

    private List<User> selectTwoUsersByLoginAndFriendEmail(String email, String friendEmail) {
        Optional<User> user = userRepository.findByEmail(email);
        Optional<User> friend = userRepository.findByEmail(friendEmail);
        if(user.isEmpty() || friend.isEmpty()) {
            throw new NonExistentUserException("유저 정보가 없습니다.");
        }
        return Arrays.asList(user.get(), friend.get());
    }
}

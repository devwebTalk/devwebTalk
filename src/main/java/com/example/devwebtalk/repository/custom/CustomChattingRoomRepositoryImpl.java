package com.example.devwebtalk.repository.custom;

import com.example.devwebtalk.dto.UserModifyDto;
import com.example.devwebtalk.entity.*;
import com.example.devwebtalk.entity.QChattingRoom;
import com.example.devwebtalk.entity.type.SocialType;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.devwebtalk.entity.QChattingRoom.*;
import static com.example.devwebtalk.entity.QChattingRoom.chattingRoom;

import static com.example.devwebtalk.entity.QUser.user;


@Repository
@RequiredArgsConstructor
public class CustomChattingRoomRepositoryImpl implements CustomChattingRoomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ChattingRoom> findOneByUserAndFriend(List<User> users) {
        ChattingRoom room = queryFactory.select(chattingRoom)
                .from(chattingRoom)
                .leftJoin(chattingRoom.users, user)
                .where()
                .fetchOne();
        return Optional.ofNullable(room);
    }
}

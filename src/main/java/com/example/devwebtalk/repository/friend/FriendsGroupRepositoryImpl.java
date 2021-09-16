package com.example.devwebtalk.repository.friend;

import com.example.devwebtalk.dto.FriendGroupDto;
import com.example.devwebtalk.entity.User;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static com.example.devwebtalk.entity.QFriendsGroup.friendsGroup;

public class FriendsGroupRepositoryImpl implements FriendsGroupRepositoryCustom{
	private final JPAQueryFactory queryFactory;

	public FriendsGroupRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public List<FriendGroupDto> getFriendGroupDtoList(User user) {
		return queryFactory
				.select(Projections.fields(FriendGroupDto.class,
						friendsGroup.id.as("groupId"),
						friendsGroup.groupName))
				.from(friendsGroup)
				.where(friendsGroup.user().eq(user))
				.orderBy(friendsGroup.id.desc())
				.fetch();
	}
}

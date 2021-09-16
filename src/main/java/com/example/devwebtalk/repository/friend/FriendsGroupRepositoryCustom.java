package com.example.devwebtalk.repository.friend;

import com.example.devwebtalk.dto.FriendGroupDto;
import com.example.devwebtalk.entity.User;

import java.util.List;

public interface FriendsGroupRepositoryCustom {
	List<FriendGroupDto> getFriendGroupDtoList(User user);
}

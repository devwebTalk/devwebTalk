package com.example.devwebtalk.service.friend;

import com.example.devwebtalk.dto.FriendDto;
import com.example.devwebtalk.dto.FriendGroupDto;
import com.example.devwebtalk.entity.FriendsGroup;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.repository.friend.FriendRepository;
import com.example.devwebtalk.repository.friend.FriendsGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendDtoService {

	final FriendRepository friendRepository;
	final FriendsGroupRepository friendsGroupRepository;

	public FriendDtoService(FriendRepository friendRepository, FriendsGroupRepository friendsGroupRepository) {
		this.friendRepository = friendRepository;
		this.friendsGroupRepository = friendsGroupRepository;
	}

	public List<FriendDto> getAllFriendList(User user) {
		return friendRepository.getListAll(user);
	}

	public List<FriendGroupDto> getAllFriendGroupList(User user) {
		return friendsGroupRepository.getFriendGroupDtoList(user);
	}
}

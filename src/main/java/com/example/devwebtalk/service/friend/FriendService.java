package com.example.devwebtalk.service.friend;

import com.example.devwebtalk.dto.FriendDto;
import com.example.devwebtalk.entity.Friend;
import com.example.devwebtalk.entity.FriendsGroup;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.repository.UserRepository;
import com.example.devwebtalk.repository.friend.FriendRepository;
import com.example.devwebtalk.repository.friend.FriendsGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendService {

	final FriendsGroupRepository friendsGroupRepository;
	final FriendRepository friendRepository;
	final UserRepository userRepository;

	public FriendService(FriendsGroupRepository friendsGroupRepository, FriendRepository friendRepository, UserRepository userRepository) {
		this.friendsGroupRepository = friendsGroupRepository;
		this.friendRepository = friendRepository;
		this.userRepository = userRepository;
	}

	// 친구 목록 생성 .
	public Long createGroup(User user, String name){
		FriendsGroup friendsGroup = friendsGroupRepository.save(new FriendsGroup(name, user));
		return friendsGroup.getId();
	}

	// 친구 그룹 이름 수정
	public void updateGroup(Long id, String name) {
		if(!name.equals("")) return;
		Optional<FriendsGroup> friendsGroup = friendsGroupRepository.findById(id);
		friendsGroup.ifPresent(group -> group.changeName(name));
	}

	// 친구 그룹 삭제
	public void deleteGroup(Long id) {
		friendsGroupRepository.deleteById(id);
	}

	// 그룹에 친구 추가
	public void addFriendToGroup(Long userId, Long groupId) {
		Optional<FriendsGroup> groupOptional = friendsGroupRepository.findById(groupId);
		Optional<User> userOptional = userRepository.findById(userId);
		if(groupOptional.isPresent() && userOptional.isPresent()) {
			FriendsGroup friendsGroup = groupOptional.get();
			User user = userOptional.get();
			Friend friend = new Friend(friendsGroup, user);
			friendRepository.save(friend);
		}

	}

	//그룹에서 친구 삭제
	public void delFriendToGroup(Long friendId) {
		Optional<Friend> friendOptional = friendRepository.findById(friendId);
		if(friendOptional.isPresent()) {
			Friend friend = friendOptional.get();
			friendRepository.delete(friend);
		}
	}

	// 친구 수정
	public void moveFriendToGroup(Long friendId, Long groupId, String name) {
		Friend friend = friendRepository.getById(friendId);
		if(!friend.getFriendName().equals(name)) friend.changeName(name);
		else {
			FriendsGroup friendsGroup = friendsGroupRepository.getById(friend.getFriendsGroup().getId());

		}
	}

	// 유저의 친구 목록 불러오기
	public List<FriendDto> getAllFriends(User user) {
		List<FriendDto> listAll = friendRepository.getListAll(user);
		return listAll;
	}


}

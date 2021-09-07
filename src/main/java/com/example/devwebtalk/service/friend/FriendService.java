package com.example.devwebtalk.service.friend;

import com.example.devwebtalk.dto.FriendDto;
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
		Optional<FriendsGroup> friendsGroup = friendsGroupRepository.findById(id);
		friendsGroup.ifPresent(group -> group.changeName(name));
	}

	// 친구 그룹 삭제
	public void deleteGroup(Long id) {
		friendsGroupRepository.deleteById(id);
	}

	// 친구 추가
	public FriendResults addFriend(User user, String email) {
		FriendResults results = FriendResults.FAIL;
		Optional<User> friend = userRepository.findByEmail(email);

		// 해당 email 유저가 없음
		if(friend.isEmpty()) return FriendResults.NOT_FIND_USER;

		// 기본 그룹이 없는 상태에서 친구추가
		if(friendsGroupRepository.existsByUserAndGroupName(user,"")){

		}else { // 기본그룹이 있는 상태에서 친구추가

		}
		return results;
	}

	// 그룹에 친구 추가
	//그룹에서 친구 삭제
	// 친구 그룹 이동

	// 유저의 친구 목록 불러오기
	public List<FriendDto> getAllFriends(User user) {
		List<FriendDto> listAll = friendRepository.getListAll(user);
		return listAll;
	}


}

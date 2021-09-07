package com.example.devwebtalk.service;

import com.example.devwebtalk.entity.Friend;
import com.example.devwebtalk.entity.FriendsGroup;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.repository.friend.FriendRepository;
import com.example.devwebtalk.repository.friend.FriendsGroupRepository;
import com.example.devwebtalk.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@Service
public class InitDummyService {
	final UserRepository userRepository;
	final FriendRepository friendRepository;
	final FriendsGroupRepository friendsGroupRepository;

	public InitDummyService(UserRepository userRepository, FriendRepository friendRepository, FriendsGroupRepository friendsGroupRepository) {
		this.userRepository = userRepository;
		this.friendRepository = friendRepository;
		this.friendsGroupRepository = friendsGroupRepository;
	}

	@PersistenceContext
	EntityManager em;

	@PostConstruct
	@Transactional
	public void init() {
		User userA = new User("userA","a@devweb.com", LocalDateTime.now());
		userA.setPw("123qwe!");
		User userB = new User("userB","b@devweb.com", LocalDateTime.now());
		userB.setPw("123qwe!");
		User userC = new User("userC","c@devweb.com", LocalDateTime.now());
		userC.setPw("123qwe!");
		userRepository.save(userA);
		userRepository.save(userB);
		userRepository.save(userC);

		FriendsGroup friendsGroupA = new FriendsGroup("", userA);
		FriendsGroup friendsGroupB = new FriendsGroup("B", userB);
		FriendsGroup friendsGroupA2 = new FriendsGroup("GroupName", userA);
		friendsGroupRepository.save(friendsGroupA);
		friendsGroupRepository.save(friendsGroupB);
		friendsGroupRepository.save(friendsGroupA2);

		Friend friendB = new Friend(friendsGroupA, userB);
		Friend friendC = new Friend(friendsGroupA, userC);
		Friend friendB2 = new Friend(friendsGroupA2, userB);
		Friend friendC2 = new Friend(friendsGroupA2, userC);
		friendRepository.save(friendB);
		friendRepository.save(friendC);
		friendRepository.save(friendB2);
		friendRepository.save(friendC2);

		Friend friendBC = new Friend(friendsGroupB, userC);
		friendRepository.save(friendBC);

	}
}

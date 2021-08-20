package com.example.devwebtalk.service.friend;

import com.example.devwebtalk.entity.FriendsGroup;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.service.friend.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class FriendServiceTest {
	@Autowired
	FriendService friendService;
	@PersistenceContext
	EntityManager em;

	@Test
	void createGroupTest() {
		User user = new User("A");
		em.persist(user);
		friendService.createGroup(user,"testGroup1");
		friendService.createGroup(user,"testGroup2");
		friendService.createGroup(user,"testGroup3");
		friendService.createGroup(user,"testGroup4");
	}

}
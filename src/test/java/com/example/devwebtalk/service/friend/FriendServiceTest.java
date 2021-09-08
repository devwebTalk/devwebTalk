package com.example.devwebtalk.service.friend;

import com.example.devwebtalk.entity.FriendsGroup;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.repository.friend.FriendsGroupRepository;
import com.example.devwebtalk.service.friend.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class FriendServiceTest {
	@Autowired
	FriendService friendService;
	@Autowired
	FriendsGroupRepository friendsGroupRepository;
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

	@Test
	void updateGroupTest() {
		User user = new User("A");
		em.persist(user);
		Long id = friendService.createGroup(user, "testGroup1");
		friendService.updateGroup(id, "change");
		Optional<FriendsGroup> group = friendsGroupRepository.findById(id);
		assertThat(group.isPresent()).isTrue();
		assertThat(group.get().getGroupName()).isEqualTo("change");
		friendService.deleteGroup(id);
		Optional<FriendsGroup> group2 = friendsGroupRepository.findById(id);
		assertThat(group2.isPresent()).isFalse();
	}
}
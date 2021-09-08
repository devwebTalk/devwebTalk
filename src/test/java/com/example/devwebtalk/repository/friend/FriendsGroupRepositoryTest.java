package com.example.devwebtalk.repository.friend;

import static org.assertj.core.api.Assertions.*;

import com.example.devwebtalk.dto.FriendDto;
import com.example.devwebtalk.entity.Friend;
import com.example.devwebtalk.entity.FriendsGroup;
import com.example.devwebtalk.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
class FriendsGroupRepositoryTest {
	@PersistenceContext
	EntityManager em;

	@Autowired
	FriendsGroupRepository fgRepository;

	@Autowired
	FriendRepository friendRepository;

	@Test
	void findAllByUserAndGroupName() {
		User user = new User("userA");
		FriendsGroup group = new FriendsGroup("gn", user);
		em.persist(user);
		em.persist(group);

		boolean emptyExist = fgRepository.existsByUserAndGroupName(user,"");
		boolean gnExist = fgRepository.existsByUserAndGroupName(user,"gn");
		List<FriendsGroup> emptyNameGroup = fgRepository.findAllByUserAndGroupName(user, "");
		List<FriendsGroup> gnNameGroup = fgRepository.findAllByUserAndGroupName(user, "gn");

		assertThat(emptyExist).isFalse();
		assertThat(gnExist).isTrue();
		assertThat(emptyNameGroup).isEmpty();
		assertThat(gnNameGroup.size()).isEqualTo(1);

	}

	@Test
	void getListAllTest() {
		User userA = new User("userA");
		User userB = new User("userB");
		User userC = new User("userC");
		FriendsGroup group1 = new FriendsGroup("g1", userA);
		FriendsGroup group2 = new FriendsGroup("g2", userA);
		em.persist(userA);
		em.persist(userB);
		em.persist(userC);
		em.persist(group1);
		em.persist(group2);
		Friend friend1 = new Friend(group1,userB);
		Friend friend11 = new Friend(group1,userC);
		Friend friend2 = new Friend(group2,userC);
		em.persist(friend1);
		em.persist(friend11);
		em.persist(friend2);

		List<FriendDto> listAll = friendRepository.getListAll(userA);
		System.out.println("listAll = " + listAll.toString());

		assertThat(listAll.get(0).getFriendName()).isEqualTo("userB");
	}
}
package com.example.devwebtalk.service.friend;

import static org.assertj.core.api.Assertions.*;

import com.example.devwebtalk.dto.FriendDto;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.repository.UserRepository;
import com.example.devwebtalk.service.friend.FriendDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
class FriendDtoServiceTest {

	@Autowired
	FriendDtoService friendDtoService;

	@Autowired
	UserRepository userRepository;

	@Test
	void getAllFriendList() {
		Optional<User> userA = userRepository.findByEmail("a@devweb.com");
		List<FriendDto> allFriendList = new ArrayList<>();
		if(userA.isPresent()){
			allFriendList = friendDtoService.getAllFriendList(userA.get());
		}
		assertThat(allFriendList.size()).isEqualTo(4);
		assertThat(allFriendList.get(0).getGroupName()).isEqualTo("");
		assertThat(allFriendList.get(1).getFriendName()).isEqualTo("userC");

	}
}
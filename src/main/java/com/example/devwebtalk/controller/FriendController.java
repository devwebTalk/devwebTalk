package com.example.devwebtalk.controller;

import com.example.devwebtalk.dto.FriendDto;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.entity.type.SocialType;
import com.example.devwebtalk.service.UserService;
import com.example.devwebtalk.service.friend.FriendService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

import static com.example.devwebtalk.entity.QUser.user;

@Controller
public class FriendController {

	final FriendService friendService;
	final UserService userService;

	public FriendController(FriendService friendService, UserService userService) {
		this.friendService = friendService;
		this.userService = userService;
	}

	@GetMapping(value = "/user/friend")
	public String friendListView() {
		return "/user/friendList";
	}

	@GetMapping(value = "/user/friend/list")
	@ResponseBody
	public List<FriendDto> getFriendList() {
		Optional<User> userA = userService.findById(1L);
		List<FriendDto> allFriends = friendService.getAllFriends(userA.orElse(null));
		return allFriends;
	}
}

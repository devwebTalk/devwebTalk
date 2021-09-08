package com.example.devwebtalk.controller;

import com.example.devwebtalk.dto.FriendDto;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.service.UserService;
import com.example.devwebtalk.service.friend.FriendDtoService;
import com.example.devwebtalk.service.friend.FriendService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class FriendController {

	final FriendService friendService;
	final UserService userService;
	final FriendDtoService friendDtoService;

	public FriendController(FriendService friendService, UserService userService, FriendDtoService friendDtoService) {
		this.friendService = friendService;
		this.userService = userService;
		this.friendDtoService = friendDtoService;
	}

	@GetMapping(value = "/friend")
	public String friendListView() {
		return "/user/friendList";
	}

	@GetMapping(value = "/friend/list")
	@ResponseBody
	public List<FriendDto> getFriendList() {
		Optional<User> userA = userService.findById(1L);
		List<FriendDto> allFriends = friendDtoService.getAllFriendList(userA.orElse(null));
		return allFriends;
	}

	@PostMapping(value = "/friend/group")
	@ResponseBody
	public String createFriendGroup(@RequestParam String name) {
		String result = "fail";
		Optional<User> userA = userService.findById(1L);
		if(userA.isPresent()) {
			Long aLong = friendService.createGroup(userA.get(), name);
			result = String.valueOf(aLong);
		}
		return result;
	}

	@PostMapping(value = "/friend/group/{id}")
	@ResponseBody
	public String updateFriendGroup(@RequestParam String name, @PathVariable("id") Long id) {
		String result = "fail";
		Optional<User> userA = userService.findById(1L);
		if(name.isEmpty()) {
			friendService.deleteGroup(id);
			result = "delete complete";
		}else {
			friendService.updateGroup(id, name);
			result = "update complete";
		}

		return result;
	}
}

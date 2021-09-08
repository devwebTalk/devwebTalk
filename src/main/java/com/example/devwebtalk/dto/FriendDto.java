package com.example.devwebtalk.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FriendDto {
	private Long groupId;
	private Long friendId;
	private String friendName;
	private String groupName;
}

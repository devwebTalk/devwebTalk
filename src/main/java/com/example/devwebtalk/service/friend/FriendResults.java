package com.example.devwebtalk.service.friend;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FriendResults {
	ADD_FRIEND_SUCCESS(true, "성공적으로 추가되었습니다"),
	NOT_FIND_USER(false, "해당 유저를 찾을 수 없습니다"),
	DUPLICATE(false,"이미 추가된 친구입니다"),
	FAIL(false, "친구 추가를 실패하였습니다");

	private boolean aBoolean;
	private String message;

	public boolean getBoolean() {
		return this.aBoolean;
	}
}

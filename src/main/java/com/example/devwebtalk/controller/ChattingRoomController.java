package com.example.devwebtalk.controller;

import com.example.devwebtalk.entity.ChattingRoom;
import com.example.devwebtalk.service.ChattingRoomService;
import com.example.devwebtalk.setting.annotation.Login;
import com.example.devwebtalk.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kim Young Long.
 * Date : 2021-09-17.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
@RequiredArgsConstructor
@RequestMapping("/room")
@Controller
public class ChattingRoomController {
    final ChattingRoomService chattingRoomService;

    @GetMapping("/{friendEmail}")
    public String enterRoom(@Login LoginVO loginVO
            , @PathVariable("friendEmail") String friendEmail
            , Model model) {
        ChattingRoom room = chattingRoomService.getOrCreateRoom(loginVO, friendEmail);
        model.addAttribute("room", room);
        return "/chat/room";
    }
}

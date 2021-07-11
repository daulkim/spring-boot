package com.study.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {


	@MessageMapping("/chat.join")
	@SendTo("/topic/subscribers")
	public ChatMessage join(ChatMessage msg) throws Exception {

		return new ChatMessage(msg.getFrom(), "님이 입장하였습니다.", "ALL");
	}

	@MessageMapping("/chat.message")
	@SendTo("/topic/subscribers")
	public ChatMessage message(ChatMessage msg) throws Exception {

		return new ChatMessage(msg.getFrom(), msg.getText(), "ALL");
	}

}

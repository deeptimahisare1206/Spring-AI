package com.code.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/ollama")
public class OllamaController {
	
	private ChatClient chatclient;

	public OllamaController(OllamaChatModel chatModel) {
		this.chatclient = ChatClient.create(chatModel);
	}
	
	@GetMapping("/{message}")
	public ResponseEntity<String> getAnswer(@PathVariable String message) {
		String response=chatclient.prompt(message).call().content();
		return ResponseEntity.ok(response);
	}
	

}

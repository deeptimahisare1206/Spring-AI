package com.email.writer.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailGeneratorController {
	
	@Autowired
	private EmailGeneratorService emailService;

	@PostMapping("/generate")
	public ResponseEntity<String> emailGenerate(@RequestBody EmailRequest emailreq){
		
		String response =emailService.emailReply(emailreq);
		return ResponseEntity.ok(response);
	}
}

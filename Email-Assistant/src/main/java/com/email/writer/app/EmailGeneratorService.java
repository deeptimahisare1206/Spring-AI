package com.email.writer.app;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmailGeneratorService {
	
	private final WebClient webClient;
	
	
	public EmailGeneratorService(WebClient.Builder webClient) {
		super();
		this.webClient = webClient.build();
	}



	@Value("${gemini.api.url}")
	private String geminiApiUrl;


	@Value("${gemini.api.key}")
	private String geminiApiKey;

	public String emailReply(EmailRequest emailreq) {

		String prompt = buildPrompt(emailreq);

		Map<String, Object> requestBody = Map.of("contents", new Object[] { Map.of("parts", new Object[] {

				Map.of("text", new Object[] {

				}) }) });
		
		String response =webClient.post()
				.uri(geminiApiUrl + geminiApiKey)
				.header("Content-Type", "application/json")
				.bodyValue(requestBody)
				.retrieve()
				.bodyToMono(String.class)
				.block();
		
		
		return extractApiResponse(response);
	}
	
	

	private String extractApiResponse(String response) {
		try {
			
			ObjectMapper obj = new ObjectMapper();
			JsonNode rootNode = obj.readTree(response);
			return rootNode.path("candidates")
					.get(0)
					.path("contents")
					.path("parts")
					.get(0)
					.path("text")
					.asText();
					
		} catch (Exception e) {
			return "Error Processing response" + e.getMessage();
		}

	}



	public String buildPrompt(EmailRequest emailreq) {

		StringBuilder prompt = new StringBuilder();

		prompt.append(
				"Generate a professional email reply for the following email content. Please do not generate subject line.");
		if (emailreq.getTone() != null && !emailreq.getTone().isEmpty()) {
			prompt.append("Use a ").append(emailreq.getTone()).append("tone");
		}
		prompt.append("\n Original mail: \n").append(emailreq.getEmailContent());

		return prompt.toString();
	}
}

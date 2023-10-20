package br.com.erudio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.erudio.vo.request.ChatGptRequest;
import br.com.erudio.vo.response.ChatGptResponse;

@Service
public class ChatGptService {

    @Value("${openai.model}")
    private String model;

    @Value(("${openai.api.url}"))
    private String apiURL;

    @Autowired
    private RestTemplate template;

    public String chat(String prompt){
        ChatGptRequest request = new ChatGptRequest(model, prompt);
        ChatGptResponse response = template.postForObject(apiURL, request, ChatGptResponse.class);
        return response.getChoices().get(0).getMessage().getContent();
    }
}

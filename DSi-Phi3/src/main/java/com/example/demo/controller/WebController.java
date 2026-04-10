package com.example.demo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class WebController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String q, Model model) {

        String url = "http://localhost:11434/api/chat";

        String body = """
        {
          "model": "phi3",
          "messages": [
            { "role": "user", "content": "%s" }
          ],
          "stream": false
        }
        """.formatted(q);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(body, headers);

            String response = restTemplate.postForObject(url, request, String.class);

            String clean = extract(response);
            int maxLenght = 2000;
            if (clean.length() > maxLenght) {
                clean = clean.substring(0, maxLenght) + "...";
            }

            model.addAttribute("response", clean);

        } catch (Exception e) {
            model.addAttribute("response", "Error: Ollama not responding");
        }

        return "index";
    }

    // extract message content from Ollama JSON
    private String extract(String json) {
        try {
            JsonNode root = mapper.readTree(json);
            return root.path("message").path("content").asText();
        } catch (Exception e) {
            return "Error parsing response";
        }
    }

    @GetMapping("/ip")
    @ResponseBody
    public String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
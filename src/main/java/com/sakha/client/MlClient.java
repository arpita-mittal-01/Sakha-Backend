package com.sakha.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sakha.client.request.SakhaChatRequest;
import com.sakha.client.response.SakhaChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MlClient {
  private HttpHeaders headers = new HttpHeaders();
  private RestTemplate restTemplate = new RestTemplate();

  @Value("${ml.model.base.url}")
  private String mlBaseUrl;

  private ObjectMapper objectMapper = new ObjectMapper();

  MlClient() {
    headers.setContentType(MediaType.APPLICATION_JSON);
  }

  public SakhaChatResponse getSakhaResponse(SakhaChatRequest sakhaChatRequest) {
    String response = null;
    SakhaChatResponse sakhaChatResponse = null;
    try {
      ResponseEntity<String> responseEntity =
          restTemplate.exchange(
              mlBaseUrl,
              HttpMethod.POST,
              new HttpEntity<>(sakhaChatRequest, headers),
              String.class);
      if (responseEntity.getStatusCodeValue() == HttpStatus.OK.value()) {
        response = responseEntity.getBody();
        sakhaChatResponse = objectMapper.readValue(response, SakhaChatResponse.class);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return sakhaChatResponse;
  }
}

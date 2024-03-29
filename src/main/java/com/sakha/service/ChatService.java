package com.sakha.service;

import com.sakha.client.MlClient;
import com.sakha.client.request.SakhaChatRequest;
import com.sakha.client.response.SakhaChatResponse;
import com.sakha.entity.Chat;
import com.sakha.entity.ChatResponse;
import com.sakha.repository.ChatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ChatService {

  @Autowired private MlClient mlClient;
  @Autowired private ChatRepo chatRepo;

  public SakhaChatResponse getSakhaResponse(SakhaChatRequest sakhaChatRequest) {
    return mlClient.getSakhaResponse(sakhaChatRequest);
  }

  public Chat saveChat(ChatResponse chatResponse, Long userId) {
    Chat chat = new Chat();
    chat.setChatDate(new Date());
    chat.setMessage(chatResponse.getMessage());
    chat.setSakhaResponse(chatResponse.getSakhaResponse());
    chat.setUserId(userId);
    chat.setChatDate(new Date());
    return chatRepo.save(chat);
  }
  public List<Chat> findAllByUserId(Long userId)
  {
    return chatRepo.findAllByUserId(userId);
  }

}

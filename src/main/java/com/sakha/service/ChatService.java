package com.sakha.service;

import com.sakha.client.MlClient;
import com.sakha.client.request.SakhaChatRequest;
import com.sakha.client.response.SakhaChatResponse;
import com.sakha.entity.*;
import com.sakha.repository.ChatRepo;
import com.sakha.repository.FeedbackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

  @Autowired private MlClient mlClient;
  @Autowired private ChatRepo chatRepo;
  @Autowired private FeedbackRepo feedbackRepo;

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

  public List<Chat> findAllByUserId(Long userId) {
    return chatRepo.findAllByUserId(userId);
  }

  public Message chatLikeDislike(ChatActivityRequest chatActivityRequest) {
    Optional<Chat> op = chatRepo.findById(chatActivityRequest.getChatId());
    if (op.isPresent()) {
      Chat chat = op.get();
      if (Boolean.TRUE.equals(chatActivityRequest.getLiked())) {
        chat.setLiked(Boolean.TRUE);
      }
      if (Boolean.TRUE.equals(chatActivityRequest.getDisliked())) {
        chat.setDisliked(Boolean.FALSE);
      }
      chatRepo.save(chat);
    }
    return new Message("Activity saved!");
  }

  public FeedBack saveFeedbackForChat(FeedbackRequest feedbackRequest) {
    FeedBack feedback = new FeedBack();
    feedback.setChatId(feedbackRequest.getChatId());
    feedback.setUserId(feedbackRequest.getUserId());
    feedback.setFeedback(feedbackRequest.getFeedback());
    feedback.setCreatedAt(new Date());
    feedbackRepo.save(feedback);
    return feedback;
  }
}

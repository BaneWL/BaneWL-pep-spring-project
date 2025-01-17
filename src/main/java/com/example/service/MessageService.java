package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    MessageRepository messageRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message postMessage(Message message){
        if ((message.getMessageText() == "") || (message.getMessageText().length() > 255)){
            return null;
        }
        try{
            return messageRepository.save(message);
        } catch(Exception e) {
            return null;
        }
    }

    public List<Message> getMessages(){
        return messageRepository.findAll();
    }

    public Message getMessage(int id){
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent()){
            return optionalMessage.get();
        }
        return null;
    }

    public int deleteMessage(int id){
        int affectedRows = 0;
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent()){
            affectedRows++;
            messageRepository.deleteById(id);
        }
        return affectedRows;
    }

    public int updateMessage(int id, String newMessageText){
        int affectedRows = 0;
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if((newMessageText == "") || (newMessageText.length() > 255)){
            return affectedRows;
        }
        if ((optionalMessage.isPresent())){
            Message message = optionalMessage.get();
            message.setMessageText(newMessageText);
            messageRepository.save(message);
            affectedRows++;
        }
        return affectedRows;
    }

    public List<Message> getUserMessages(int id){
        return messageRepository.findMessagesByPostedBy(id);
    }

}

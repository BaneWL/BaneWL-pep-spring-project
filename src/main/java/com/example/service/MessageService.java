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
        return messageRepository.save(message);
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

    public Message deleteMessage(int id){
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent()){
            messageRepository.deleteById(id);
            return optionalMessage.get();
        }
        return null;
    }

    public Message updateMessage(int id, Message replacement){
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent()){
            Message message = optionalMessage.get();
            message.setMessageText(replacement.getMessageText());
            return messageRepository.save(message);
        }
        return null;
    }

    public List<Message> getUserMessages(int id){
        return messageRepository.findMessagesByPostedBy(id);
    }

}

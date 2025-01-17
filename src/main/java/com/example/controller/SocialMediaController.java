package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account){
        if ((account.getUsername() == "") || (account.getPassword().length() < 4)){
            return ResponseEntity.status(400).build();
        }
        Account newAccount = accountService.registerAccount(account);
        if (newAccount == null){
            return ResponseEntity.status(409).build();
        }
        return ResponseEntity.ok(newAccount);        
    }

    @PostMapping("/login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account account){
        Account loginAccount = accountService.loginAccount(account);
        if (loginAccount == null){
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(loginAccount);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> postMessage(@RequestBody Message message){
        Message postMessage = messageService.postMessage(message);
        if (postMessage == null){
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.ok(postMessage);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages(){
        return ResponseEntity.ok(messageService.getMessages());
    }

    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessage(@PathVariable int message_id){
        return ResponseEntity.ok(messageService.getMessage(message_id));
    }

    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable int message_id){
        int rowsDeleted = messageService.deleteMessage(message_id);
        if (rowsDeleted > 0){
            return ResponseEntity.ok(rowsDeleted);
        }
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer> updateMessage(@RequestBody JsonNode request, @PathVariable int message_id){
        String newMessageText = request.get("messageText").asText();
        int updatedMessage = messageService.updateMessage(message_id, newMessageText);
        if (updatedMessage == 0){
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.ok(updatedMessage);
    }

    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getUserMessages(@PathVariable int account_id){
        return ResponseEntity.ok(messageService.getUserMessages(account_id));
    }
}

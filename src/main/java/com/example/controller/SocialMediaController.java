package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

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
            return ResponseEntity.status(400).body(account);
        }
        Account newAccount = accountService.registerAccount(account);
        if (newAccount == null){
            return ResponseEntity.status(409).body(account);
        }
        return ResponseEntity.ok(newAccount);        
    }

    @PostMapping("/login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account account){
        Account loginAccount = accountService.loginAccount(account);
        if (loginAccount == null){
            return ResponseEntity.status(401).body(account);
        }
        return ResponseEntity.ok(loginAccount);
    }

    @PostMapping("/message")
    public ResponseEntity<Message> postMessage(@RequestBody Message message){
        Message postMessage = messageService.postMessage(message);
        return ResponseEntity.ok(postMessage);
    }

    @GetMapping("/messages")
    public Message getMessages(){
        return null;
    }

    @GetMapping("/messages/{message_id}")
    public Message getMessage(@PathVariable int id){
        return null;
    }

    @DeleteMapping("/messages/{message_id}")
    public Message deleteMessage(@PathVariable int id){
        return null;
    }

    @PatchMapping("/messages/{message_id}")
    public Message updateMessage(@RequestBody Message message, @PathVariable int id){
        return null;
    }

    @GetMapping("/accounts/{account_id}/messages")
    public Message getUserMessages(@PathVariable int id){
        return null;
    }
}

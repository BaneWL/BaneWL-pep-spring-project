package com.example.controller;

import org.springframework.web.bind.annotation.*;

import com.example.entity.Account;
import com.example.entity.Message;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    @PostMapping("/register/")
    public Account registerAccount(@RequestBody Account account){
        return null;
    }

    @PostMapping("/login/")
    public Account loginAccount(@RequestBody Account account){
        return null;
    }

    @PostMapping("/messages/")
    public Message postMessage(@RequestBody Message message){
        return null;
    }

    @GetMapping("/messages/")
    public Message getMessages(){
        return null;
    }

    @GetMapping("/messages/{message_id}/")
    public Message getMessage(@PathVariable int id){
        return null;
    }

    @DeleteMapping("/messages/{message_id}/")
    public Message deleteMessage(@PathVariable int id){
        return null;
    }

    @PatchMapping("/messages/{message_id}/")
    public Message updatMessage(@RequestBody Message message, @PathVariable int id){
        return null;
    }

    @GetMapping("/accounts/{account_id}/messages/")
    public Message getUserMessage(@PathVariable int id){
        return null;
    }
}

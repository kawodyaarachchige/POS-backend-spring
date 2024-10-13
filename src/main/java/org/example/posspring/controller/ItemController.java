package org.example.posspring.controller;

import org.example.posspring.dto.impl.ItemDTO;
import org.example.posspring.exception.DataPersistException;
import org.example.posspring.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addItem(@RequestBody ItemDTO itemDto) {
        try{
            itemService.addItem(itemDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

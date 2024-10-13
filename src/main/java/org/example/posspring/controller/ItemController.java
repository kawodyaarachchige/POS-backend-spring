package org.example.posspring.controller;

import org.example.posspring.customstatuscode.SelectedItemCodes;
import org.example.posspring.dto.ItemStatus;
import org.example.posspring.dto.impl.ItemDTO;
import org.example.posspring.exception.DataPersistException;
import org.example.posspring.service.ItemService;
import org.example.posspring.util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping(value = "/{propertyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemStatus getItem(@PathVariable("propertyId") String propertyId){
        boolean isItemIdValid = Regex.ITEM_ID.validate(propertyId);
        if (isItemIdValid){
            return itemService.getItem(propertyId);
        }else{
            return new SelectedItemCodes(1, "Item Id Invalid");
        }
    }
}

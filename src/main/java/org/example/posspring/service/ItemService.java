package org.example.posspring.service;

import org.example.posspring.dto.ItemStatus;
import org.example.posspring.dto.impl.ItemDTO;

import java.util.List;

public interface ItemService {
    void addItem(ItemDTO itemDto);
    void updateItem(String item_id, ItemDTO itemDto);
    void deleteItem(String item_id);
    ItemStatus getItem(String item_id);
    List<ItemDTO> getAllItems();
}

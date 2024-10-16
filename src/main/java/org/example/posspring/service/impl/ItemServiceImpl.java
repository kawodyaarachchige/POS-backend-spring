package org.example.posspring.service.impl;

import org.example.posspring.controller.ItemController;
import org.example.posspring.customstatuscode.SelectedItemCodes;
import org.example.posspring.dao.ItemDao;
import org.example.posspring.dto.ItemStatus;
import org.example.posspring.dto.impl.ItemDTO;
import org.example.posspring.entity.impl.Item;
import org.example.posspring.exception.DataPersistException;
import org.example.posspring.exception.ItemNotFoundException;
import org.example.posspring.service.ItemService;
import org.example.posspring.util.AppUtil;
import org.example.posspring.util.Mapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;
    @Autowired
    private Mapping mapper;

    static Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Override
    public void addItem(ItemDTO itemDto) {
        itemDto.setItem_id(AppUtil.generateItemId());
        Item savedItem = itemDao.save(mapper.mapToItem(itemDto));
        if (savedItem == null) {
            logger.error("Failed to add item , Data Persist Exception occurred");
            throw new DataPersistException("Failed to add item");
        }

    }

    @Override
    public void updateItem(String item_id, ItemDTO itemDto) {
        Item fetchedItem = itemDao.getReferenceById(item_id);
        if (fetchedItem == null) {
            logger.error("Item not in database , Item not found exception occurred (Searching failed to retrieve :"+item_id+")");
            throw new ItemNotFoundException("Item not found");
        }
        fetchedItem.setDescription(itemDto.getDescription());
        fetchedItem.setQuantity(itemDto.getQuantity());
        fetchedItem.setPrice(itemDto.getPrice());
        itemDao.save(fetchedItem);


    }

    @Override
    public void deleteItem(String item_id) {
        Item fetchedItem = itemDao.getReferenceById(item_id);
        if (fetchedItem == null) {
            logger.error("Item not in database , Item not found exception occurred (Searching failed to retrieve :"+item_id+")");
            throw new ItemNotFoundException("Item not found");
        }
        itemDao.delete(fetchedItem);

    }

    @Override
    public ItemStatus getItem(String item_id) {
        Item fetchedItem = itemDao.getReferenceById(item_id);
        if (fetchedItem == null) {
            logger.error("Item not in database , Item not found exception occurred (Searching failed to retrieve :"+item_id+")");
            return new SelectedItemCodes(1, "Item not found");
        }
        return mapper.mapToItemDto(fetchedItem);
    }


    @Override
    public List<ItemDTO> getAllItems() {
        return mapper.mapToItemDtoList(itemDao.findAll());
    }
}

package com.github.TerryLight.LimonStand.service;

import com.github.TerryLight.LimonStand.domain.Item;
import com.github.TerryLight.LimonStand.repository.ItemRepository;

import java.util.List;

public class ItemService {
    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public List<Item> getAll(){
        return itemRepository.getAll();
    }
}

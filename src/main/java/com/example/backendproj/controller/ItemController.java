package com.example.backendproj.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.backendproj.entity.Item;
import com.example.backendproj.repository.ItemRepository;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemRepository repo;

    public ItemController(ItemRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Item addItem(@RequestBody Item item) {
        return repo.save(item);
    }

    @GetMapping
    public List<Item> getItems() {
        return repo.findAll();
    }
}

package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookRestController {

    @Autowired
    private BookService service;

    @GetMapping("/books")
    public List<Book> getList() {
        return service.getList();
    }

    @PostMapping("/books")
    public Book register(
            @RequestBody Book book
    ) {
        return service.register(book);
    }

}

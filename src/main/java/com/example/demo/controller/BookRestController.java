package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getById(
            @PathVariable Long id
    ) {
        try {
            return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Book not found with id" + id, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/books")
    public Book register(
            @RequestBody Book book
    ) {
        return service.register(book);
    }

}

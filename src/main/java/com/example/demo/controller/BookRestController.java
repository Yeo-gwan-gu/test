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
            return new ResponseEntity<>("Book not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/books")
    public Book register(
            @RequestBody Book book
    ) {
        return service.register(book);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Long id,
            @RequestBody Book requestBook
    ) {
        try {
            Book updatedBook = service.update(id, requestBook);
            return ResponseEntity.ok(updatedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(
            @PathVariable Long id
    ) {
        try {
            service.getByDelete(id);
            return ResponseEntity.ok("Deleted book with id: " + id);
        } catch (Exception e) {
            // 삭제 예외처리 미발동(리턴타입 확인 필요)
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete book with id: " + id + ".Error" + e.getMessage());
        }
    }

    @GetMapping("/books/{title}/{name}")
    public Book findTitleAndName(
            @PathVariable String title, @PathVariable String name
    ) {
        return service.findTitleAndName(title, name);
    }

}

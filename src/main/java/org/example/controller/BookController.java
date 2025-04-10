package org.example.controller;

import org.example.mysql.entity.Book;
import org.example.service.mysql.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody Book book)
    {
        Book savedBook=bookService.addBook(book);
        return ResponseEntity.ok(savedBook);
    }

    @PostMapping("/{id}/title")
    public int updateBookTitle(@PathVariable int id, @RequestBody Book book) {
        return bookService.updateBookTitle(id, book.getTitle());
    }

    @PatchMapping("partial/{id}/title")
    public ResponseEntity<Integer> updateBookTitle(@PathVariable int id, @RequestBody Map<String, String> fields) {
        String title = fields.get("title");
        if (title != null) {

            return ResponseEntity.ok(bookService.updateBookTitle(id, title));
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PutMapping("entity/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        // Fetch the book, update it with the new data, and save
        Book book = bookService.updatingEntity(id, updatedBook);
        return ResponseEntity.ok(book);
    }
}

package com.leopaul29.library.controller;

import com.leopaul29.library.exception.ResourceNotFoundException;
import com.leopaul29.library.model.Book;
import com.leopaul29.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    // Get All books
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Create a new Book
    @PostMapping("/book")
    public Book createBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

    // Get a Single Book
    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable(value = "id") Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
    }
}

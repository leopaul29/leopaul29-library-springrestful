package com.leopaul29.library.controller;

import com.leopaul29.library.exception.ResourceNotFoundException;
import com.leopaul29.library.model.Book;
import com.leopaul29.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/books")
    public Book createBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

    // Get a Single Book
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable(value = "id") Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
    }

    // Update a Book
    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable(value = "id") Long bookId,
                           @Valid @RequestBody Book bookDetails) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());

        Book updatedBook = bookRepository.save(book);
        return updatedBook;
    }

    // Delete a Book
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));

        bookRepository.delete(book);

        return ResponseEntity.ok().build();
    }
}

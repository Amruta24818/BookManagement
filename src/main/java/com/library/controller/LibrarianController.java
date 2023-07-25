package com.library.controller;

import com.library.model.Book;
import com.library.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class LibrarianController {

    @Autowired
    private IBookService bookService;

    @PostMapping("/add-book")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-book")
    public ResponseEntity<String> deleteBookById(@PathVariable int bookId){
        if(bookService.deleteById(bookId)){
            return new ResponseEntity<>("Book Deleted successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Book Deletion failed", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/find-book")
    public ResponseEntity<Book> findBook(@PathVariable String bookName){
        return new ResponseEntity<>(bookService.findBookByName(bookName), HttpStatus.CREATED);
    }
}

package com.library.controller;

import com.library.dto.AssignBookDto;
import com.library.model.Book;
import com.library.model.IssueRecord;
import com.library.service.IBookService;
import com.library.service.IIssueRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class LibrarianController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private IIssueRecordService iIssueRecordService;

    @PostMapping("/add-book")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-book/{bookId}")
    public ResponseEntity<String> deleteBookById(@PathVariable int bookId){
        if(bookService.deleteById(bookId)){
            return new ResponseEntity<>("Book Deleted successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Book Deletion failed", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/find-book/{bookName}")
    public ResponseEntity<Book> findBook(@PathVariable String bookName){
        return new ResponseEntity<>(bookService.findBookByName(bookName), HttpStatus.CREATED);
    }

    @PostMapping("/assign-book")
    public ResponseEntity<IssueRecord> assignBook(@RequestBody AssignBookDto assignBookDto){
        return new ResponseEntity<>(iIssueRecordService.assignBook(assignBookDto), HttpStatus.CREATED);
    }

    @PostMapping("/return-Book")
    public ResponseEntity<IssueRecord> returnBook(@RequestBody AssignBookDto assignBookDto){
        return new ResponseEntity<>(iIssueRecordService.returnBook(assignBookDto), HttpStatus.CREATED);
    }
}

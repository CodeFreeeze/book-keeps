package gloffice.controller;

import gloffice.dto.BookDto;
import gloffice.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> findbooks(@RequestParam(required=false) String title, @RequestParam(required=false) String author){

        if(StringUtils.isEmpty(title) && StringUtils.isEmpty(author)){
            return bookService.findbooks();
        }else if(StringUtils.isEmpty(title)){
            return bookService.findBooksByAuthor(author);
        }else if(StringUtils.isEmpty(author)){
            return bookService.findBooksByTitle(title);
        }else{
            return bookService.findBooksByTitleAndAuthor(title, author);
        }
    }
    @GetMapping("{id}")
    public BookDto findbookById(@PathVariable int id){
        return bookService.findBookById(id);
    }

    @PostMapping
    public void updatebook(@RequestBody BookDto book){
        bookService.updateBook(book);
    }

    @DeleteMapping("{id}")
    public void removeBook(@PathVariable int id){
        bookService.removeBook(id);
    }
    @DeleteMapping("/all")
    public void removeBook(){
        bookService.removeBookAll();
    }
}

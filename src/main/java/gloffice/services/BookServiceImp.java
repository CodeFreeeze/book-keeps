package gloffice.services;

import gloffice.domain.entity.Book;
import gloffice.dto.BookDto;
import gloffice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BookServiceImp implements  BookService {

    BookRepository bookRepository;

    @Autowired
    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto findBookById(int id) {
        if(bookRepository.exists(id)){
            return new BookDto(bookRepository.findOne(id));
        }
        return null;
    }

    @Override
    public List<BookDto> findbooks() {
        List<BookDto>  bookList = new ArrayList<>();
        bookRepository.findAll().forEach(book -> bookList.add( new BookDto(book)));

        return bookList;
    }

    @Override
    public List<BookDto> findBooksByAuthor(String authorName) {
        List<BookDto>  bookList = new ArrayList<>();
        bookRepository.findByAuthor(authorName).forEach(book -> bookList.add( new BookDto(book)));

        return bookList;
    }

    @Override
    public List<BookDto> findBooksByTitle(String title) {
        List<BookDto>  bookList = new ArrayList<>();
        bookRepository.findByTitle(title).forEach(book -> bookList.add( new BookDto(book)));
        return bookList;
    }

    @Override
    public List<BookDto> findBooksByTitleAndAuthor(String title, String author) {
        List<BookDto>  bookList = new ArrayList<>();
        bookRepository.findByTitleAndAuthor(title, author).
                forEach(book -> bookList.add( new BookDto(book)));
        return bookList;
    }

    @Override
    public void updateBook(BookDto bookDto) {
        bookRepository.save(new Book(bookDto));
    }

    @Override
    public void removeBook(int id) {
        bookRepository.delete(id);
    }

    @Override
    public void removeBookAll() {
        bookRepository.deleteAll();
    }
}

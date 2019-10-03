package gloffice.services;

import gloffice.dto.BookDto;

import java.util.List;

public interface BookService {

    BookDto findBookById(int id);
    List<BookDto> findbooks();
    List<BookDto> findBooksByAuthor(String authorName);
    List<BookDto> findBooksByTitle(String title);
    List<BookDto> findBooksByTitleAndAuthor(String title, String author);

    void updateBook(BookDto bookDto);
    void removeBook(int id);
    void removeBookAll();
}

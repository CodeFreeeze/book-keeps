package gloffice.repository;

import gloffice.domain.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository  extends CrudRepository<Book, Integer> {
    List<Book> findByAuthor(String author);
    List<Book> findByTitle(String title);
    List<Book> findByTitleAndAuthor(String title, String Author);

}

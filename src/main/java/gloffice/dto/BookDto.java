package gloffice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import gloffice.domain.entity.Book;
import lombok.Data;
import java.util.Date;

@Data
public class BookDto {
    private Integer id;
    private String title;
    private String author;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date dateCreated;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date dateModified;
    public BookDto(){

    }
    public BookDto(Book book){
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.dateCreated = book.getDateCreated();
        this.dateModified = book.getDateModified();
    }
    public BookDto(Integer id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
}

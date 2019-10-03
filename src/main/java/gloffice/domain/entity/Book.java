package gloffice.domain.entity;

import gloffice.dto.BookDto;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Entity
@Table(name = "book_detail")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "Title is required.")
    private String title;
    private String author;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created",nullable = false, updatable = false)
    private Date dateCreated;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_modified",nullable = false)
    private Date dateModified;

    public Book(){}
    public Book(BookDto bookDto){
        this.id = bookDto.getId();
        this.title = bookDto.getTitle();
        this.author = bookDto.getAuthor();
    }
    public Book(Integer id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
}

package ru.asayke.springcourse.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(min = 2, max = 100, message = "Название должно быть длиной от 2 до 100 символов")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Автор книги не должен быть пустым")
    @Size(min = 2, max = 100, message = "Автор должен быть длиной от 2 до 100 символов")
    @Column(name = "author")
    private String author;

    @Min(value = 1500, message = "Год выпуска должен быть не раньше, чем 1500-й год")
    @Column(name = "year")
    private int year;

    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    @Transient
    private boolean expired;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    public Book() {}

    public Book(String title, String author, int year){
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public int getYear() { return year; }

    public void setYear(int year) { this.year = year; }

    public Date getTakenAt() { return takenAt; }

    public void setTakenAt(Date takenAt) { this.takenAt = takenAt; }

    public boolean isExpired() { return expired; }

    public void setExpired(boolean expired) { this.expired = expired; }

    public Person getOwner() { return owner; }

    public void setOwner(Person owner) { this.owner = owner; }
}
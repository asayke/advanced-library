package ru.asayke.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.asayke.springcourse.models.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitleStartingWith(String startsWith);
}
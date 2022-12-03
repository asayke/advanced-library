package ru.asayke.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asayke.springcourse.models.Book;
import ru.asayke.springcourse.models.Person;
import ru.asayke.springcourse.repositories.BookRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BookRepository bookRepository;

    @Autowired
    public BooksService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findALl(boolean sortByYear){
        if(sortByYear)
            return bookRepository.findAll(Sort.by("year"));
        else
            return bookRepository.findAll();
    }

    public List<Book> findWithPagination(Integer page, Integer perPage, boolean sortByYear){
        if(sortByYear)
            return bookRepository.findAll(PageRequest.of(page, perPage, Sort.by("year"))).getContent();
        else
            return   bookRepository.findAll(PageRequest.of(page, perPage)).getContent();
    }

    public Book findOne(int id){
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> findAllByTitle(String title){
        return bookRepository.findByTitleStartingWith(title);
    }

    @Transactional
    public void save(Book book){
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook){
        Book bookToUpdate = bookRepository.findById(id).get();

        updatedBook.setId(id);
        updatedBook.setOwner(bookToUpdate.getOwner());

        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }

    public Person getBookOwner(int id){
        return bookRepository.findById(id).map(Book::getOwner).orElse(null);
    }

    @Transactional
    public void release(int id){
        bookRepository.findById(id).ifPresent(book -> {
            book.setOwner(null);
            book.setTakenAt(null);
        });
    }

    @Transactional
    public void assign(int id, Person selectedPerson){
        bookRepository.findById(id).ifPresent(book -> {
            book.setOwner(selectedPerson);
            book.setTakenAt(new Date());
        });
    }
}
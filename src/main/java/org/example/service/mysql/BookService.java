package org.example.service.mysql;

import jakarta.transaction.Transactional;
import org.example.mysql.entity.Book;
import org.example.mysql.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public Book addBook(Book book)
    {
          return bookRepository.save(book);
    }

    @Transactional
    public int updateBookTitle(int id, String title)
    {
         return bookRepository.updateBookTitle(id,title);
    }

    @Transactional
    public Book updatingEntity(int id, Book book)
    {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with id " + id));

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setGenre(book.getGenre());

        return bookRepository.save(existingBook);
    }
}

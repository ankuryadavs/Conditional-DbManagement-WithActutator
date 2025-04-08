package org.example.repo;

import jakarta.transaction.Transactional;
import org.example.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.title = :title WHERE b.id = :id")
    int updateBookTitle(@Param("id") int id, @Param("title") String title);
}

package pl.coderslab.Book_Reader_Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.Book_Reader_Backend.Entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	Book findOneById(long id);
}

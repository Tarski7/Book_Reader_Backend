package pl.coderslab.Book_Reader_Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.Book_Reader_Backend.Entity.Book;
import pl.coderslab.Book_Reader_Backend.repository.BookRepository;

@RestController
@RequestMapping("/books2")
public class BookController2 {

	@RequestMapping("/hello")
	public String hello() {
		return "{hello: World}";
	}
	
	@RequestMapping("/helloBook")
	public Book helloBook(){
		return new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel", "Helion", "programming");
	}
	
	@Autowired
	BookRepository bookRepository;
	
	@GetMapping("/")
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Book getBookById(@PathVariable long id) {	
		return bookRepository.findOneById(id);
	}
	
	@PostMapping("/")
	public Book addBook(@RequestBody Book book) {
		bookRepository.save(book);
		return new Book();
	}
	
	@PutMapping("/{id}")
	public Book editBook(@PathVariable int id, @RequestBody Book editedBook) {
		Book book = bookRepository.findOneById(id);
		book.setTitle(editedBook.getTitle());
		bookRepository.save(book);
		return new Book();	
	}
	
	@DeleteMapping("/{id}")
	public Book deleteBook(@PathVariable long id) {
		Book book = bookRepository.findOneById(id);
		bookRepository.delete(book);
		return new Book();
	}
}

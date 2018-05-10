package pl.coderslab.Book_Reader_Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.Book_Reader_Backend.Entity.Book;
import pl.coderslab.Book_Reader_Backend.Service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	@RequestMapping("/hello")
	public String hello() {
		return "{hello: World}";
	}
	
	@RequestMapping("/helloBook")
	public Book helloBook(){
		return new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel", "Helion", "programming");
	}
	
	@Autowired
	BookService bookService;
	
	@GetMapping("/")
	public List<Book> getBooks() {
		return bookService.getList();
	}
	
	@GetMapping("/{id}")
	public Book getBookById(@PathVariable long id) {	
		return bookService.getBookById(id);
	}
	
	@PostMapping("/")
	public Book addBook(@RequestBody Book book) {
		bookService.addBook(book);
		return new Book();
	}
	
	@RequestMapping("/editBook/{id}/{isbn}/{title}/{author}/{publisher}/{type}")
	public String editBook(@PathVariable int id, @PathVariable String isbn,
						   @PathVariable String title, @PathVariable String author,
						   @PathVariable String publisher, @PathVariable String type) {
		Book book = bookService.getBookById(id);
		int index = bookService.getList().indexOf(book);
		Book newBook = new Book(id, isbn, title, author, publisher, type);
		bookService.editBook(index, newBook);
		return "Book has been edited";
	}
	
	@DeleteMapping("/{id}")
	public String deleteBook(@PathVariable long id) {
		Book book = bookService.getBookById(id);
		bookService.deleteBook(book);
		return "Book has been deleted";
	}
}

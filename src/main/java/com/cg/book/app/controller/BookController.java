package com.cg.book.app.controller;

import java.awt.print.Book;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.cg.book.app.model.Book;
import com.cg.book.app.service.BookServiceImpl;
	
	@RestController
	@RequestMapping("/book")
	@CrossOrigin(origins = "*")
	// @CrossOrigin(origins = "http://localhost:3000")
	public class BookController {

		@Autowired
		private BookServiceImpl bookService;

		private final Logger logger = LoggerFactory.getLogger(this.getClass());

		// http://localhost:7777/book/get-all-books
		@GetMapping("/get-all-books")
		public ResponseEntity<List<Book>> getAllBooks() {
			List<Book> bookList = bookService.viewAllBook();
			for (Book bookTemp : bookList)
				logger.info(bookTemp.toString());
			HttpStatus status = HttpStatus.OK;
			ResponseEntity<List<Book>> response = new ResponseEntity<>(bookList, status);
			return response;
		}

		// http://localhost:7777/book/get-book-by-bookId/{bookId}
		@GetMapping("/get-book-by-bookId/{bookId}")
		public ResponseEntity<Book> getBookByBookId(@PathVariable(name = "bookId") int bookId) {
			logger.info(Integer.toString(bookId));
			Book book = bookService.viewBookById(bookId);
			HttpStatus status = HttpStatus.OK;
			ResponseEntity<Book> response = new ResponseEntity<>(book, status);
			return response;
		}

		// http://localhost:7777/book/get-book-by-bookName/{bookName}
		@GetMapping("/get-book-by-bookName/{bookName}")
		public ResponseEntity<List<Book>> getBookByBookName(@PathVariable(name = "bookName") String bookName) {
			logger.info(bookName);
			HttpStatus status = HttpStatus.OK;
			ResponseEntity<List<Book>> response = new ResponseEntity<>(bookService.getBookByName(bookName), status);
			return response;
		}

		// http://localhost:7777/book/add-book
		@PostMapping("/add-book")
		public ResponseEntity<Book> addBook(@RequestBody Book book) {
			logger.info(book.toString());
			HttpStatus status = HttpStatus.CREATED; // 201
			ResponseEntity<Book> response = new ResponseEntity<>(bookService.addBook(book), status);
			return response;
		}

		// http://localhost:7777/book/update-book
		@PutMapping("/update-book")
		public ResponseEntity<Book> updateBook(@RequestBody Book book) {
			logger.info(book.toString());
			HttpStatus status = HttpStatus.CREATED; // 201
			ResponseEntity<Book> response = new ResponseEntity<>(bookService.updateBook(book), status);
			return response;
		}

		// http://localhost:7777/book/delete-book
		@DeleteMapping("/delete-book/{bookId}")
		public ResponseEntity<Book> deleteBook(@PathVariable(name = "bookId") int bookId) {
			logger.info(Integer.toString(bookId));
			HttpStatus status = HttpStatus.OK;
			ResponseEntity<Book> response = new ResponseEntity<>(bookService.deleteBook(bookId), status);
			return response;
		}
	}
}
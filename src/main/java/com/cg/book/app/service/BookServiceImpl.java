package com.cg.book.app.service;

import java.awt.print.Book;
import java.util.List;

public interface BookServiceImpl {
	
	public List<Book> viewAllBook();

	public Book viewBookById(int bookId);

	public Book addBook(Book book);

	public Book updateBook(Book book);

	public Book deleteBook(int bookId);


}

package com.cg.book.app.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.book.app.model.Author;

//import com.cg.spring.boot.exception.AuthorNotFoundException;
//import com.cg.spring.boot.model.Author;
//import com.cg.spring.boot.repository.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	AuthorRepository authorRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<Author> getAllAuthors() {
		logger.info("getAllAuthors");
		return authorRepository.findAll();
	}

	public Author AuthorById(int authorId) {
		Optional<Department> depOptional = authorRepository.findById(authorId);
		Author author = null;
		if (authorOptional.isPresent()) {
			author = authorOptional.get();
			logger.info(author.toString());
			return author;
		} else {
			String errorMessage = "Author with authorId " + authorId + " does not exist.";
			logger.error(errorMessage);
			throw new AuthorNotFoundException(errorMessage);
		}
	}

	public Author addAuthor(Author author) {
		logger.info(author.toString());
		return authorRepository.save(author);
	}

}

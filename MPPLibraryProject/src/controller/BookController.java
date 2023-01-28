package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.Book;
import domain.exception.NewBookException;
import domain.exception.NewMemberException;
import usecase.SearchBookUseCase;
import usecase.BookUseCase;

public class BookController implements SearchBookUseCase,BookUseCase{
	BookController(){
	}
	
	@Override
	public Book searchBook(String isbn) {
		DataAccess da = new DataAccessFacade();
		HashMap<String,Book> map = da.readBooksMap();	
		return map.get(isbn);
	}

	@Override
	
	public void addBook(Book book) throws NewBookException {
		if(searchBook(book.getISBN()) == null) {
			DataAccess da = new DataAccessFacade();
			da.saveNewBook(book);	
		}else {
			throw new NewBookException("Book already exits");
		}
	}

	@Override
	public List<Book> getBookCollection() {
		DataAccess da = new DataAccessFacade();
		HashMap<String,Book> map= da.readBooksMap();
	
		List<Book> books = new ArrayList<>(map.values());
		return books;
	}

}

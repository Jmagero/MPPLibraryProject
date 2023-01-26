package controller;

import java.util.HashMap;

import domain.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import usecase.BookCopyUseCase;

public class AddBookCopyController implements BookCopyUseCase {
	AddBookCopyController() {
	}
	
	@Override
	public Book addBookCopy(Book book, int noOfCopies) {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> hmBooks = da.readBooksMap();
		
		if (hmBooks.containsKey(book.getISBN())) {
			Book bookFromDB = hmBooks.get(book.getISBN());
			
			for(int i=0; i<noOfCopies; i++) {
				bookFromDB.addBookCopy();
			}
			
			hmBooks.put(bookFromDB.getISBN(), bookFromDB);
			da.updateBookHM(hmBooks);
			return bookFromDB;
		} else {
			return null;
		}
	}
}

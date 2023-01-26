package dataaccess;

import java.util.HashMap;

import domain.Author;
import domain.Book;



public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String,Author> readAuthorMap();
	public void saveNewBook(Book book); 
	public void updateBookHM(HashMap<String, Book> hmBooks);
}

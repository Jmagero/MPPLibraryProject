package dataaccess;

import java.util.HashMap;

import domain.Author;
import domain.Book;
import domain.LibraryMember;


public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public HashMap<String,Author> readAuthorMap();
	public void saveNewMember(LibraryMember member);
	public void saveNewBook(Book book); 
	public void updateBookHM(HashMap<String, Book> hmBooks);
}

package domain;

import java.io.Serializable;

public class BookCopy implements Serializable {
	private static final long serialVersionUID = -178596484926377152L;
	private Book book;
	private int copyNum;
	private boolean isAvailable;
	public BookCopy(Book book, int copyNum, boolean isAvailable) {
		this.book = book;
		this.copyNum = copyNum;
		this.isAvailable = isAvailable;
	}
	
	BookCopy(Book book, int copyNum) {
		this.book = book;
		this.copyNum = copyNum;
	}
	
	
	public boolean isAvailable() {
		return isAvailable;
	}

	public int getCopyNum() {
		return copyNum;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void changeAvailability() {
		isAvailable = !isAvailable;
	}
	
	@Override
	public boolean equals(Object ob) {
		if(ob == null) return false;
		if(!(ob instanceof BookCopy)) return false;
		BookCopy copy = (BookCopy)ob;
		return copy.book.getISBN().equals(book.getISBN()) && copy.copyNum == copyNum;
	}


  
}

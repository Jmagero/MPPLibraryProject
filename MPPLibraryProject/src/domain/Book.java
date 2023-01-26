package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class Book implements Serializable {

private static final long serialVersionUID = -5363264426884438785L;
private String title;
   private String ISBN;
   private List<Author> authors;
   private BookCopy [] bookcopies;
   private int maxCheckoutLength;
public String getTitle() {
	return title;
}

public String getISBN() {
	return ISBN;
}

public int getMaxCheckoutLength() {
	return maxCheckoutLength;
}

public Book(String ISBN,String title,int maxCheckoutLength,List<Author> selectedAuthors) {
	super();
	this.title = title;
	this.ISBN = ISBN;
	this.maxCheckoutLength = maxCheckoutLength;
	this.authors = Collections.unmodifiableList(selectedAuthors);
	bookcopies = new BookCopy[]{new BookCopy(this, 1, true)};	
}

public List<Author> getAuthors() {
	return authors;
}


public void addBookCopy() {
	BookCopy[] newArr = new BookCopy[bookcopies.length + 1];
	System.arraycopy(bookcopies, 0, newArr, 0, bookcopies.length);
	newArr[bookcopies.length] = new BookCopy(this, bookcopies.length +1, true);
	bookcopies = newArr;
	
}

public void updateCopies(BookCopy copy) {
	for(int i = 0; i < bookcopies.length; ++i) {
		BookCopy c = bookcopies[i];
		if(c.equals(copy)) {
			bookcopies[i] = copy;
			
		}
	}
}

public List<Integer> getCopyNums() {
	List<Integer> retVal = new ArrayList<>();
	for(BookCopy c : bookcopies) {
		retVal.add(c.getCopyNum());
	}
	return retVal;
	
}

public boolean isAvailable() {
	if(bookcopies == null) {
		return false;
	}
	for(BookCopy bk : bookcopies) {
		if(bk.isAvailable() == true)
			return true;
	}
	return false;
}

@Override
public boolean equals(Object ob) {
	if(ob == null) return false;
	if(ob.getClass() != getClass()) return false;
	Book b = (Book)ob;
	return b.ISBN.equals(this.ISBN);
}
public BookCopy getNextAvailableCopy() {	
	Optional<BookCopy> optional 
		= Arrays.stream(bookcopies)
		        .filter(x -> x.isAvailable()).findFirst();
	return optional.isPresent() ? optional.get() : null;
}

public BookCopy getCopy(int copyNum) {
	for(BookCopy c : bookcopies) {
		if(copyNum == c.getCopyNum()) {
			return c;
		}
	}
	return null;
}

public String toString() {
	return "isbn: " + ISBN + ", title:" + title + ", maxLength: " + maxCheckoutLength + ", available: " + isAvailable();
}

public BookCopy[] getBookcopies() {
	return bookcopies;
}

public int getNumCopies() {
	// TODO Auto-generated method stub
	return bookcopies.length;
}
   
}

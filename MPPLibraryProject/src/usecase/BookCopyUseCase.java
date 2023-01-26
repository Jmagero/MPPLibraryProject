package usecase;

import domain.Book;

public interface BookCopyUseCase {
	public Book addBookCopy(Book book, int noOfCopies);

}

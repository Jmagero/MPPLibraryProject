package usecase;

import domain.Book;
import domain.exception.NewBookException;

public interface BookUseCase {

	void addBook(Book book) throws NewBookException;

}

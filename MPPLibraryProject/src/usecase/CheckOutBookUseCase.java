package usecase;

import domain.exception.BookCopyNotAvailableException;
import domain.exception.BookNotFoundException;
import domain.exception.MemberNotFoundException;

public interface CheckOutBookUseCase extends PrintCheckOutRecordUseCase {
	public void checkOutBook(String memberId, String bookId) throws BookNotFoundException, MemberNotFoundException, BookCopyNotAvailableException;
}

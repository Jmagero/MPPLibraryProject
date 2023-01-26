package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.Book;
import domain.BookCopy;
import domain.CheckOutRecord;
import domain.CheckOutRecordEntry;
import domain.LibraryMember;
import domain.exception.BookCopyNotAvailableException;
import domain.exception.BookNotFoundException;
import domain.exception.MemberNotFoundException;
import usecase.CheckMemberUseCase;
import usecase.CheckOutBookUseCase;
import usecase.SearchBookUseCase;

public class CheckOutBookController implements CheckOutBookUseCase {
	
	private SearchBookUseCase searchBookUseCase;
	private CheckMemberUseCase checkMember;
	
	CheckOutBookController() {
		searchBookUseCase = ControllerFactory.createSearchBookUseCase();
		checkMember = ControllerFactory.createCheckMemberUseCase();
	}

	@Override
	public void checkOutBook(String memberId, String bookId) throws BookNotFoundException, MemberNotFoundException, BookCopyNotAvailableException {
		
		DataAccess da = new DataAccessFacade();
		Book book = searchBookUseCase.searchBook(bookId);

		if (book == null) {
			throw new BookNotFoundException("Book not found");
		}

		if (!checkMember.checkMember(memberId)) {
			throw new MemberNotFoundException("Member not found");
		}	
		
		// Check Book Available
	 	BookCopy bookCopy = book.getNextAvailableCopy();
	 	
	 	if(bookCopy == null) {
	 		throw new BookCopyNotAvailableException(book.getISBN());
	 	}
	 	
	 	bookCopy.changeAvailability();
	 	
	 	LocalDate dueDate = LocalDate.now().plusDays(bookCopy.getBook().getMaxCheckoutLength());
	 	LocalDate checkOutDate = LocalDate.now();
	 	CheckOutRecordEntry checkOutEntry = new CheckOutRecordEntry(dueDate, checkOutDate, bookCopy);

	 	HashMap<String, CheckOutRecord> mapCheckOutRecord = da.readCheckOutRecordsMap();
	 	
	 	CheckOutRecord checkOutRecord = null;
	 	LibraryMember member = checkMember.getMember(memberId);
	 	
	 	if(mapCheckOutRecord.get(member.getMemberId()) != null) {
	 		checkOutRecord = mapCheckOutRecord.get(member.getMemberId()); 		
	 		checkOutRecord.addCheckOutRecordEntry(checkOutEntry);
	 	} else {
	 		ArrayList<CheckOutRecordEntry> list = new ArrayList<>();
	 		list.add(checkOutEntry);
	 		checkOutRecord = new CheckOutRecord(member, list);
	 	}
	 	
	 	// save checkoutrecord -> save to file
	 	mapCheckOutRecord.put(member.getMemberId(), checkOutRecord);
		da.saveCheckOutRecord(mapCheckOutRecord);
		
		// update book collection
		book.updateCopies(bookCopy);
		
		HashMap<String, Book> mapBooks = da.readBooksMap();
		Book bookFromDB = mapBooks.get(book.getISBN());
		
		mapBooks.put(bookFromDB.getISBN(), book);
		da.updateBookHM(mapBooks);
	}

	@Override
	public CheckOutRecord getCheckOutRecord(String memberId) {
		
		DataAccess da = new DataAccessFacade();
		HashMap<String, CheckOutRecord> hmCheckOutRecord = da.readCheckOutRecordsMap(); 	 	
	 	return hmCheckOutRecord.get(memberId); 	 		
	}
}

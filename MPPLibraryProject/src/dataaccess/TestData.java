package dataaccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.Address;
import domain.Author;
import domain.Book;
import domain.CheckOutRecord;
import domain.CheckOutRecordEntry;
import domain.LibraryMember;

/**
 * This class loads data into the data repository and also
 * sets up the storage units that are used in the application.
 * The main method in this class must be run once (and only
 * once) before the rest of the application can work properly.
 * It will create three serialized objects in the dataaccess.storage
 * folder.
 * 
 *
 */
public class TestData {
	public static void main(String[] args) {
		TestData td = new TestData();
		td.bookData();
		td.libraryMemberData();
		//td.userData();
		
		//Added by WinWin
		td.authorData();	
		td.checkOutRecordData();
		td.userData();
		
		td.authorData();
		
		//td.checkOutRecordData();
		
		DataAccess da = new DataAccessFacade();
		System.out.println(da.readBooksMap());
		System.out.println(da.readAuthorMap());
		System.out.println(da.readUserMap());
	}
	///create books
	public void bookData() {
		allBooks.get(0).addBookCopy();
		allBooks.get(0).addBookCopy();
		allBooks.get(1).addBookCopy();
		allBooks.get(3).addBookCopy();
		allBooks.get(2).addBookCopy();
		allBooks.get(2).addBookCopy();
		DataAccessFacade.loadBookMap(allBooks);
		
	}
	
	public void userData() {
		DataAccessFacade.loadUserMap(allUsers);
	}
	
	public void authorData() {
		DataAccessFacade.loadAuthorMap(allAuthors);
	}
	public void libraryMemberData() {
		LibraryMember libraryMember = new LibraryMember("1001", "Andy", "Rogers", "641-223-2211", addresses.get(4));
		members.add(libraryMember);
		libraryMember = new LibraryMember("1002", "Drew", "Stevens", "702-998-2414", addresses.get(5));
		members.add(libraryMember);
		
		libraryMember = new LibraryMember("1003", "Sarah", "Eagleton", "451-234-8811", addresses.get(6));
		members.add(libraryMember);
		
		libraryMember = new LibraryMember("1004", "Ricardo", "Montalbahn", "641-472-2871", addresses.get(7));
		members.add(libraryMember);
		
		DataAccessFacade.loadMemberMap(members);	
	}
	List<LibraryMember> members = new ArrayList<LibraryMember>();	
	
	public void checkOutRecordData() {
		LibraryMember libraryMember = new LibraryMember("1001", "Andy", "Rogers", "641-223-2211", addresses.get(4));
		List<CheckOutRecordEntry> list = new ArrayList<CheckOutRecordEntry>();
		CheckOutRecord checkOutRecord = new CheckOutRecord(libraryMember, list);			
		DataAccessFacade.loadCheckOutRecordMap(checkOutRecord);
	}
	
	List<Address> addresses = new ArrayList<Address>() {
		{
			add(new Address("101 S. Main", "Fairfield", "IA", "52556"));
			add(new Address("51 S. George", "Georgetown", "MI", "65434"));
			add(new Address("23 Headley Ave", "Seville", "Georgia", "41234"));
			add(new Address("1 N. Baton", "Baton Rouge", "LA", "33556"));
			add(new Address("5001 Venice Dr.", "Los Angeles", "CA", "93736"));
			add(new Address("1435 Channing Ave", "Palo Alto", "CA", "94301"));
			add(new Address("42 Dogwood Dr.", "Fairfield", "IA", "52556"));
			add(new Address("501 Central", "Mountain View", "CA", "94707"));
		}
	};
	
	//Added authorId by WinWin
	@SuppressWarnings("serial")
	public List<Author> allAuthors = new ArrayList<Author>() {
		{
			add(new Author("A101","Joe", "Thomas", "641-445-2123", addresses.get(0), "A happy man is he."));
			add(new Author("A102","Sandra", "Thomas", "641-445-2123", addresses.get(0), "A happy wife is she."));
			add(new Author("A103","Nirmal", "Pugh", "641-919-3223", addresses.get(1), "Thinker of thoughts."));
			add(new Author("A104","Andrew", "Cleveland", "976-445-2232", addresses.get(2), "Author of childrens' books."));
			add(new Author("A105","Sarah", "Connor", "123-422-2663", addresses.get(3), "Known for her clever style."));
		}
	};
	
	@SuppressWarnings("serial")
	List<Book> allBooks = new ArrayList<Book>() {
		{
			add(new Book("23-11451", "The Big Fish", 21, Arrays.asList(allAuthors.get(0), allAuthors.get(1))));
			add(new Book("28-12331", "Antartica", 7, Arrays.asList(allAuthors.get(2))));
			add(new Book("99-22223", "Thinking Java", 21, Arrays.asList(allAuthors.get(3))));
			add(new Book("48-56882", "Jimmy's First Day of School", 7, Arrays.asList(allAuthors.get(4))));		
		}
	};

	@SuppressWarnings("serial")
	List<User> allUsers = new ArrayList<User>() {
		{
			add(new User("101", "xyz", Auth.LIBRARIAN));
			add(new User("102", "abc", Auth.ADMIN));
			add(new User("103", "111", Auth.BOTH));
		}
	};
	
}

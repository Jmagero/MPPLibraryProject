package controller;

import usecase.AddMemberUseCase;
import usecase.LogInUseCase;
import usecase.PrintCheckOutRecordUseCase;
import usecase.BookCopyUseCase;
import usecase.BookUseCase;
import usecase.CalculateLateFeeUseCase;
import usecase.CheckMemberUseCase;
import usecase.CheckOutBookUseCase;
import usecase.GetAuthorUseCase;
import usecase.SearchBookUseCase;


public class ControllerFactory {
	private ControllerFactory() {
	}

	
	public static LogInUseCase createLogInUseCase() {
		LogInUseCase useCase = new LogInController();
		return useCase;

	}
	
	public static AddMemberUseCase createAddMemberUseCase() {
		AddMemberUseCase useCase = new AddMemberController();
		return useCase;

	}


	public static SearchBookUseCase createSearchBookUseCase() {
		SearchBookUseCase useCase = new BookController();
		return useCase;
	}

	public static BookUseCase createAddBookUseCase() {
		BookUseCase useCase = new BookController();
		return useCase;
	}

	public static BookCopyUseCase createBookCopyUseCase() {
		BookCopyUseCase useCase = new AddBookCopyController();
		return useCase;
	}

	public static GetAuthorUseCase createGetAuthorController() {
		GetAuthorUseCase useCase = new GetAuthorController();
		return useCase;
	}
	
	public static CheckMemberUseCase createCheckMemberUseCase() {
		CheckMemberUseCase useCase = new AddMemberController();
		return useCase;
	}
	
	public static CheckOutBookUseCase createCheckOutBookUseCase() {
		CheckOutBookUseCase useCase = new CheckOutBookController();
		return useCase;
	}
	
	public static PrintCheckOutRecordUseCase createPrintCheckOutBookUseCase() {
		PrintCheckOutRecordUseCase useCase = new CheckOutBookController();
		return useCase;
	}

	public static CalculateLateFeeUseCase createCalculateLateFeeUseCase() {
		CalculateLateFeeUseCase useCase = new CalculateLateFeeController();
		return useCase;
	}
}

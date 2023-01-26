package controller;

import usecase.BookCopyUseCase;
import usecase.BookUseCase;
import usecase.GetAuthorUseCase;
import usecase.SearchBookUseCase;

public class ControllerFactory {
	private ControllerFactory() {
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
}

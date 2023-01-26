package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.Author;

import usecase.GetAuthorUseCase;

public class GetAuthorController implements GetAuthorUseCase {

	@Override
	public List<Author> getAllAuthors() {
		DataAccess da = new DataAccessFacade();
		HashMap<String,Author> authors = da.readAuthorMap();
		if (authors==null) return null;
		return new ArrayList<>(authors.values());
	}

}

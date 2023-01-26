package usecase;

import dataaccess.Auth;
import domain.exception.LogInException;

public interface LogInUseCase {
	public Auth logIn(String id, String password) throws LogInException;
}

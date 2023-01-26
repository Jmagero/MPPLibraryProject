package controller;

import java.util.HashMap;

import dataaccess.Auth;
import usecase.LogInUseCase;
import domain.exception.LogInException;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;
public class LogInController implements LogInUseCase {
	
	LogInController() {
	}
	
	
	public static Auth currentAuth = null;

	@Override
	public Auth logIn(String id, String password) throws LogInException {
		// TODO Auto-generated method stub
		DataAccess db = new DataAccessFacade();
		HashMap<String, User> map = db.readUserMap();
		System.out.println(map);
		
		if (!map.containsKey(id)) {	
			throw new LogInException("User doesnot Exist.");
		}
		
		String pdw = map.get(id).getPassword();
		if (!pdw.equals(password)) {
			throw new LogInException("Incorrect Password. Please Try Again.");
		}
		
 		currentAuth = map.get(id).getAuthorization();
 		return currentAuth;
	}

}

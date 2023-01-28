package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.LibraryMember;
import domain.exception.NewMemberException;
import usecase.AddMemberUseCase;
import usecase.CheckMemberUseCase;

public class AddMemberController implements AddMemberUseCase, CheckMemberUseCase {

	@Override
	public void addNewMember(LibraryMember member) throws NewMemberException {
		DataAccess da = new DataAccessFacade();
		
		if (member == null) {
			throw new NewMemberException("Member is null");
		}
		
		if (member.getMemberId().isEmpty()) {
			throw new NewMemberException("Invalid Member Id");
		}
	
		if (member.getFirstName().isEmpty()) {
			throw new NewMemberException("Invalid First Name");
		}
		
		if (member.getLastName().isEmpty()) {
			throw new NewMemberException("Invalid Last Name");
		}
		
		if (checkMember(member.getMemberId())) {
			throw new NewMemberException("Member ID already exist");
		}
		
		da.saveNewMember(member);
		
	}
	
	@Override
	public boolean checkMember(String memberId) {
		if (getMember(memberId) != null)
			return true;
		
		return false;
				
	}
	
	@Override
	public LibraryMember getMember(String memberId) {
		DataAccess da = new DataAccessFacade();
		HashMap<String,LibraryMember> map = da.readMemberMap();
		return map.get(memberId);
	}

	@Override
	
	
	public List<LibraryMember> getMemberList() {
		DataAccess da = new DataAccessFacade();
		HashMap<String,LibraryMember> map= da.readMemberMap();
	
		List<LibraryMember> members = new ArrayList<>(map.values());
		return members;
	}


}

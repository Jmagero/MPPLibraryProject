package usecase;

import java.util.List;

import domain.LibraryMember;
import domain.exception.NewMemberException;

public interface AddMemberUseCase {
	public void addNewMember(LibraryMember member) throws NewMemberException;
	
	public List<LibraryMember> getMemberList() throws NewMemberException;

}

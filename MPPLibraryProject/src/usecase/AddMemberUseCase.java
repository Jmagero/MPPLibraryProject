package usecase;

import domain.LibraryMember;
import domain.exception.NewMemberException;

public interface AddMemberUseCase {
	public void addNewMember(LibraryMember member) throws NewMemberException;

}

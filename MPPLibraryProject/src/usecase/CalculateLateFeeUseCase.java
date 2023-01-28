package usecase;

import domain.CheckOutRecord;

public interface CalculateLateFeeUseCase {
	
	public CheckOutRecord getCheckOutRecordByMember(String memberId);

}

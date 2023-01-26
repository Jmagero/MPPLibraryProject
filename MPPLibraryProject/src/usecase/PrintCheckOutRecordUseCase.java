package usecase;

import domain.CheckOutRecord;

public interface PrintCheckOutRecordUseCase {
	public CheckOutRecord getCheckOutRecord(String memberId);
}

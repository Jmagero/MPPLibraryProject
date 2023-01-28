package controller;

import java.util.HashMap;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.CheckOutRecord;
import usecase.CalculateLateFeeUseCase;

public class CalculateLateFeeController implements CalculateLateFeeUseCase {

	@Override
	public CheckOutRecord getCheckOutRecordByMember(String memberId) {
		DataAccess da = new DataAccessFacade();
		HashMap<String, CheckOutRecord> hmCheckOutRecord = da.readCheckOutRecordsMap(); 	 	
	 	return hmCheckOutRecord.get(memberId); 	 	
	}

}
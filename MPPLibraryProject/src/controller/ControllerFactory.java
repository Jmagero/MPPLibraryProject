package controller;

import usecase.AddMemberUseCase;
import usecase.LogInUseCase;

public class ControllerFactory {
	private ControllerFactory() {
	}
	
	public static LogInUseCase createLogInUseCase() {
		LogInUseCase useCase = new LogInController();
		return useCase;

	}
	
	public static AddMemberUseCase createAddMemberUseCase() {
		AddMemberUseCase useCase = new AddMemberController();
		return useCase;

	}

}

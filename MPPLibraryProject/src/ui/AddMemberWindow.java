package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ControllerFactory;
import domain.Address;
import domain.LibraryMember;
import domain.exception.NewMemberException;
import usecase.AddMemberUseCase;
import util.Util;

public class AddMemberWindow extends JFrame implements SystemWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3942721083207814580L;
	public static final AddMemberWindow UI = new AddMemberWindow();
	AddMemberUseCase addLibraryMemberUseCase = ControllerFactory.createAddMemberUseCase();
	
	private boolean isInitialized = false;
	
	private JPanel mainPanel = new JPanel();
	private JPanel topPanel = new JPanel();
	private JPanel outerMiddel = new JPanel();
	private JPanel lowerPanel;
	
	private JTextField txtMemberId;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtPhoneNumber;
	private JTextField txtStreet;
	private JTextField txtCity;
	private JTextField txtZipCode;
	private JTextField txtState;
	
	private JLabel lblMemberId;
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblPhoneNumber;
	private JLabel lblStreet;
	private JLabel lblCity;
	private JLabel lblZipCode;
	private JLabel lblState;
	
	private AddMemberWindow() {}
	

	@Override
	public void init() {
		initializeComponent();
		mainPanel.setLayout(new BorderLayout());
		defineTopPanel();
		defineOuterMiddle();
		defineLowerPanel();
		
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(outerMiddel, BorderLayout.CENTER);
		mainPanel.add(lowerPanel, BorderLayout.SOUTH);
		getContentPane().add(mainPanel);
		isInitialized = true;
		
	}
	
	private void initializeComponent() {
		txtMemberId = new JTextField(20);
		txtFirstName = new JTextField(20);
		txtLastName = new JTextField(20);
		txtPhoneNumber = new JTextField(15);
		txtStreet = new JTextField(20);
		txtCity = new JTextField(20);
		txtZipCode = new JTextField(20);
		txtState = new JTextField(20);
		
		lblMemberId = new JLabel("Member ID");
		lblFirstName = new JLabel("First Name");
		lblLastName = new JLabel("Last Name");
		lblPhoneNumber = new JLabel("Phone Number");
		lblStreet = new JLabel("Street");
		lblCity = new JLabel("City");
		lblZipCode = new JLabel("Zip Code");
		lblState = new JLabel("State");
		
	}


	private void defineTopPanel() {
		topPanel = new JPanel();
		JLabel addMemberLabel = new JLabel("Add New Library Member");
		Util.adjustLabelFont(addMemberLabel, Util.DARK_BLUE, true);
		topPanel.setLayout(new FlowLayout(0));
		topPanel.add(addMemberLabel);
		
	}

	private void defineOuterMiddle() {
		outerMiddel = new JPanel();
		outerMiddel.setLayout(new BorderLayout());
		
		JPanel middlePanel = new JPanel();
		FlowLayout fl = new FlowLayout(1, 25, 25);
		
		middlePanel.setLayout(fl);
		
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		
		leftPanel.setLayout(new BoxLayout(leftPanel, 1));
		rightPanel.setLayout(new BoxLayout(rightPanel, 1));
		
		leftPanel.add(lblMemberId);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		
		leftPanel.add(lblFirstName);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		
		leftPanel.add(lblLastName);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		
		leftPanel.add(lblPhoneNumber);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		
		leftPanel.add(lblStreet);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		
		leftPanel.add(lblCity);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		
		leftPanel.add(lblZipCode);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		
		leftPanel.add(lblState);
		
		rightPanel.add(txtMemberId);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		rightPanel.add(txtFirstName);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		rightPanel.add(txtLastName);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		rightPanel.add(txtPhoneNumber);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		rightPanel.add(txtStreet);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		rightPanel.add(txtCity);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		rightPanel.add(txtZipCode);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		rightPanel.add(txtState);
		
		middlePanel.add(leftPanel);
		middlePanel.add(rightPanel);
		
		outerMiddel.add(middlePanel, BorderLayout.NORTH);
		
	}

	private void defineLowerPanel() {
		
		lowerPanel = new JPanel();
		lowerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));;
		
		JButton btnBackToMain = new JButton("<= Back to Main");
		btnBackToMain.addActionListener(new BackToMainListener());
		btnBackToMain.setFocusPainted(false);
		lowerPanel.add(btnBackToMain);
		
		JButton btnAddMember = new JButton(" Submit ");
		btnAddMember.addActionListener((evt) -> {
			if (validateForm()) {
				try {
					addLibraryMemberUseCase.addNewMember(createMemberObject());
					clearForm();
					JOptionPane.showMessageDialog(this, "New Member added successfully.");
				} catch (NewMemberException e) {
					JOptionPane.showMessageDialog(this, e.getMessage());
					e.printStackTrace();
				}
			}
		});
		btnAddMember.setFocusPainted(false);
		lowerPanel.add(btnAddMember);
		
		JButton btnClear = new JButton(" Cancel ");
		btnClear.addActionListener(e -> { this.clearForm();});
		btnClear.setFocusPainted(false);
		lowerPanel.add(btnClear);
		
	}


	private boolean validateForm() {
		if (txtMemberId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter Member ID");
			return false;
		}
		
		if (txtFirstName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter First Name");
			return false;
		}
		
		if (txtLastName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter Last Name");
			return false;
		}
		
		if (txtPhoneNumber.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter Phone Number");
			return false;
		}

		
		return true;
	}

	private LibraryMember createMemberObject () {
		Address address = new Address(txtStreet.getText(), txtCity.getText(), txtState.getText(), txtZipCode.getText());
		LibraryMember member = new LibraryMember(txtMemberId.getText(), txtFirstName.getText(), txtLastName.getText(), txtPhoneNumber.getText(), address);
		return member;
	}
	
	private void clearForm() {
		System.out.println("In clear form");
		txtStreet.setText("");
		txtCity.setText("");
		txtState.setText("");
		txtZipCode.setText("");
		txtMemberId.setText("");
		txtFirstName.setText("");
		txtLastName.setText("");
		txtPhoneNumber.setText("");
		
	}
	
	class BackToMainListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			LibrarySystem.hideAllWindows();
			LibrarySystem.UI.setVisible(true);
		}
	}

	@Override
	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		// TODO Auto-generated method stub
		isInitialized = val;
		
	}

}

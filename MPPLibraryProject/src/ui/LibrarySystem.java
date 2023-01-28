package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import dataaccess.Auth;
import ui.LibrarySystem;
import usecase.PrintCheckOutRecordUseCase;
import util.Util;


public class LibrarySystem extends JFrame implements SystemWindow{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4988174279150633755L;
	public final static LibrarySystem UI = new LibrarySystem();
	private boolean isInitialized = false;
	
	private JPanel mainPanel;
	
	private JPanel TopPanel;
	private JPanel CenterPanel;
	
	private JPanel AdminPanel;
	private JPanel LibrarianPanel;
	private JPanel LogInPanel;
	
	private JButton btnAddMember, btnAddBook, btnAddBookCopy;
	private JButton btnCheckOutBook, btnPrintCheckOutRecord, btnCalculateLateFee;
	private JButton btnLogIn;
	
	private static SystemWindow[] allWindows = { 
			LibrarySystem.UI, 
			LogInWindow.UI,
			AddMemberWindow.UI,
			CheckoutBookWindow.UI,
			PrintCheckOutRecordWindow.UI,
			BookWindow.UI, 
			BookCopyWindow.UI};
	
	private LibrarySystem() {
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		getContentPane().add(mainPanel);
		
		createTopPanel(); // logo title
		createCenterPanel(); // buttons
		
		showOnlyLogIn();
		
		setSize(660, 500);
		isInitialized = true;
	}

	private void createTopPanel() {

		JPanel innerPanel = new JPanel(new BorderLayout());

		JLabel space  =  new JLabel(" \n \n ");
		innerPanel.add(space,BorderLayout.CENTER);
		JLabel space2  =  new JLabel(" \n \n ");
		innerPanel.add(space2,BorderLayout.SOUTH);
		
		TopPanel  = new JPanel(new FlowLayout());
		ImageIcon image = new ImageIcon(System.getProperty("user.dir")+ "\\MPPLibraryProject\\src\\ui\\MIULogo.png");
		TopPanel.add(new JLabel(image));
		JLabel title = new JLabel("MAHARISHI LIBRARY SYSTEM");
		title.setFont(new Font("Times New Roman", Font.BOLD, 19));
		title.setForeground(new Color(0, 128, 255));
		TopPanel.add(title);
		
		innerPanel.add(TopPanel,BorderLayout.NORTH);
		
		getContentPane().add(TopPanel, BorderLayout.NORTH);
		
	}

	private void createCenterPanel() {
		CenterPanel = new JPanel();
		CenterPanel.setLayout(new BorderLayout());
		
		
		createAdminPanel();
		createLibrarianPanel();
		createLogInPanel();
		
		getContentPane().add(CenterPanel, BorderLayout.CENTER);
	}

	private void createAdminPanel() {
		
		JPanel innerPanel = new JPanel(new BorderLayout());
		
		JLabel space  =  new JLabel("  ");
		innerPanel.add(space,BorderLayout.NORTH);
		
		JLabel space2  =  new JLabel("  ");
		innerPanel.add(space,BorderLayout.CENTER);
		
		
		AdminPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		btnAddMember = new JButton("Add Member");
		btnAddBook = new JButton("Add Book");
		btnAddBookCopy = new JButton("Add Book Copy");
		btnAddMember.setFocusPainted(false);
		btnAddBook.setFocusPainted(false);
		btnAddBookCopy.setFocusPainted(false);
		
		btnAddMember.addActionListener(new AddMemberListener());
		btnAddBook.addActionListener(new AddBookListener());
		btnAddBookCopy.addActionListener(new AddBookCopyListener());
		
		AdminPanel.add(btnAddMember);
		AdminPanel.add(btnAddBook);
		AdminPanel.add(btnAddBookCopy);
		
		innerPanel.add(AdminPanel,BorderLayout.SOUTH);
		CenterPanel.add(innerPanel, BorderLayout.NORTH);
	}

	private void createLibrarianPanel() {
		
		JPanel innerPanel = new JPanel(new BorderLayout());

		JLabel space  =  new JLabel(" \n \n ");
		innerPanel.add(space,BorderLayout.NORTH);
		
		
		LibrarianPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnCheckOutBook = new JButton("Checkout Book");
		btnPrintCheckOutRecord = new JButton("Print Checkout Record");
		btnCalculateLateFee = new JButton("Calculate Late Fee");
		btnCheckOutBook.setFocusPainted(false);
		btnPrintCheckOutRecord.setFocusPainted(false);
		btnCalculateLateFee.setFocusPainted(false);
		
		btnCheckOutBook.addActionListener(new CheckOutBookListener());
		btnPrintCheckOutRecord.addActionListener(new PrintCheckOutRecordListener());
		btnCalculateLateFee.addActionListener(new CalculateLateFeeListener());
		
		LibrarianPanel.add(btnCheckOutBook);
		LibrarianPanel.add(btnPrintCheckOutRecord);
		LibrarianPanel.add(btnCalculateLateFee);
		innerPanel.add(LibrarianPanel,BorderLayout.CENTER);
		
		CenterPanel.add(innerPanel, BorderLayout.CENTER);
	}

	private void createLogInPanel() {
		
		JPanel innerPanel = new JPanel(new BorderLayout());
		
		JLabel space  =  new JLabel(" \n \n ");
		innerPanel.add(space,BorderLayout.SOUTH);
		
		LogInPanel = new JPanel();
		LogInPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		btnLogIn = new JButton("Log In");
		btnLogIn.setFocusPainted(false);
		btnLogIn.addActionListener(new LogInListener());
		
		LogInPanel.add(btnLogIn);
		
		innerPanel.add(LogInPanel,BorderLayout.NORTH);
		
		
		
		CenterPanel.add(innerPanel, BorderLayout.SOUTH);
		
	}

	class LogInListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (btnLogIn.getText().equals("Log In")) {
				if(!LogInWindow.UI.isInitialized()) {
					
					LibrarySystem.hideAllWindows();
					LogInWindow.UI.init();
					LogInWindow.UI.revalidate();
					Util.centerFrameOnDesktop(LogInWindow.UI);
					LogInWindow.UI.setVisible(true);
				}
				else {
					LibrarySystem.hideAllWindows();
					LogInWindow.UI.setVisible(true);
					
				}
			} else {
				showOnlyLogIn();
			}
			
		}

	}

	class AddMemberListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!AddMemberWindow.UI.isInitialized()) {
				
				LibrarySystem.hideAllWindows();
				AddMemberWindow.UI.init();
				AddMemberWindow.UI.pack();
				Util.centerFrameOnDesktop(AddMemberWindow.UI);
				AddMemberWindow.UI.setVisible(true);
			}
			else {
				LibrarySystem.hideAllWindows();
				AddMemberWindow.UI.setVisible(true);
				
			}
		}

	}
	
	class CheckOutBookListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!CheckoutBookWindow.UI.isInitialized()) {
				LibrarySystem.hideAllWindows();
				CheckoutBookWindow.UI.init();
				CheckoutBookWindow.UI.pack();
				Util.centerFrameOnDesktop(CheckoutBookWindow.UI);
				CheckoutBookWindow.UI.setVisible(true);
			}
			else {
				LibrarySystem.hideAllWindows();
				CheckoutBookWindow.UI.setVisible(true);		
				CheckoutBookWindow.UI.clearForm();		
			}
		}
	}
	
	class PrintCheckOutRecordListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!PrintCheckOutRecordWindow.UI.isInitialized()) {
				
				LibrarySystem.hideAllWindows();
				PrintCheckOutRecordWindow.UI.init();
				PrintCheckOutRecordWindow.UI.pack();
				Util.centerFrameOnDesktop(PrintCheckOutRecordWindow.UI);
				PrintCheckOutRecordWindow.UI.setVisible(true);
			}
			else {
				
				LibrarySystem.hideAllWindows();
				PrintCheckOutRecordWindow.UI.setVisible(true);
				PrintCheckOutRecordWindow.UI.clearForm();
			}

		}
	}
	
	class AddBookCopyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!BookCopyWindow.UI.isInitialized()) {
				
				LibrarySystem.hideAllWindows();
				BookCopyWindow.UI.init();
				BookCopyWindow.UI.pack();
				Util.centerFrameOnDesktop(BookCopyWindow.UI);
				BookCopyWindow.UI.setVisible(true);
			}else {
				LibrarySystem.hideAllWindows();
				BookCopyWindow.UI.setVisible(true);
				
			}

		}

	}

	class AddBookListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!BookWindow.UI.isInitialized()) {
				
				LibrarySystem.hideAllWindows();
				BookWindow.UI.init();
				BookWindow.UI.pack();
				Util.centerFrameOnDesktop(BookWindow.UI);
				BookWindow.UI.setVisible(true);
			}else {
				
				LibrarySystem.hideAllWindows();
				BookWindow.UI.setVisible(true);
			}
		}

	}
	
	class CalculateLateFeeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!CalculateLateFeeWindow.UI.isInitialized()) {
				
				LibrarySystem.hideAllWindows();
				CalculateLateFeeWindow.UI.init();
				CalculateLateFeeWindow.UI.pack();
				Util.centerFrameOnDesktop(CalculateLateFeeWindow.UI);
				CalculateLateFeeWindow.UI.setVisible(true);
			}
			else {
				
				LibrarySystem.hideAllWindows();
				CalculateLateFeeWindow.UI.setVisible(true);
			}
			
		}
	}

	public static void hideAllWindows() {

		for (SystemWindow frame : allWindows) {
			frame.setVisible(false);

		}
		//LibrarySystem.UI.dispose();
		
	}
	
	public void giveAuthority(Auth auth) {
		
		if (auth == Auth.ADMIN) {
			adminAuthority();
		} else if (auth == Auth.LIBRARIAN) {
			librarianAuthority();
		} else if (auth == Auth.BOTH) {
			fullAuthority();
		}
		
		enbleLogOut();
		
	}
	
	private void adminAuthority() {
		// TODO Auto-generated method stub
		showAdminPanel();
		hideLibrarianPanel();
		
	}
	
	private void librarianAuthority() {
		// TODO Auto-generated method stub
		hideAdminPanel();
		showLibrarianPanel();
		
	}

	
	private void fullAuthority() {
		// TODO Auto-generated method stub
		showAdminPanel();
		showLibrarianPanel();
		
	}
	
	private void showOnlyLogIn() {
		this.btnLogIn.setText("Log In");
		
		hideAdminPanel();
		hideLibrarianPanel();
		
		mainPanel.revalidate();
		mainPanel.repaint();
	}


	public void enbleLogOut() {
		this.btnLogIn.setText("Log Out");
	}
	
	public void hideAdminPanel() {
		btnAddBook.setVisible(false);
		btnAddBookCopy.setVisible(false);
		btnAddMember.setVisible(false);
		AdminPanel.setVisible(false);
	}
	
	public void hideLibrarianPanel() {
		btnCheckOutBook.setVisible(false);
		btnPrintCheckOutRecord.setVisible(false);
		btnCalculateLateFee.setVisible(false);
		LibrarianPanel.setVisible(false);
		
	}
	public void showAdminPanel() {
		btnAddBook.setVisible(true);
		btnAddBookCopy.setVisible(true);
		btnAddMember.setVisible(true);
		AdminPanel.setVisible(true);
	}
	
	public void showLibrarianPanel() {
		btnCheckOutBook.setVisible(true);
		btnPrintCheckOutRecord.setVisible(true);
		btnCalculateLateFee.setVisible(true);
		LibrarianPanel.setVisible(true);
		
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

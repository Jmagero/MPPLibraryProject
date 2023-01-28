package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.Period;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.ControllerFactory;
import domain.CheckOutRecord;
import domain.CheckOutRecordEntry;
import usecase.CalculateLateFeeUseCase;

public class CalculateLateFeeWindow extends JFrame implements SystemWindow {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5187174704136540512L;
	private boolean isInitialized = false;
	public static final CalculateLateFeeWindow UI = new CalculateLateFeeWindow();
	
	CalculateLateFeeUseCase calculateOperation = ControllerFactory.createCalculateLateFeeUseCase();
	private CalculateLateFeeWindow() {}
	
	private JPanel mainPanel;
	private JPanel upperHalf;
	private JPanel middleHalf;
	private JPanel lowerHalf;
	
	JTextField txtMemberID;
	JTable jt;
	DefaultTableModel jtmodel = new DefaultTableModel();
	

	@Override
	public void init() {
		// TODO Auto-generated method stub
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		this.setTitle("Late Book Return");
		this.setMinimumSize(new Dimension(660, 300));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		defineUpperHalf();
		defineMiddleHalf();
		defineLowerHalf();
		
		mainPanel.add(upperHalf, BorderLayout.NORTH);
		mainPanel.add(middleHalf, BorderLayout.CENTER);
		mainPanel.add(lowerHalf, BorderLayout.SOUTH);
		
		getContentPane().add(mainPanel);
		isInitialized = true;		
		
	}

	private void defineUpperHalf() {
		upperHalf = new JPanel();
		upperHalf.setLayout(new BorderLayout());
		
		defineTextField();
		defineButtons();
		
		
	}
	
	private void defineTextField() {
		JPanel middlePanel = new JPanel();
		FlowLayout fl = new FlowLayout(1, 25, 25);
		
		middlePanel.setLayout(fl);
		
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		
		leftPanel.setLayout(new BoxLayout(leftPanel, 1));
		rightPanel.setLayout(new BoxLayout(rightPanel, 1));
		
		JLabel lblMemberId = new JLabel("Member ID");
		leftPanel.add(lblMemberId);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		
		txtMemberID = new JTextField(20);
		rightPanel.add(txtMemberID);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		middlePanel.add(leftPanel);
		middlePanel.add(rightPanel);
		
		upperHalf.add(middlePanel, BorderLayout.NORTH);
	}
	
	private void defineButtons() {

		JPanel lowerPanel = new JPanel();
		lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton btnBackToMain = new JButton("<< Back to Main");
		addBackButtonListener(btnBackToMain);
		btnBackToMain.setFocusPainted(false);
		lowerPanel.add(btnBackToMain);
		
		JButton btnSearch = new JButton(" Search ");
		addCheckIDListener(btnSearch);
		btnSearch.setFocusPainted(false);
		lowerPanel.add(btnSearch);
		
		JButton btnClear = new JButton(" Clear ");
		btnClear.addActionListener(e -> { this.clear();});
		btnClear.setFocusPainted(false);
		lowerPanel.add(btnClear);
		
		upperHalf.add(lowerPanel, BorderLayout.SOUTH);
	}

	private void defineMiddleHalf() {
		middleHalf = new JPanel();
		middleHalf.setLayout(new BorderLayout());
		
		jtmodel.addColumn("Member Id");
		jtmodel.addColumn("Member Name");
		jtmodel.addColumn("ISBN");
		jtmodel.addColumn("Book Name");
		jtmodel.addColumn("Due Date");
		jtmodel.addColumn("No of Due Days");
		jtmodel.addColumn("Late Fee");


		jt = new JTable(jtmodel);

		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(20, 200, 800, 150);
		middleHalf.add(sp);

		
	}

	private void defineLowerHalf() {
		lowerHalf = new JPanel();
		
	}

	
	private void addBackButtonListener(JButton btnBackToMain) {
		btnBackToMain.addActionListener(evt -> {
			backToMain();
		});
	}	
	private void backToMain() {
		this.clear();
		LibrarySystem.hideAllWindows();
		LibrarySystem.UI.setVisible(true);
	}
	public void clear() {
		this.txtMemberID.setText("");
		jtmodel.setRowCount(0);
	}
	
	private void addCheckIDListener(JButton btnSearch) {
		btnSearch.addActionListener(evt -> {
			
			
			String memberID = this.txtMemberID.getText();
			if (memberID.length() == 0) {
				JOptionPane.showMessageDialog(this, "Enter Member ID.", "Search Failed",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			else {
				try {
					displayCheckoutInfo();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Search Failed!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}	
	
	private void displayCheckoutInfo() {
		CheckOutRecord cr = calculateOperation.getCheckOutRecordByMember(txtMemberID.getText());
		if (cr == null) {
			jtmodel.setRowCount(0);
			jtmodel.addRow(new Object[] {"" , "","","No Records", "","",""});
			//JOptionPane.showMessageDialog(this, "There is no checkout record.");
			return;
		}

		//DefaultTableModel model2 = (DefaultTableModel) jt.getModel();
		jtmodel.setRowCount(0);
		int dueBook = 0;	
		int totalDueDays = 0;
		for (CheckOutRecordEntry entry : cr.getCheckOutRecordEntries()) 
		{
			
			LocalDate today = LocalDate.now();
			Period p = entry.getDueDate().until(today);
			int days = p.getDays();
			System.out.println("due days : "+ days);
			if(days > 0) {
				dueBook++;
				totalDueDays += days;
				System.out.println("due book : "+ dueBook+ "total day : "+ totalDueDays);
				jtmodel.addRow(new Object[] { cr.getMember().getMemberId(), cr.getMember().getFullName(),
						entry.getBookCopy().getBook().getISBN(),
						entry.getBookCopy().getBook().getTitle(), 
						entry.getDueDate().toString(),days, "$ " +(days*0.25) });
				
			}
			else {
				jtmodel.addRow(new Object[] { cr.getMember().getMemberId(), cr.getMember().getFullName(),
						entry.getBookCopy().getBook().getISBN(),
						entry.getBookCopy().getBook().getTitle(), 
						entry.getDueDate().toString(),0,"$ 0.0"});
			}

		}
//		if(dueBook <= 0) {
//			//JOptionPane.showMessageDialog(this, "This Member does not have any due book");
//			jtmodel.addRow(new Object[] {"" , "","","", "","Total Late Fee :","$0"});
//		}
//		else {
//			//JOptionPane.showMessageDialog(this, "Total Late Fee : $ "+ (totalDueDays*0.25));
//			
//		}
		jtmodel.addRow(new Object[] {"" , "","","", "","Total Late Fee","$ "+ (totalDueDays*0.25)});
		
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
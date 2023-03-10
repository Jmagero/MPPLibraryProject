package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.ControllerFactory;
import domain.BookCopy;
import domain.CheckOutRecord;
import domain.CheckOutRecordEntry;
import domain.exception.BookCopyNotAvailableException;
import domain.exception.BookNotFoundException;
import domain.exception.MemberNotFoundException;
import usecase.CheckOutBookUseCase;

public class CheckoutBookWindow extends JFrame implements SystemWindow {

    private static final long serialVersionUID = 1L;
    public static final CheckoutBookWindow UI = new CheckoutBookWindow();

    private CheckoutBookWindow() {}

    CheckOutBookUseCase checkOutBookUseCase = ControllerFactory.createCheckOutBookUseCase();
    private boolean isInitialized = false;

    JTextField txtISBN, txtMemberID;
    JComboBox<BookCopy> cmbCopies;
    JTable jt;
    JPanel panelCheckoutFields;
    JScrollPane sp;
    DefaultTableModel model2;

    public void checkOutBook() {

        panelCheckoutFields = new JPanel();
        panelCheckoutFields.setLayout(null);
        panelCheckoutFields.setSize(500,500);

        this.setTitle("Checkout Book");
        this.setMinimumSize(new Dimension(850, 600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblISBN = new JLabel("ISBN");
        lblISBN.setBounds(20, 20, 100, 20);
        txtISBN = new JTextField(10);
        txtISBN.setBounds(110, 20, 100, 20);
        JLabel lblMemberID = new JLabel("Member ID");
        lblMemberID.setBounds(20, 50, 100, 20);
        txtMemberID = new JTextField(10);
        txtMemberID.setBounds(110, 50, 100, 20);

        JButton btnCheckout = new JButton("Checkout");
        btnCheckout.setBounds(110, 80, 100, 20);
        addCheckoutListener(btnCheckout);

        JButton btnBackToMain = new JButton("Back to Main");
        addBackButtonListener(btnBackToMain);

        JPanel pnlButtonSave = new JPanel();
        pnlButtonSave.add(btnBackToMain);
        pnlButtonSave.setBounds(20, 150, 800, 35);
        pnlButtonSave.setBackground(Color.white);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Member Id");
        model.addColumn("Member Name");
        model.addColumn("ISBN");
        model.addColumn("Book Name");
        model.addColumn("Checkout Date");
        model.addColumn("Due Date");
        model.addColumn("Available");

        jt = new JTable(model);

        sp = new JScrollPane(jt);
        sp.setBounds(20, 200, 800, 150);
        panelCheckoutFields.add(sp);

        panelCheckoutFields.add(lblMemberID);
        panelCheckoutFields.add(txtISBN);

        panelCheckoutFields.add(lblISBN);
        panelCheckoutFields.add(txtMemberID);

        panelCheckoutFields.add(btnCheckout);
        panelCheckoutFields.add(pnlButtonSave, BorderLayout.CENTER);

        this.setSize(450, 450);
        this.setVisible(true);
        this.setResizable(true);
        this.add(panelCheckoutFields);
        isInitialized = true;
    }

    private void addCheckoutListener(JButton butn) {
        butn.addActionListener(evt -> {
            String bkISBN = txtISBN.getText().trim();
            String memberID = txtMemberID.getText().trim();

            if (bkISBN.length() == 0 || memberID.length() == 0) {
                JOptionPane.showMessageDialog(this, "ISBN and member ID required", "Checkout Failed",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                    try {
                        checkOutBookUseCase.checkOutBook(memberID, bkISBN);
                        JOptionPane.showMessageDialog(this, "Checkout successful", "Thank you", JOptionPane.PLAIN_MESSAGE);
                        displayCheckoutInfo(); 
                        txtISBN.setText("");
                		txtMemberID.setText("");
                    } catch (BookNotFoundException | MemberNotFoundException | BookCopyNotAvailableException e) {
                        JOptionPane.showMessageDialog(this, e.getMessage(), "Check out book", JOptionPane.ERROR_MESSAGE);
                        clearForm();
                    }
            }
        });
    }

    private void addBackButtonListener(JButton butn) {
        butn.addActionListener(evt -> {
            LibrarySystem.hideAllWindows();
            LibrarySystem.UI.setVisible(true);
        });
    }

    
    private void displayCheckoutInfo() {
        CheckOutRecord cr = checkOutBookUseCase.getCheckOutRecord(txtMemberID.getText());
        if (cr == null)
            return;

        model2 = (DefaultTableModel) jt.getModel();
        model2.setRowCount(0);

        for (CheckOutRecordEntry entry : cr.getCheckOutRecordEntries()) {
            model2.addRow(new Object[] { cr.getMember().getMemberId(), cr.getMember().getFullName(),
                    entry.getBookCopy().getBook().getISBN(),
                    entry.getBookCopy().getBook().getTitle(), entry.getCheckOutDate().toString(),
                    entry.getDueDate().toString(), (entry.getBookCopy().getBook().getBookcopies().length - entry.getBookCopy().getCopyNum())});
        }
    }

    public boolean isInitialized() {
        return this.isInitialized;
    }

    public void isInitialized(boolean val) {
        this.isInitialized = val;
    }

    @Override
    public void init() {
        checkOutBook();
    }
    
	public void clearForm() {
		txtISBN.setText("");
		txtMemberID.setText("");	
		model2 = (DefaultTableModel) jt.getModel();
        model2.setRowCount(0);
	}

}
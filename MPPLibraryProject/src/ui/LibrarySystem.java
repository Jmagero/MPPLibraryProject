package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import util.Util;

public class LibrarySystem extends JFrame implements SystemWindow {
	private static final long serialVersionUID = 1L;
	public final static LibrarySystem UI = new LibrarySystem();
	JPanel mainPanel;
	JMenuBar menuBar;
	JMenu options;
	JMenuItem addBookCopy, addBook;
	String pathToImage;
	private boolean isInitialized = false;

	private static SystemWindow[] allWindows = { 
			LibrarySystem.UI, 

			BookWindow.UI, 
			BookCopyWindow.UI };

	public static void hideAllWindows() {

		for (SystemWindow frame : allWindows) {
			frame.setVisible(false);

		}
	}

	private LibrarySystem() {
	}

	public void init() {
		formatContentPane();
		setPathToImage();
		insertSplashImage();

		createMenus();
		// pack();
		setSize(660, 500);
		isInitialized = true;
	}

	private void formatContentPane() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 1));
		getContentPane().add(mainPanel);
	}

	private void setPathToImage() {
		String currDirectory = System.getProperty("user.dir");
		pathToImage = currDirectory + "/src/librarysystem/library.jpg";
	}

	private void insertSplashImage() {
		ImageIcon image = new ImageIcon(pathToImage);
		mainPanel.add(new JLabel(image));
	}

	private void createMenus() {
		menuBar = new JMenuBar();
		menuBar.setBorder(BorderFactory.createRaisedBevelBorder());
		addMenuItems();
		setJMenuBar(menuBar);
	}

	private void addMenuItems() {
		options = new JMenu("OPTIONS");
		menuBar.add(options);
		
		addBookCopy = new JMenuItem("Add Book Copy");
		addBookCopy.addActionListener(new AddBookCopyListener());

		addBook = new JMenuItem("Add New Book");
		addBook.addActionListener(new AddBookListener());
		
		options.add(addBook);
		options.add(addBookCopy);
		
		doAuth();
	}
	
	private void doAuth() {

		LibrarySystem.UI.addBookCopy.setVisible(true);
		LibrarySystem.UI.addBook.setVisible(true);
	}


	class AddBookCopyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LibrarySystem.hideAllWindows();
			BookCopyWindow.UI.init();
			BookCopyWindow.UI.pack();
			Util.centerFrameOnDesktop(BookCopyWindow.UI);
			BookCopyWindow.UI.setVisible(true);

		}

	}

	class AddBookListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			LibrarySystem.hideAllWindows();
			BookWindow.UI.init();
			BookWindow.UI.pack();
			Util.centerFrameOnDesktop(BookWindow.UI);
			BookWindow.UI.setVisible(true);
		}

	}

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;

	}
	
}

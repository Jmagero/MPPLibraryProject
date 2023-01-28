package librarySystem;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import ui.LibrarySystem;



public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			LibrarySystem.UI.setTitle("Maharishi Library System");
			LibrarySystem.UI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			LibrarySystem.UI.init();
			centerFrameOnDesktop(LibrarySystem.UI);
			LibrarySystem.UI.setVisible(true);
		});
	}

	public static void centerFrameOnDesktop(Component f) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int height = toolkit.getScreenSize().height;
		int width = toolkit.getScreenSize().width;
		int frameHeight = f.getSize().height;
		int frameWidth = f.getSize().width;
		f.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 3);
	}


}

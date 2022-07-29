package application;

import javax.swing.SwingUtilities;

public class App {

	public static void main(String[] args) {
		
/*		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MainFrame();
			}
			
		});
		replace all this with a lambda
		*/
		
		SwingUtilities.invokeLater(()->new MainFrame());
		
	}

}

package LoginPackageGUI;

import java.awt.*;

import javax.swing.*;

public class WelcomePanel extends JPanel {

	private String image = "/Images/background1.jpg";

	public WelcomePanel() {

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(getImage(), 0,0, this.getWidth(), this.getHeight(), null);
	}

	public Image getImage(){
		ImageIcon i = new ImageIcon(getClass().getResource(image));
		return i.getImage();
	}

}
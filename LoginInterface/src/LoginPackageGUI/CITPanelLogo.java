package LoginPackageGUI;

import javax.swing.*;
import java.awt.*;

public class CITPanelLogo extends JPanel {

	private String image = "/Images/cit-logo.jpg";
	private int imageWidth, imageHeight;

	public CITPanelLogo(){}

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

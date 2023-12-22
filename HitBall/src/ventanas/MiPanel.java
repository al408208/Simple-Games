package ventanas;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class MiPanel extends JPanel{
	private Image img;

	public MiPanel(Image img) {
		this.img = img;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, 800, 600, null);
	}
}

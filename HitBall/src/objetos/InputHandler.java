package objetos;
import java.awt.Component;
import java.awt.event.*;

/**
 * Author Paco Pulido 10/10/2019
 */
public class InputHandler implements KeyListener {

	Boolean[] keys = new Boolean[256];

	public InputHandler(Component c) {
		for (int i = 0; i < keys.length; i++)
			keys[i] = false;
		c.addKeyListener(this);
	}

	public boolean isKeyDown(int keyCode) {
		if (keyCode > 0 && keyCode < 256) {
			return keys[keyCode];
		}

		return false;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() > 0 && e.getKeyCode() < 256) {
			keys[e.getKeyCode()] = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() > 0 && e.getKeyCode() < 256) {
			keys[e.getKeyCode()] = false;
		}
	}

	public void keyTyped(KeyEvent e) {
	}
}
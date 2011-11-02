package na.widgets.button;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MainMenuButton extends JButton {

	public MainMenuButton() {
//		this.setBorderPainted(false);
//		this.setContentAreaFilled(false);
	}
	
	public MainMenuButton(ImageIcon myIcon) {
		// TODO Quita bordes autom�ticamente de los botones que tienen una imagen
		super(myIcon);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
	}

}

package fish.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JDesktopPane;

public class MyDeskTopPanel extends JDesktopPane{

	/** serialVersionUID*/  
	private static final long serialVersionUID = 1L;
	
	private Image img;
	
	public void setImgURL(URL imgUrl) {
		img=Toolkit.getDefaultToolkit().createImage(imgUrl);
	}
	@Override
	protected void paintComponent(Graphics g) {
		if(this.img == null) return;
		g.drawImage(img, 0, 0,this.getWidth(),this.getHeight(),this);
	}
}

package fish.view;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image image = null;
    public BackgroundPanel(Image image) {
        this.image = image;
    }

    @Override
    public void paintComponents(Graphics g) {
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
//        super.paintComponents(g);
    }
}

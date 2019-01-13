package fish.util;

import fish.view.ProductInternalFrame;

import javax.swing.*;

public class JInternalFrameFactory {
    public static ProductInternalFrame pFrime = new ProductInternalFrame();

    public static JInternalFrame getInstance(String name) {
        JInternalFrame frame = null;
        switch (name) {
            case "ProductInternalFrame":
                frame = createPFrame();
                break;
        }
        return frame;
    }

    public static ProductInternalFrame createPFrame() {
        if (pFrime == null) pFrime = new ProductInternalFrame();
        return pFrime;
    }
}

package GUIUtil;

import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

public class GUIUtil {
	public static void tocenter(Component comp) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle rectangle = ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
		comp.setLocation(((int)rectangle.getWidth()-comp.getWidth())/2,
				((int)rectangle.getHeight()-comp.getHeight())/2);
	}

}

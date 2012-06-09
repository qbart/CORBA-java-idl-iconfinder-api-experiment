
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel
{
    private BufferedImage image;
    URL url;

    public ImagePanel(String src) {
        try {
            url = new URL(src);
            image = resizeImage(ImageIO.read(url));
        } catch (IOException ex) {
        	image = null;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	if (image != null) {
            int x = (getWidth()-image.getWidth())/2;
            int y = (getHeight()-image.getHeight())/2;
            g.drawImage(image, x, y, null);    		
    	}
    }

    public URL getUrl() {
        return url;
    }

    private BufferedImage resizeImage(BufferedImage originalImage) {
        double height = originalImage.getHeight();
        double width = originalImage.getWidth();

        if (height > 100 || width > 100) {

            if (height > width) {
                double tmp = 100 / height;
                height = 100;
                width *= tmp;
            } else if (height < width) {
                double tmp = 100 / width;
                width = 100;
                height *= tmp;
            } else {
                width = 100;
                height = 100;
            }

            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
            BufferedImage resizedImage = new BufferedImage((int) width, (int) height, type);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, (int) width, (int) height, null);
            g.dispose();
            g.setComposite(AlphaComposite.Src);

            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            return resizedImage;
        }

        return originalImage;
    }
}

package ec.cmd;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * The Grayscale class implements the Command interface and provides a method to convert
 * a BufferedImage to grayscale.
 */
public class Grayscale implements Command {

  /**
   * Converts the given BufferedImage to grayscale.
   *
   * @param image the BufferedImage to convert
   * @return the converted grayscale BufferedImage
   */
	@Override
	public BufferedImage convert(BufferedImage image) {
		
        int width = image.getWidth();
        int height = image.getHeight();

        // Create a new BufferedImage for the grayscale image
        BufferedImage grayscaleImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
 

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Get the RGB values of the pixel
                int rgb = grayscaleImage.getRGB(x, y);
                Color color = new Color(rgb);

                // Calculate the grayscale value
                int gray = (int) (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue());

                // Create a new Color object with the grayscale value
                Color grayColor = new Color(gray, gray, gray);

                // Set the new grayscale color to the pixel
                grayscaleImage.setRGB(x, y, grayColor.getRGB());
            }
        }
        
        return grayscaleImage;
	}

}

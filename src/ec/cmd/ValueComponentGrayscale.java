package ec.cmd;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * The ValueComponentGrayscale class implements the Command interface and provides a method to
 * convert a BufferedImage to grayscale using the value component (brightness).
 */
public class ValueComponentGrayscale implements Command {

	/**
	 * Converts the given BufferedImage to grayscale using the value component (brightness).
	 *
	 * @param image the BufferedImage to convert
	 * @return the grayscale BufferedImage
	 */
	@Override
	public BufferedImage convert(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();

		// Create a new BufferedImage for the grayscale image
		BufferedImage grayscaleImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

		// Convert each pixel to grayscale using value component
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// Get the RGB values of the pixel
				int rgb = image.getRGB(x, y);
				Color color = new Color(rgb);

				// Convert to HSB color model
				float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);

				// Get the value component (brightness)
				float brightness = hsb[2];

				// Create a grayscale color using the brightness value
				Color grayscaleColor = new Color(brightness, brightness, brightness);

				// Set the grayscale color to the pixel in the grayscale image
				grayscaleImage.setRGB(x, y, grayscaleColor.getRGB());
			}
		}

		return grayscaleImage;
	}

}

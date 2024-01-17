package ec.cmd;

import java.awt.image.BufferedImage;

/**
 * The Brighten class implements the Command interface and provides a method to
 * increase the brightness of a BufferedImage by a specified increment.
 */
public class Brighten  implements Command {
	
	private int incr;

	/**
	 * Constructs a Brighten object with the specified increment value.
	 *
	 * @param incr the increment value to increase the brightness
	 */
	public Brighten(int incr) {
		this.incr = incr;
	}

	/**
	 * Converts the given BufferedImage by increasing the brightness of each pixel
	 * by the specified increment value.
	 *
	 * @param input the BufferedImage to convert
	 * @return the converted BufferedImage with increased brightness
	 */
	@Override
	public BufferedImage convert(BufferedImage input) {
        BufferedImage image = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
        image.getGraphics().drawImage(input, 0, 0, null);
 		
	    int width = image.getWidth();
	    int height = image.getHeight();
 
	    for (int y = 0; y < height; y++) {
	        for (int x = 0; x < width; x++) {
	            // Get the RGB value of the pixel
	            int rgb = image.getRGB(x, y);

	            // Extract the individual color components
	            int alpha = (rgb >> 24) & 0xFF;
	            int red = (rgb >> 16) & 0xFF;
	            int green = (rgb >> 8) & 0xFF;
	            int blue = rgb & 0xFF;

	            // Increase the brightness of each color component
	            red = Math.min(red + incr, 255);
	            green = Math.min(green + incr, 255);
	            blue = Math.min(blue + incr, 255);

	            // Combine the color components and update the pixel value
	            int updatedRGB = (alpha << 24) | (red << 16) | (green << 8) | blue;
	            image.setRGB(x, y, updatedRGB);
	        }
	    }
        return image;
	}

}

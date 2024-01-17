package ec.cmd;

import java.awt.image.BufferedImage;

/**
 * The HorizontalFlip class implements the Command interface and provides a method to
 * horizontally flip a BufferedImage.
 */
public class HorizontalFlip  implements Command  {

	/**
	 * Converts the given BufferedImage by horizontally flipping it.
	 *
	 * @param image the BufferedImage to convert
	 * @return the horizontally flipped BufferedImage
	 */
	@Override
	public BufferedImage convert(BufferedImage image) {
		
        BufferedImage clonedImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        clonedImage.getGraphics().drawImage(image, 0, 0, null);
 		
	    int width = clonedImage.getWidth();
	    int height = clonedImage.getHeight();

	    // Iterate over each row
	    for (int y = 0; y < height; y++) {
	        // Swap the pixels horizontally
	        for (int x1 = 0, x2 = width - 1; x1 < x2; x1++, x2--) {
	            int pixel1 = clonedImage.getRGB(x1, y);
	            int pixel2 = clonedImage.getRGB(x2, y);
	            clonedImage.setRGB(x1, y, pixel2);
	            clonedImage.setRGB(x2, y, pixel1);
	        }
	    }
	 
	    return clonedImage;
	    
	}
	
}

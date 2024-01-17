package ec.cmd;

import java.awt.image.BufferedImage;

/**
 * The VerticalFlip class implements the Command interface and provides a method to flip a BufferedImage vertically.
 */
public class VerticalFlip implements Command {

  /**
   * Flips the given BufferedImage vertically.
   *
   * @param input the BufferedImage to flip
   * @return the vertically flipped BufferedImage
   */
	@Override
	public BufferedImage convert(BufferedImage input) {
		
        BufferedImage image = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
        image.getGraphics().drawImage(input, 0, 0, null);
 		
	    int width = image.getWidth();
	    int height = image.getHeight();
 
        // Swap the rows vertically
        for (int y1 = 0, y2 = height - 1; y1 < y2; y1++, y2--) {
            int[] row1 = image.getRGB(0, y1, width, 1, null, 0, width);
            int[] row2 = image.getRGB(0, y2, width, 1, null, 0, width);
            image.setRGB(0, y1, width, 1, row2, 0, width);
            image.setRGB(0, y2, width, 1, row1, 0, width);
        }
        
        return image;
		
	}

}

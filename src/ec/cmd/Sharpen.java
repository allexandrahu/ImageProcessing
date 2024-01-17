package ec.cmd;

import java.awt.image.BufferedImage;

/**
 * The Sharpen class implements the Command interface and provides a method to
 * apply sharpening effect to a BufferedImage.
 */
public class Sharpen implements Command  {

  /**
   * Converts the given BufferedImage by applying a sharpening effect.
   *
   * @param image the BufferedImage to convert
   * @return the sharpened BufferedImage
   */
	@Override
	public BufferedImage convert(BufferedImage image) {
		
 	BufferedImage res = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = sharpenPixel(image, x, y);
                res.setRGB(x, y, rgb);
            }
        }

		return res;
	}

  /**
   * Applies sharpening effect to the specified pixel in the given BufferedImage.
   *
   * @param image the BufferedImage
   * @param x     the x-coordinate of the pixel
   * @param y     the y-coordinate of the pixel
   * @return the modified RGB value of the pixel after sharpening effect
   */
	private int sharpenPixel(BufferedImage image, int x, int y) {
        int[] pixels = new int[9];
        
 
        try {
			image.getRGB(x - 1, y - 1, 3, 3, pixels, 0, 3);
		} catch (Exception e) {
			// skip boundary
 
		}

        int sumR = 0, sumG = 0, sumB = 0;
        for (int pixel : pixels) {
            sumR += (pixel >> 16) & 0xFF;
            sumG += (pixel >> 8) & 0xFF;
            sumB += pixel & 0xFF;
        }

        int avgR = sumR / 9;
        int avgG = sumG / 9;
        int avgB = sumB / 9;

        int pixel = image.getRGB(x, y);
        int originalR = (pixel >> 16) & 0xFF;
        int originalG = (pixel >> 8) & 0xFF;
        int originalB = pixel & 0xFF;

        int sharpenedR = clamp(originalR + (originalR - avgR));
        int sharpenedG = clamp(originalG + (originalG - avgG));
        int sharpenedB = clamp(originalB + (originalB - avgB));

        return (sharpenedR << 16) | (sharpenedG << 8) | sharpenedB;
	}

  /**
   * Clamps the given value to be within the range of 0 to 255 (inclusive).
   *
   * @param value the value to clamp
   * @return the clamped value
   */
    private int clamp(int value) {
        return Math.max(0, Math.min(value, 255));
    }
}

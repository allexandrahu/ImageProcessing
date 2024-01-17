package ec.cmd;

import java.awt.image.BufferedImage;

/**
 * The Blur class implements the Command interface and provides a method to convert
 * a BufferedImage to a blurred version using a sepia filter.
 */
public class Blur implements Command {

  /**
   * Converts the given BufferedImage to a blurred version using a sepia filter.
   *
   * @param image the BufferedImage to convert
   * @return the converted BufferedImage
   */
  @Override
  public BufferedImage convert(BufferedImage image) {

    BufferedImage res = new BufferedImage(image.getWidth(), image.getHeight(),
        BufferedImage.TYPE_INT_RGB);

    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        int rgb = blurPixel(image, x, y);
        res.setRGB(x, y, rgb);
      }
    }
    return res;
  }

  /**
   * Applies a sepia filter to the pixel at the specified coordinates in the given image.
   *
   * @param image the BufferedImage to apply the filter to
   * @param x the x-coordinate of the pixel
   * @param y the y-coordinate of the pixel
   * @return the filtered RGB value of the pixel
   */
  private int blurPixel(BufferedImage image, int x, int y) {
    int pixel = image.getRGB(x, y);
    int r = (pixel >> 16) & 0xFF;
    int g = (pixel >> 8) & 0xFF;
    int b = pixel & 0xFF;

    int sepiaR = (int) (0.393 * r + 0.769 * g + 0.189 * b);
    int sepiaG = (int) (0.349 * r + 0.686 * g + 0.168 * b);
    int sepiaB = (int) (0.272 * r + 0.534 * g + 0.131 * b);

    sepiaR = Math.min(255, sepiaR);
    sepiaG = Math.min(255, sepiaG);
    sepiaB = Math.min(255, sepiaB);

    return (sepiaR << 16) | (sepiaG << 8) | sepiaB;
  }
}

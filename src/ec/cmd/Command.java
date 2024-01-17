package ec.cmd;

import java.awt.image.BufferedImage;

/**
 * The Command interface represents a command that can be applied to a BufferedImage.
 */
public interface Command {

	/**
	 * Converts the given BufferedImage according to the specific command implementation.
	 *
	 * @param image the BufferedImage to convert
	 * @return the converted BufferedImage
	 */
	BufferedImage convert(BufferedImage image);

}

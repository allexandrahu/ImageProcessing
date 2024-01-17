package ec.view;

import java.awt.Image;

/**
 * The ImageView interface represents a view for rendering images
 */
public interface ImageView {

	/**
	 * Renders the specified image.
	 *
	 * @param image the image to be rendered
	 */
	void renderImage(Image image);

/**
 * Sets the visibility of the image view.
 */
	void setVisible();

}

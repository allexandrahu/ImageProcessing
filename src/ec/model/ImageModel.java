package ec.model;

import java.awt.Image;

/**
 * The `ImageModel` interface represents a model for storing and retrieving images.
 * Implementations of this interface provide methods to put an image into the model and
 * retrieve an image from the model.
 */
public interface ImageModel {
	/**
	 * Stores an image in the model with the specified name.
	 *
	 * @param name  the name of the image
	 * @param image the image to be stored
	 */
	void put(String name, Image image);

	/**
	 * Retrieves the image associated with the specified name from the model.
	 *
	 * @param name the name of the image to retrieve
	 * @return the retrieved image, or null if no image is associated with the specified name
	 */
	Image get(String name);
	
}

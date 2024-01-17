package ec.model;

import java.awt.Image;
import java.util.HashMap;

/**
 * The `ImageModelmpl` class is an implementation of the `ImageModel` interface
 * that uses a HashMap to store and retrieve images based on their names.
 */
public class ImageModelmpl implements ImageModel {

	// model
	private HashMap<String, Image> map;

	/**
	 * Constructs a new `ImageModelmpl` instance.
	 */
	public ImageModelmpl() {
		map = new HashMap<String, Image>();
	}

	/**
	 * Stores the specified image with the given name in the model.
	 *
	 * @param name  the name of the image
	 * @param image the image to be stored
	 */
	@Override
	public void put(String name, Image image) {

		if (image == null) {
			System.out.println("image is null, please try again");
			return;
		}

		map.put(name, image);

	}

	/**
	 * Retrieves the image associated with the given name from the model.
	 *
	 * @param name the name of the image to retrieve
	 * @return the image associated with the given name, or null if not found
	 */
	@Override
	public Image get(String name) {
		return map.get(name);

	}

}

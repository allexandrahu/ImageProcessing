package ec.view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The `ImageViewImpl` class is an implementation of the `ImageView` interface
 * that provides a graphical view for rendering and displaying images.
 */
public class ImageViewImpl extends JFrame implements ImageView {

  private JPanel imagePanel;
  ImageIcon scaledImageIcon;
  JLabel imageLabel;

  /**
   * Constructs a new `ImageViewImpl` instance.
   */
  public ImageViewImpl() {
    setTitle("Image Manipulation and Enhancement");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    BufferedImage image = new BufferedImage(800, 500, BufferedImage.TYPE_INT_RGB);

    scaledImageIcon = new ImageIcon(image);
    imageLabel = new JLabel(scaledImageIcon);

    add(imageLabel, BorderLayout.NORTH);
    setSize(800, 500);
    setVisible(true);
  }

  /**
   * Renders the image
   *
   * @param image the image to be rendered
   */
  @Override
  public void renderImage(Image image) {

    if (image == null) {
      System.out.println("image is null, please try again");
      return;
    }

    Image newimage = image.getScaledInstance(800, 500, Image.SCALE_SMOOTH);

    scaledImageIcon = new ImageIcon(newimage);
    imageLabel.setIcon(scaledImageIcon);

  }

  /**
   * Main method to demonstrate the `ImageViewImpl` class.
   *
   * @param args the command-line arguments
   */
  public static void main(String[] args) {
    ImageView frame = new ImageViewImpl();

    frame.setVisible();
  }

  /**
   * Sets the visibility of a component to true by calling the setVisible() method from the superclass
   */
  @Override
  public void setVisible() {
    super.setVisible(true);

  }

}

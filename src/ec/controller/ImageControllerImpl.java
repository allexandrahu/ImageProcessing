package ec.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;

import ec.cmd.Blur;
import ec.cmd.Brighten;
import ec.cmd.Command;
import ec.cmd.HorizontalFlip;
import ec.cmd.SepiaTone;
import ec.cmd.Sharpen;
import ec.cmd.ValueComponentGrayscale;
import ec.cmd.VerticalFlip;
import ec.model.ImageModel;
import ec.model.ImageModelmpl;
import ec.view.ImageView;
import ec.view.ImageViewImpl;

/**
 * The ImageControllerImpl class implements the ImageController interface and provides methods for controlling image processing operations.
 */
public class ImageControllerImpl implements ImageController {

  private ImageModel model;
  private ImageView view;
  private String workDir = "";

  /**
   * Constructs an ImageControllerImpl object with the specified model and view.
   *
   * @param model the image model
   * @param view the image view
   */
  public ImageControllerImpl(ImageModel model, ImageView view) {

    this.model = model;
    this.view = view;

    try {
      File currentDirectory = new File(new File(".").getAbsolutePath());
      System.out.println(currentDirectory.getCanonicalPath());
      // mac vs windows
      workDir = currentDirectory.getCanonicalPath();
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

  }

  @Override
  public void go() {

    view.setVisible();

    Scanner scanner = new Scanner(System.in);
    String lastLine;

    System.out.println("Enter command (or 'q' to exit): ");

    while (true) {

      boolean cont = false;

      cont = processLine(scanner.nextLine());

      if (!cont) {
        break;
      }
    }

    scanner.close();
    System.out.println("Loop exited. Program finished.\n");

  }

  /**
   * Processes a single line of input.
   *
   * @param line the input line to process
   * @return true if the processing should continue, false otherwise
   */
  public boolean processLine(String line) {

    if (line.equalsIgnoreCase("q")) {
      return false;
    }

    if (line.startsWith("script")) {

      String[] tokens = line.split(" ");

      // recursive
      try (BufferedReader reader = new BufferedReader(new FileReader(workDir + tokens[1]))) {
        String singleLine;
        while ((singleLine = reader.readLine()) != null) {
          boolean cont = false;

          cont = processLine(singleLine);
          if (!cont) {
            return false;
          }
        }
      } catch (IOException e) {
        System.out.println("Err : process script file, please try again");
      }

      return true;

    } else if (line.startsWith("load")) {
      String[] tokens = line.split(" ");

      // load res/Sunset.png sunset

      ImageIcon imageIcon = new ImageIcon(workDir + tokens[1]);
      view.renderImage(imageIcon.getImage());
      model.put(tokens[2], imageIcon.getImage());

    } else if (line.startsWith("vertical-flip")) {
      String[] tokens = line.split(" ");

      // vertical-flip sunset sunset-vertical

      Image origin = model.get(tokens[1]);

      Command cmd = new VerticalFlip();

      Image image = cmd.convert(convertImageToBufferedImage(origin));

      model.put(tokens[2], image);

      view.renderImage(image);

    } else if (line.startsWith("horizontal-flip")) {
      String[] tokens = line.split(" ");

      // horizontal-flip sunset sunset-vertical

      Image origin = model.get(tokens[1]);

      Command cmd = new HorizontalFlip();

      Image image = cmd.convert(convertImageToBufferedImage(origin));

      model.put(tokens[2], image);

      view.renderImage(image);

    } else if (line.startsWith("value-component")) {
      String[] tokens = line.split(" ");

      // value-component sunset sunset-value-component

      Image origin = model.get(tokens[1]);

      Command cmd = new ValueComponentGrayscale();

      Image image = cmd.convert(convertImageToBufferedImage(origin));

      model.put(tokens[2], image);

      view.renderImage(image);

    } else if (line.startsWith("brighten")) {
      String[] tokens = line.split(" ");

      // brighten 10 sunset sunset-brighten-10

      Image origin = model.get(tokens[2]);

      Command cmd = new Brighten(Integer.parseInt(tokens[1]));

      Image image = cmd.convert(convertImageToBufferedImage(origin));

      model.put(tokens[3], image);

      view.renderImage(image);


    } else if (line.startsWith("blur")) {
      String[] tokens = line.split(" ");

      // blur sunset sunset-blur

      Image origin = model.get(tokens[1]);

      Command cmd = new Blur();

      Image image = cmd.convert(convertImageToBufferedImage(origin));
      model.put(tokens[2], image);

      view.renderImage(image);
      ;

    } else if (line.startsWith("sharpen")) {
      String[] tokens = line.split(" ");

      // sharpen sunset sunset-sharpen

      Image origin = model.get(tokens[1]);

      Command cmd = new Sharpen();

      Image image = cmd.convert(convertImageToBufferedImage(origin));
      model.put(tokens[2], image);

      view.renderImage(image);
      ;

    } else if (line.startsWith("sepiatone")) {
      String[] tokens = line.split(" ");

      // sepiatone sunset sunset-sepiatone

      Image origin = model.get(tokens[1]);

      Command cmd = new SepiaTone();

      Image image = cmd.convert(convertImageToBufferedImage(origin));
      model.put(tokens[2], image);

      view.renderImage(image);
      ;

    } else if (line.startsWith("save")) {
      String[] tokens = line.split(" ");
      Image origin = model.get(tokens[2]);

      saveImageAsPPM(convertImageToBufferedImage(origin), workDir + tokens[1]);

    } else {
      System.out.println("Invalid command, please try again");
    }

    return true;
  }

  /**
   * Converts an Image object to a BufferedImage object.
   *
   * @param image the Image to convert
   * @return the converted BufferedImage
   */
  public BufferedImage convertImageToBufferedImage(Image image) {
    // Create a BufferedImage with the same width, height, and transparency type
    BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
        BufferedImage.TYPE_INT_ARGB);

    // Draw the Image onto the BufferedImage
    Graphics2D graphics = bufferedImage.createGraphics();
    graphics.drawImage(image, 0, 0, null);
    graphics.dispose();

    // Return the converted BufferedImage
    return bufferedImage;
  }

  /**
   * Saves a BufferedImage as a PPM file.
   *
   * @param image the BufferedImage to save
   * @param filePath the file path to save the image to
   */
  public void saveImageAsPPM(BufferedImage image, String filePath) {
    int width = image.getWidth();
    int height = image.getHeight();

    try (PrintWriter writer = new PrintWriter(new File(filePath))) {
      writer.println("P3");
      writer.println(width + " " + height);
      writer.println("255");

      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          int rgb = image.getRGB(x, y);
          int red = (rgb >> 16) & 0xFF;
          int green = (rgb >> 8) & 0xFF;
          int blue = rgb & 0xFF;

          writer.println(red + " " + green + " " + blue);
        }
      }
    } catch (IOException e) {
      System.out.println("Err : save ppm file, please try again");
    }
  }

  /**
   * The main method creates an ImageControllerImpl object and starts the image processing program.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {

    ImageController controller = new ImageControllerImpl(new ImageModelmpl(), new ImageViewImpl());

    controller.go();
  }

}

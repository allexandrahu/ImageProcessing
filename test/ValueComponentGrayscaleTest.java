import static junit.framework.TestCase.assertNotNull;


import ec.cmd.Command;
import ec.cmd.ValueComponentGrayscale;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;

public class ValueComponentGrayscaleTest {



  // one commnd
  // buffer image
  Command cmd = new ValueComponentGrayscale();
  String workDir = "";

  @Test
  public void convertTest() throws IOException {


    try {
      File currentDirectory = new File(new File(".").getAbsolutePath());
      System.out.println(currentDirectory.getCanonicalPath());
      workDir = currentDirectory.getCanonicalPath() + File.separator + File.separator;
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    BufferedImage img =  ImageIO.read(new File(workDir + "/res/Sunset.png"));
    BufferedImage genImage =  cmd.convert(img);

    assertNotNull(genImage);

      }

}

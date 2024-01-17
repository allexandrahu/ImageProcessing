
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;


import ec.cmd.Brighten;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;

public class BrightenTest {

   // one commnd
  // buffer image
   Brighten cmd = new Brighten(10);
   String workDir = "";

   @Test
   public void convertTest() throws IOException {

      try {
         File currentDirectory = new File(new File(".").getAbsolutePath());
         System.out.println(currentDirectory.getCanonicalPath());
         workDir = currentDirectory.getCanonicalPath() +  File.separator +File.separator;
      } catch (IOException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }

      BufferedImage img =  ImageIO.read(new File(workDir + "/res/Sunset.png"));
      BufferedImage genImage =  cmd.convert(img);

      assertNotNull(genImage);

   }

}

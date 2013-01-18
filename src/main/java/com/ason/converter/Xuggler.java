package com.ason.converter;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IVideoPictureEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Xuggler {
    public static void transcode(String dir, String in, String out,String type) {

        IMediaReader reader = ToolFactory.makeReader(dir + "/" + in);

        IMediaWriter writer = ToolFactory.makeWriter(dir + "/" + out, reader);
        reader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);
        if (!type.equals("mp4") ) {reader.addListener(writer);
        }
        reader.addListener(new ImageSnapListener(out));

        while (reader.readPacket() == null) ;


        if (!type.equals("mp4") ) {
            File file = new File(dir+"/"+in);
            file.delete();
        }
//            do {
//            } while (false);
    }


    private static class ImageSnapListener extends MediaListenerAdapter {

        String output;

        public ImageSnapListener(String out) {

            output = out;

        }

        public void onVideoPicture(IVideoPictureEvent event) {

            if (event.getTimeStamp() == 0) {


                dumpImageToFile(event.getImage());

            }

        }

        private String dumpImageToFile(BufferedImage image) {
            try {
                String outputFilename = "pictures/" +
                        output + ".png";
                ImageIO.write(image, "png", new File(outputFilename));
                return outputFilename;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

    }

}

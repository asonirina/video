package com.ason.converter;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.IMediaViewer;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;

public class Xuggler {
    public static void transcode(String in, String out)
    {

        IMediaReader reader = ToolFactory.makeReader(in);
        // add a viewer to the reader, to see progress as the media is
        // transcoded
        //reader.addListener(ToolFactory.makeViewer(true));
        // create the media writer
        IMediaWriter writer =

        ToolFactory.makeWriter(out, reader);

        reader.addListener(writer);

        while (reader.readPacket() == null)

            do {} while(false);
    }

}

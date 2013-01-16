package com.ason.converter;

//import com.ason.converter.VideoConverter;
import org.junit.Test;



public class VideoConverterTest {
    @Test
    public void test() throws Exception {

        Xuggler.transcode("123.mpg","123.mp4");
    }
}

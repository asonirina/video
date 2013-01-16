package com.ason.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.IOException;

import com.ason.configuration.AppConfig;


public class VideoServlet extends HttpServlet {

    String name;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        name = request.getParameter("name");
        File file = new File(AppConfig.getInstance().getProps().getProperty("video_dir") + "/" + name);
        String postfix = name.substring(name.lastIndexOf('.') + 1);
        response.setContentType("video/" + postfix);
        response.setContentLength((int) file.getTotalSpace());
        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Content-Length", String.valueOf(file.length()));
        byte[] content = new byte[1024];
        FileInputStream is = new FileInputStream(file);
        OutputStream os = response.getOutputStream();

        while (is.read(content) != -1) {
            os.write(content);
            os.flush();
        }

        is.close();
        os.close();

    }

}
package com.ason.controller;

import java.io.File;
import java.util.List;
import com.ason.model.FileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import com.ason.configuration.AppConfig;



@Controller
public class FileController {

    private static List<String> list = AppConfig.FILE_LIST;
    private static String videoDir = AppConfig.VIDEO_DIR;


    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView displayForm() {
        return new ModelAndView("file_upload_form", "list", list);
    }

    @RequestMapping(value = "/play/*")
    public ModelAndView playVideo(HttpServletRequest request) {

        String name = request.getRequestURI();
        int index = name.lastIndexOf('/');
        name = name.substring(index + 1);

        return new ModelAndView("play", "name", name);
    }

    @RequestMapping(value = "/delete/*")
    public String delete(HttpServletRequest request) throws IOException {
        String name = request.getRequestURI();
        int index = name.lastIndexOf('/');
        name = name.substring(index + 1);

        File file = new File(videoDir + "/" + name);
        if (file.delete()) {
            list.remove(name);
            return "delete_success";
        }
        return "delete_fail";
    }

    @RequestMapping(value = "/save")
    public ModelAndView save(
            @ModelAttribute("uploadForm") FileUpload fileUpload) throws Exception {


        MultipartFile file = fileUpload.getFile();
        if (file != null) {
            file.transferTo(new File(videoDir + "/" + file.getOriginalFilename()));
            list.add(file.getOriginalFilename());
        }

        return new ModelAndView("file_upload_form", "list", list);
    }
}

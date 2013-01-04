package com.ason.controller;

import java.io.File;

import java.util.HashSet;
import java.util.Set;

import com.ason.model.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class FileUploadController {

    private static Set<String> set = new HashSet<String>();

    static {
        File dir = new File("videos");
        for (String file : dir.list()) {
            set.add(file);
        }
    }


    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView displayForm() {
        return new ModelAndView("file_upload_form", "set", set);
    }

    @RequestMapping(value = "/play/*")
    public ModelAndView playVideo(HttpServletRequest request) {

        String name = request.getRequestURI();
        int index = name.lastIndexOf('/');
        name = name.substring(index + 1);

        return new ModelAndView("play", "name", name);
    }

    @RequestMapping(value = "/play")
    public String play(HttpServletRequest request) {

        String name = request.getRequestURI();

        return "play";
    }

    @RequestMapping(value = "/save")
    public ModelAndView save(
            @ModelAttribute("uploadForm") FileUpload fileUpload) throws Exception {


        MultipartFile file = fileUpload.getFile();
        if (file != null) {

            file.transferTo(new File("videos/" + file.getOriginalFilename()));

            set.add(file.getOriginalFilename());


        }

        return new ModelAndView("file_upload_form", "set", set);
    }
}

package com.ason.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.ason.model.FileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


@Controller
public class FileController {

    private static List<String> list = new ArrayList<String>();

    static {
        File dir = new File("videos");
        for (String file : dir.list()) {
            list.add(file);
        }
    }


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
    public String delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getRequestURI();
        int index = name.lastIndexOf('/');
        name = name.substring(index + 1);

        File file = new File("videos/" + name);
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
            file.transferTo(new File("videos/" + file.getOriginalFilename()));
            list.add(file.getOriginalFilename());
        }

        return new ModelAndView("file_upload_form", "list", list);
    }
}

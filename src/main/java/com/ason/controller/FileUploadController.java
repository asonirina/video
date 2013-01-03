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

@Controller
public class FileUploadController {

    private static List<String> list = new ArrayList<String>();


    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView displayForm() {
        return new ModelAndView("file_upload_form", "list", list);
    }

    @RequestMapping(value = "/save")
    public ModelAndView save(
            @ModelAttribute("uploadForm") FileUpload fileUpload) throws Exception {


        MultipartFile file = fileUpload.getFile();
        if (file != null) {

            file.transferTo(new File(file.getOriginalFilename()));

            list.add(file.getOriginalFilename());

        }

        return new ModelAndView("file_upload_form", "list", list);
    }
}

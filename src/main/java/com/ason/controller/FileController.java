package com.ason.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ason.model.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

import com.ason.configuration.AppConfig;


@Controller
public class FileController {
    static AppConfig ac = AppConfig.getInstance();
    private static List<String> FILE_LIST = new ArrayList<String>();
    private static String VIDEO_DIR = ac.getProps().getProperty("video_dir");

    static {
        File dir = new File(VIDEO_DIR);
        for (String file : dir.list()) {
            FILE_LIST.add(file);
        }
    }


    @RequestMapping(value = "/play")
    public ModelAndView playVideo(HttpServletRequest request) {
        String name = request.getParameter("name");

        return new ModelAndView("play", "name", name);
    }

    @RequestMapping(value = "/delete")
    public String delete(HttpServletRequest request) throws IOException {
        String name = request.getParameter("name");

        File file = new File(VIDEO_DIR + "/" + name);
        if (file.delete()) {
            FILE_LIST.remove(name);
            return "delete_success";
        }
        return "delete_fail";
    }

//    @RequestMapping(value = "/save")
//    public ModelAndView save(
//            @ModelAttribute("uploadForm") FileUpload fileUpload, HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//
//        MultipartFile file = fileUpload.getFile();
//        if (file != null) {
//            file.transferTo(new File(videoDir + "/" + file.getOriginalFilename()));
//            list.add(file.getOriginalFilename());
//        }
//
//        request.setAttribute("num",list.size()/10);
//        RequestDispatcher view = request.getRequestDispatcher("file_upload_form");
//        view.forward(request, response);
//
//        return new ModelAndView("file_upload_form", "list", list);
//    }

    @RequestMapping(value = "/save")
    public ModelAndView save(
            @ModelAttribute("uploadForm") FileUpload fileUpload,
            HttpServletRequest request) throws Exception {

        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception ex) {
            page = 1;
        }


        MultipartFile file = fileUpload.getFile();
        if (file != null) {
            file.transferTo(new File(VIDEO_DIR + "/" + file.getOriginalFilename()));
            FILE_LIST.add(file.getOriginalFilename());

        }
        List<String> res = new ArrayList<String>();
        int lost = FILE_LIST.size() - (page - 1) * 10;
        for (int i = 0; i < 10 && lost > 0; ++i) {
            res.add(FILE_LIST.get((page - 1) * 10 + i));
            lost--;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", res);
        map.put("pg", page);
        map.put("num", FILE_LIST.size() / 10 + Math.round(Math.signum(FILE_LIST.size() % 10)));


        return new ModelAndView("file_upload_form", map);
    }
}

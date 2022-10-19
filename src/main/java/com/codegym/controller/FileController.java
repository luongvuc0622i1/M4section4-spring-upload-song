package com.codegym.controller;

import com.codegym.model.File;
import com.codegym.model.FileForm;
import com.codegym.model.ProductForm;
import com.codegym.service.FileService;
import com.codegym.service.IFileService;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {
    private final IFileService fileService = new FileService();

    @GetMapping("")
    public String index(Model model) {
        List<File> files = fileService.findAll();
        model.addAttribute("files", files);
        return "/index";
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("fileForm", new FileForm());
        return modelAndView;
    }

    @Value("${file-upload}")
    private String fileUpload;

    @PostMapping("/save")
    public ModelAndView saveProduct(@ModelAttribute FileForm fileForm) {
        MultipartFile multipartFile = fileForm.getName();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(fileForm.getName().getBytes(), new java.io.File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        File file = new File(fileForm.getId(), fileName, fileForm.getOriginalFilename(), fileForm.getContentType(), fileForm.getSize(), fileForm.getDate());
        fileService.save(file);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("fileForm", fileForm);
        modelAndView.addObject("message", "Created new file successfully !");
        return modelAndView;
    }
}


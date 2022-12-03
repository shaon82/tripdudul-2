package com.updatetech.tripDodol.controller.supper_admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BlogController {


    @GetMapping("/allBlog")
    public String blogAllPage(){
        return "admin/blog-all";
    }


    @GetMapping("/addBlog")
    public String blogAddPage(){
        return "admin/blog-add";
    }
}

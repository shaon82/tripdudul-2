package com.updatetech.tripDodol.controller.supper_admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class UiController {


    @GetMapping("/uiForm")
    public String uiFormPage(){
        return "admin/ui-form";
    }



    @GetMapping("/uiKit")
    public String uiKitPage(){
        return "admin/ui-kit";
    }



    @GetMapping("/uiTable")
    public String uiTablePage(){
        return "admin/ui-table";
    }



    @GetMapping("/uiPreLoad")
    public String uiPreLoadPage(){
        return "admin/ui-pre-load";
    }


    @GetMapping("/uiTab")
    public String uiTabPage(){
        return "admin/ui-tab";
    }



    @GetMapping("/uiIcon")
    public String uiIconsPage(){
        return "admin/ui-icons";
    }



    @GetMapping("/uiCollapsible")
    public String uiCollapsiblePage(){
        return "admin/ui-collapsible";
    }
}

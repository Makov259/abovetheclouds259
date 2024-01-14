package com.simpleform.controller;

import com.simpleform.entity.UserModel;
import com.simpleform.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;  // Correct import for Model
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest",
                new UserModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UserModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel){
        System.out.println("register request: " + userModel);
        UserModel registeredUser = usersService.registerUser(userModel.getLogin(), userModel.getPassword(),
                userModel.getEmail(),userModel.getRole());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @GetMapping("/adminUser_page")
    public String getAdminUserPage(ch.qos.logback.core.model.Model model) {
        // Add necessary model attributes if needed
        return "adminUser_page";
    }


    @GetMapping("/addEmployee_page.html")
    public String getAddEmployeePage() {
        return "addEmployee_page";
    }

    @GetMapping("/deleteEmployee_page.html")
    public String getDeleteEmployeePage() {
        return "deleteEmployee_page";
    }

    @GetMapping("/updateEmployee_page.html")
    public String getUpdateEmployeePage(){
        return "updateEmployee_page";
    }

    @GetMapping("/addClient_page.html")
    public String getAddClientPage() {
        return "addClient_page";
    }

    @GetMapping("/deleteClient_page.html")
    public String getDeleteClientPage() {
        return "deleteClient_page";
    }

    @GetMapping("/updateClient_page.html")
    public String getUpdateClientPage(){
        return "updateClient_page";
    }

    @GetMapping("/addCompany_page.html")
    public String getAddCompanyPage(){
        return "addCompany_page";
    }
    @GetMapping("/updateCompany_page.html")
    public String getUpdateCompanyPage(){
        return "updateCompany_page";
    }
    @GetMapping("/deleteCompany_page.html")
    public String getDeleteCompanyPage(){
        return "deleteCompany_page";
    }

    @GetMapping("/addOffice_page.html")
    public String getAddOfficePage(){
        return "addOffice_page";
    }

    @GetMapping("/updateOffice_page.html")
    public String getUpdateOfficePage(){
        return "updateOffice_page";
    }

    @GetMapping("/deleteOffice_page.html")
    public String getDeleteOfficePage(){
        return "deleteOffice_page";
    }

    @GetMapping("/addPackage_page.html")
    public String getAddPackagePage(){
        return "addPackage_page";
    }

    @GetMapping("/updatePackage_page.html")
    public String getUpdatePackagePage(){
        return "updatePackage_page";
    }

    @GetMapping("/deletePackage_page.html")
    public String getDeletePackagePage(){
        return "deletePackage_page";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel, Model model) {
        System.out.println("login request: " + userModel);
        UserModel authenticated = usersService.authenticate(userModel.getLogin(), userModel.getPassword());

        if (authenticated != null) {
            String role = authenticated.getRole();
            if ("admin".equals(role)) {
                model.addAttribute("userLogin", authenticated.getLogin());
                return "adminUser_page";
            } else if ("User".equals(role)) {
                model.addAttribute("userLogin", authenticated.getLogin());
                return "user_index";

            } else if ("employeeUser".equals(role)) {
                model.addAttribute("userLogin", authenticated.getLogin());
                return "employeeUser_page";
            } else {
                return "error_page";
            }
        } else {
            return "error_page";
        }
    }

}


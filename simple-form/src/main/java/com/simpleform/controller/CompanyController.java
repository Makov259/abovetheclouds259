package com.simpleform.controller;

import com.simpleform.entity.Company;
import com.simpleform.entity.Employee;
import com.simpleform.service.CompanyService;
import com.simpleform.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/add")
    public String addCompany(Company company) {
        System.out.println("Received company data: " + company);
        companyService.addCompany(company);
        return "addCompany_page.html";
    }

    @PostMapping("/update")
    public String updateCompany(@RequestParam("id") Long id,
                                 @RequestParam(value = "companyName", required = false) String companyName,
                                 @RequestParam(value = "bulstat", required = false) Integer bulstat,
                                 @RequestParam(value = "numberOfOffices", required = false) Integer numberOfOffices
                                 ) {
        try {
            Company existingCompany = companyService.getCompanyById(id);
            if (existingCompany != null) {
                if (companyName != null && !companyName.isEmpty()) {
                    existingCompany.setCompanyName(companyName);
                }
                if (bulstat != null) {
                    existingCompany.setBulstat(bulstat);
                }
                if (numberOfOffices != null) {
                    existingCompany.setNumberOfOffices(numberOfOffices);
                }
                companyService.updateCompany(existingCompany);
            }
            return "updateCompany_page.html";
        } catch (Exception e) {
            System.err.println("Error updating company: " + e.getMessage());
            return "error_page.html"; // Error page
        }
    }

    @PostMapping("/delete")
    public String deleteCompany(@RequestParam Long id) {
        Company company = new Company();
        company.setId(id);
        companyService.deleteCompany(company);
        return "deleteCompany_page.html";
    }


}

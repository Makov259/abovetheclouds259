package com.simpleform.controller;

import com.simpleform.entity.Company;
import com.simpleform.entity.Office;
import com.simpleform.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/office")
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @PostMapping("/add")
    public String addOffice(Office office) {
        System.out.println("Received company data: " + office);
        officeService.addOffice(office);
        return "addOffice_page.html";
    }

    @PostMapping("/update")
    public String updateCompany(@RequestParam("id") Long id,
                                @RequestParam(value = "location", required = false) String location,
                                @RequestParam(value = "numberOfEmployees", required = false) Integer numberOfEmployees,
                                @RequestParam(value = "officeContactNumber", required = false) String officeContactNumber
    ) {
        try {
            Office existingOffice = officeService.getOfficeById(id);
            if (existingOffice != null) {
                boolean isUpdated = false;
                if (location != null && !location.trim().isEmpty()) {
                    existingOffice.setLocation(location);
                    isUpdated = true;
                }
                if (numberOfEmployees != null) {
                    existingOffice.setNumberOfEmployees(numberOfEmployees);
                    isUpdated = true;
                }
                // Only update the contact number if it's provided and not empty
                if (officeContactNumber != null && !officeContactNumber.trim().isEmpty()) {
                    existingOffice.setOfficeContactNumber(officeContactNumber);
                    isUpdated = true;
                }
                // Save the changes only if at least one field was updated
                if (isUpdated) {
                    officeService.updateOffice(existingOffice);
                }
            }
            return "updateOffice_page.html";
        } catch (Exception e) {
            System.err.println("Error updating office: " + e.getMessage());
            return "error_page.html"; // Error page
        }
    }


    @PostMapping("/delete")
    public String deleteOffice(@RequestParam Long id) {
        Office office = new Office();
        office.setId(id);
        officeService.deleteOffice(office);
        return "deleteOffice_page.html";
    }

}

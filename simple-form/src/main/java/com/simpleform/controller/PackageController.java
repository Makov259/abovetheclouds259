package com.simpleform.controller;

import com.simpleform.entity.Office;
import com.simpleform.entity.Package;
import com.simpleform.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/package")
public class PackageController {

    @Autowired
    PackageService packageService;

    @PostMapping("/add")
    public String addPackage(Package package_) {
        System.out.println("Received company data: " + package_);
        packageService.addPackage(package_);
        return "addPackage_page.html";
    }


    @PostMapping("/update")
    public String updateCompany(@RequestParam("id") Long id,
                                @RequestParam(value = "sender", required = false) String sender,
                                @RequestParam(value = "recipient", required = false) String recipient,
                                @RequestParam(value = "deliveryAddress", required = false) String deliveryAddress,
                                @RequestParam(value = "weight", required = false) String weight
    ) {
        try {
            Package existingPackage = packageService.getPackageById(id);
            if (existingPackage != null) {
                boolean isUpdated = false;
                if (sender != null && !sender.trim().isEmpty()) {
                    existingPackage.setSender(sender);
                    isUpdated = true;
                }
                if (recipient != null && !recipient.trim().isEmpty()) {
                    existingPackage.setRecipient(recipient);
                    isUpdated = true;
                }
                // Only update the contact number if it's provided and not empty
                if (deliveryAddress != null && !deliveryAddress.trim().isEmpty()) {
                    existingPackage.setDeliveryAddress(deliveryAddress);
                    isUpdated = true;
                }
                if (weight != null && !weight.trim().isEmpty()) {
                    existingPackage.setWeight(weight);
                    isUpdated = true;
                }
                // Save the changes only if at least one field was updated
                if (isUpdated) {
                    packageService.updatePackage(existingPackage);
                }
            }
            return "updatePackage_page.html";
        } catch (Exception e) {
            System.err.println("Error updating office: " + e.getMessage());
            return "error_page.html"; // Error page
        }
    }


    @PostMapping("/delete")
    public String deletePackage(@RequestParam Long id) {
        Package package_ = new Package();
        package_.setId(id);
        packageService.deletePackage(package_);
        return "deletePackage_page.html";
    }
}

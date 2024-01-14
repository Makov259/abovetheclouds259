package com.simpleform.service;


import com.simpleform.entity.Company;
import com.simpleform.entity.Employee;
import com.simpleform.entity.Office;
import com.simpleform.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OfficeService {

    @Autowired
    OfficeRepository officeRepository;

    public void addOffice(Office office){
        officeRepository.save(office);
    }

    public void updateOffice(Office updatedOffice) {
        Office existingOffice = officeRepository.findById(updatedOffice.getId()).orElse(null);
        if (existingOffice != null) {
            boolean shouldSave = false;

            if (updatedOffice.getLocation() != null && !updatedOffice.getLocation().isEmpty()) {
                existingOffice.setLocation(updatedOffice.getLocation());
                shouldSave = true;
            }
            if (updatedOffice.getNumberOfEmployees() != null) {
                existingOffice.setNumberOfEmployees(updatedOffice.getNumberOfEmployees());
                shouldSave = true;
            }
            if (updatedOffice.getOfficeContactNumber() != null && !updatedOffice.getOfficeContactNumber().isEmpty()) {
                existingOffice.setOfficeContactNumber(updatedOffice.getOfficeContactNumber());
                shouldSave = true;
            }

            if (shouldSave) {
                officeRepository.save(existingOffice);
            }
        }
    }


    public void deleteOffice(Office office){
        Office existingOffice = officeRepository.findById(office.getId()).orElse(null);
        if(existingOffice != null){
            officeRepository.delete(existingOffice);
        }

    }

    public Office getOfficeById(Long id) {
        Optional<Office> optionalOffice = officeRepository.findById(id);
        return optionalOffice.orElse(null);
    }
}

package com.simpleform.service;

import com.simpleform.entity.Office;
import com.simpleform.entity.Package;
import com.simpleform.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PackageService {

    @Autowired
    PackageRepository packageRepository;

    public void addPackage(Package package_){
        packageRepository.save(package_);
    }

    public void updatePackage(Package updatedPackage) {
        Package existingPackage = packageRepository.findById(updatedPackage.getId()).orElse(null);
        if (existingPackage != null) {
            boolean shouldSave = false;

            if (updatedPackage.getSender() != null && !updatedPackage.getSender().isEmpty()) {
                existingPackage.setSender(updatedPackage.getSender());
                shouldSave = true;
            }
            if (updatedPackage.getRecipient() != null && !updatedPackage.getRecipient().isEmpty()) {
                existingPackage.setRecipient(updatedPackage.getRecipient());
                shouldSave = true;
            }
            if (updatedPackage.getDeliveryAddress() != null && !updatedPackage.getDeliveryAddress().isEmpty()) {
                existingPackage.setDeliveryAddress(updatedPackage.getDeliveryAddress());
                shouldSave = true;
            }

            if (updatedPackage.getWeight() != null) {
                existingPackage.setWeight(updatedPackage.getWeight());
                shouldSave = true;
            }

            if (shouldSave) {
                packageRepository.save(existingPackage);
            }
        }
    }


    public void deletePackage(Package package_){
        Package existingPackage = packageRepository.findById(package_.getId()).orElse(null);
        if(existingPackage != null){
            packageRepository.delete(existingPackage);
        }

    }

    public Package getPackageById(Long id) {
        Optional<Package> optionalPackage = packageRepository.findById(id);
        return optionalPackage.orElse(null);
    }
}

package com.simpleform.service;

import com.simpleform.entity.Company;
import com.simpleform.entity.Employee;
import com.simpleform.repository.ClientRepository;
import com.simpleform.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public void addCompany(Company company) {
        companyRepository.save(company);
    }




    public void deleteCompany(Company company){
        // You might need to fetch the employee from the repository before deleting
        Company existingCompany = companyRepository.findById(company.getId()).orElse(null);
        if (existingCompany != null) {
            companyRepository.delete(existingCompany);
        }
    }


    public void updateCompany(Company updatedCompany) {
        Company existingCompany = companyRepository.findById(updatedCompany.getId()).orElse(null);
        if (existingCompany != null) {
            if (updatedCompany.getCompanyName() != null && !updatedCompany.getCompanyName().isEmpty()) {
                existingCompany.setCompanyName(updatedCompany.getCompanyName());
            }
            if (updatedCompany.getBulstat() != null) {
                existingCompany.setBulstat(updatedCompany.getBulstat());
            }
            if (updatedCompany.getNumberOfOffices() != null) {
                existingCompany.setNumberOfOffices(updatedCompany.getNumberOfOffices());
            }

            companyRepository.save(existingCompany);
        }
    }


    public Company getCompanyById(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(null);
    }
}



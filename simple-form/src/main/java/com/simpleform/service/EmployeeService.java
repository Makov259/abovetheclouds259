package com.simpleform.service;

import com.simpleform.dto.EmployeeDTO;
import com.simpleform.dto.clientDTO;
import com.simpleform.entity.Client;
import com.simpleform.entity.Employee;
import com.simpleform.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UsersService usersService;


    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);

        // Create user details
        String firstName = employee.getFirstName();
        String email = firstName + "@gmail.com";
        String password = firstName + "123456";

        // Register the employee as a user with the role of 'employeeUser'
        usersService.registerEmployeeUser(firstName, password, email);
    }




    public void deleteEmployee(Employee employee){
        // You might need to fetch the employee from the repository before deleting
        Employee existingEmployee = employeeRepository.findById(employee.getId()).orElse(null);
        if (existingEmployee != null) {
            employeeRepository.delete(existingEmployee);
        }
    }


    public void updateEmployee(Employee updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(updatedEmployee.getId()).orElse(null);
        if (existingEmployee != null) {
            if (updatedEmployee.getFirstName() != null && !updatedEmployee.getFirstName().isEmpty()) {
                existingEmployee.setFirstName(updatedEmployee.getFirstName());
            }
            if (updatedEmployee.getLastName() != null && !updatedEmployee.getLastName().isEmpty()) {
                existingEmployee.setLastName(updatedEmployee.getLastName());
            }
            if (updatedEmployee.getAge() != null) {
                existingEmployee.setAge(updatedEmployee.getAge());
            }
            if (updatedEmployee.getSalary() != null) {
                existingEmployee.setSalary(updatedEmployee.getSalary());
            }

            employeeRepository.save(existingEmployee);
        }
    }


    public Employee getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.orElse(null);
    }
}

package com.simpleform.controller;
import com.simpleform.dto.EmployeeDTO;
import com.simpleform.dto.clientDTO;
import com.simpleform.entity.Employee;
import com.simpleform.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public String addEmployee(Employee employee) {
        System.out.println("Received employee data: " + employee);
        employeeService.addEmployee(employee);
        return "addEmployee_page.html";
    }

    @PostMapping("/delete")
    public String deleteEmployee(@RequestParam Long id) {
        Employee employee = new Employee();
        employee.setId(id);
        employeeService.deleteEmployee(employee);
        return "deleteEmployee_page.html";
    }


    @PostMapping("/update")
    public String updateEmployee(@RequestParam("id") Long id,
                                 @RequestParam(value = "firstName", required = false) String firstName,
                                 @RequestParam(value = "lastName", required = false) String lastName,
                                 @RequestParam(value = "age", required = false) Integer age,
                                 @RequestParam(value = "salary", required = false) Double salary) {
        try {
            Employee existingEmployee = employeeService.getEmployeeById(id);
            if (existingEmployee != null) {
                if (firstName != null && !firstName.isEmpty()) {
                    existingEmployee.setFirstName(firstName);
                }
                if (lastName != null && !lastName.isEmpty()) {
                    existingEmployee.setLastName(lastName);
                }
                if (age != null) {
                    existingEmployee.setAge(age);
                }
                if (salary != null) {
                    existingEmployee.setSalary(salary);
                }
                employeeService.updateEmployee(existingEmployee);
            }
            return "updateEmployee_page.html";
        } catch (Exception e) {
            System.err.println("Error updating employee: " + e.getMessage());
            return "error_page.html"; // Error page
        }
    }


}

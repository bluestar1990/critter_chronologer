package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private EmployeeService employeeService;

    public UserController(UserService userService, EmployeeService employeeService){
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setNotes(customerDTO.getNotes());
        Customer customerResult  = userService.saveCustomer(customer);
        CustomerDTO customerDTOResult = new CustomerDTO();
        customerDTOResult.setId(customerResult.getId());
        customerDTOResult.setName(customerResult.getName());
        customerDTOResult.setPhoneNumber(customerResult.getPhoneNumber());
        customerDTOResult.setNotes(customerResult.getNotes());

        return customerDTOResult;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        List<Customer> customers = userService.getAllCustomer();
        customers.forEach(c -> {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setPhoneNumber(c.getPhoneNumber());
            customerDTO.setId(c.getId());
            customerDTO.setName(c.getName());
            customerDTO.setNotes(c.getNotes());
            customerDTO.setPetIds(c.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
            customerDTOS.add(customerDTO);
        });
        return customerDTOS;
    }

    // customer = user
    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer c = userService.findCustomerByPetId(petId);
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setPhoneNumber(c.getPhoneNumber());
        customerDTO.setId(c.getId());
        customerDTO.setName(c.getName());
        customerDTO.setNotes(c.getNotes());
        customerDTO.setPetIds(c.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        return customerDTO;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSkills(employeeDTO.getSkills());
        Employee employeeResult =  employeeService.saveEmployee(employee);

        EmployeeDTO employeeDTOResult = new EmployeeDTO();
        employeeDTOResult.setId(employeeResult.getId());
        employeeDTOResult.setSkills(employeeResult.getSkills());
        employeeDTOResult.setName(employeeResult.getName());
        return employeeDTOResult;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {

        EmployeeDTO employeeDTO = new EmployeeDTO();
        Optional<Employee> optionalEmployee = employeeService.findEmployeeById(employeeId);
        if(optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();
            employeeDTO.setName(employee.getName());
            employeeDTO.setSkills(employee.getSkills());
            employeeDTO.setId(employee.getId());
            employeeDTO.setDaysAvailable(employee.getDaysAvailable());
        }
        return employeeDTO;
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        Optional<Employee> optionalEmployee =  employeeService.findEmployeeById(employeeId);
        if(optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();
            employee.setDaysAvailable(daysAvailable);
            employeeService.saveEmployee(employee);
        }
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTORequest) {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        Set<EmployeeSkill> employeeSet = employeeDTORequest.getSkills();
        EmployeeSkill employeeSkill = null;
        for (Iterator<EmployeeSkill> it = employeeSet.iterator(); it.hasNext(); ) {
            employeeSkill = it.next();

        }
        List<Employee> employees = employeeService.findEmployeesByDate(employeeSkill);
        employees.forEach(e -> {
            EmployeeDTO employeeDTOResult = new EmployeeDTO();
            employeeDTOResult.setId(e.getId());
            employeeDTOResult.setSkills(e.getSkills());
            employeeDTOResult.setName(e.getName());
            employeeDTOResult.setDaysAvailable(e.getDaysAvailable());
            employeeDTOS.add(employeeDTOResult);
        });
        return employeeDTOS;
    }

}

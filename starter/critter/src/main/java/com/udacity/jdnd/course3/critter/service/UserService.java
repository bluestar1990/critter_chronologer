package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class UserService {

    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Customer saveCustomer(Customer customer){
        return userRepository.save(customer);
    }

    public List<Customer> getAllCustomer(){
        return userRepository.findAll();
    }

    public Optional<Customer> findOne(long empId){
        return userRepository.findById(empId);
    }

    public Customer findCustomerByPetId(long petId){
        return  userRepository.findCustomerByPetId(petId);
    }
}

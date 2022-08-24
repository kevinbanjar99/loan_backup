package com.danamon.livecodeloan.service.loan.impl;

import com.danamon.livecodeloan.dto.loan.FileResponseDTO;
import com.danamon.livecodeloan.entity.loan_entity.Customer;
import com.danamon.livecodeloan.entity.loan_entity.ProfilePicture;
import com.danamon.livecodeloan.repo.loan_repo.CustomerRepository;
import com.danamon.livecodeloan.repo.loan_repo.ProfilePictureRepository;
import com.danamon.livecodeloan.service.loan.CustomerService;
import com.danamon.livecodeloan.utils.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerByID(String id) {
        if(customerRepository.findById(id).isPresent()){
            return customerRepository.findById(id).get();
        }
        else throw new DataNotFoundException("Data Not Found");
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        if(customerRepository.findById(customer.getId()).isPresent()){
            return customerRepository.save(customer);
        }
        else throw new DataNotFoundException("Data customer with selected id isn't found");
    }

    @Override
    public void deleteCustomer(String id) {
        if(customerRepository.findById(id).isPresent()){
            customerRepository.deleteById(id);
        }
        else throw new DataNotFoundException("Data customer with selected id isn't found");
    }

}

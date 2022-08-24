package com.danamon.livecodeloan.service.loan;

import com.danamon.livecodeloan.dto.loan.FileResponseDTO;
import com.danamon.livecodeloan.entity.loan_entity.Customer;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> getAllCustomer();
    Customer getCustomerByID(String id);
    Customer updateCustomer(Customer customer);
    void deleteCustomer(String id);

}

package com.danamon.livecodeloan.controller;

import com.danamon.livecodeloan.constant.ResponseMessage;
import com.danamon.livecodeloan.dto.loan.CustomerDTO;
import com.danamon.livecodeloan.dto.loan.FileResponseDTO;
import com.danamon.livecodeloan.entity.loan_entity.Customer;
import com.danamon.livecodeloan.service.loan.CustomerService;
import com.danamon.livecodeloan.service.loan.LoanDocumentsService;
import com.danamon.livecodeloan.service.loan.ProfilePictureService;
import com.danamon.livecodeloan.utils.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    CustomerService customerService;
    ProfilePictureService profilePictureService;
    LoanDocumentsService loanDocumentsService;

    @Autowired
    public CustomerController(CustomerService customerService, ProfilePictureService profilePictureService, LoanDocumentsService loanDocumentsService) {
        this.customerService = customerService;
        this.profilePictureService = profilePictureService;
        this.loanDocumentsService = loanDocumentsService;
    }

    @PostMapping
    public ResponseEntity<Response<CustomerDTO>> createCustomer(@RequestBody Customer customer){
        String message = String.format(ResponseMessage.DATA_CREATED, ResponseMessage.COSTUMER);
        CustomerDTO customerDTO = new CustomerDTO(customerService.saveCustomer(customer));
        Response<CustomerDTO> response = new Response<>(message, customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<CustomerDTO>>> getAllCustomer(){
        String message = String.format(ResponseMessage.DATA_FETCHED_ALL, ResponseMessage.COSTUMER );
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer c:customerService.getAllCustomer()) {
            customerDTOList.add(new CustomerDTO(c));
        }
        Response<List<CustomerDTO>> response = new Response<>(message, customerDTOList);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<CustomerDTO>> getCustomerById(@PathVariable String id){
        String message = String.format(ResponseMessage.DATA_FETCHED_BY_ID, ResponseMessage.COSTUMER);
        Response<CustomerDTO> response = new Response<>(message, new CustomerDTO(customerService.getCustomerByID(id)));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PutMapping
    public ResponseEntity<Response<Customer>> updateCustomer(@RequestBody Customer customer){
        String message = String.format(ResponseMessage.DATA_UPDATED, ResponseMessage.COSTUMER);
        Response<Customer> response = new Response<>(message, customerService.updateCustomer(customer));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> deleteCustomerById(@PathVariable String id){
            customerService.deleteCustomer(id);
            String message = String.format(ResponseMessage.DATA_DELETED, ResponseMessage.COSTUMER);
            Response<?> response = new Response<>(message,null);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON).
                    body(response);
        }

    @PostMapping("/{customerId}/upload/avatar")
    public ResponseEntity<?> uploadImage(@PathVariable String customerId,
                                         @RequestPart MultipartFile profileImage) {
        FileResponseDTO upload = profilePictureService.uploadAvatar(profileImage, customerId);
        Response<FileResponseDTO> response = new Response<>("successfully upload avatar", upload);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{customerId}/avatar")
    public ResponseEntity<?>downloadImage(@PathVariable String customerId){
        Resource download = profilePictureService.downloadAvatar(customerId);
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" +
                        download.getFilename() + "\"").body(download);
    }

    @PostMapping("/{customerId}/documents")
    public ResponseEntity<?> uploadDocument(@PathVariable String customerId,
                                         @RequestPart MultipartFile document) {
        FileResponseDTO upload = loanDocumentsService.uploadDocuments(document, customerId);
        Response<FileResponseDTO> response = new Response<>("successfully upload avatar", upload);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{documentId}/documents")
    public ResponseEntity<?>downloadDocument(@PathVariable String documentId){
        Resource download = loanDocumentsService.downloadDocuments(documentId);
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" +
                        download.getFilename() + "\"").body(download);
    }
}



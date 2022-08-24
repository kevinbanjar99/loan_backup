package com.danamon.livecodeloan.controller;

import com.danamon.livecodeloan.constant.ResponseMessage;
import com.danamon.livecodeloan.entity.loan_entity.LoanType;
import com.danamon.livecodeloan.service.loan.LoanTypeService;
import com.danamon.livecodeloan.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/loan-types")
public class LoanTypeController {

    LoanTypeService loanTypeService;

    @Autowired
    public LoanTypeController(LoanTypeService loanTypeService) {
        this.loanTypeService = loanTypeService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    public ResponseEntity<Response<LoanType>> createLoanType(@RequestBody LoanType loanType){
        String message = String.format(ResponseMessage.DATA_CREATED, ResponseMessage.LOAN_TYPES);
        LoanType loanType1 = loanTypeService.saveLoanType(loanType);
        Response<LoanType> response = new Response<>(message, loanType1);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<LoanType>>> getAllLoanType(){
        String message = String.format(ResponseMessage.DATA_FETCHED_ALL, ResponseMessage.LOAN_TYPES);
        List<LoanType> loanTypeList = new ArrayList<>(loanTypeService.getAllLoanType());
        Response<List<LoanType>> response = new Response<>(message, loanTypeList);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<LoanType>> getLoanTypeById(@PathVariable String id){
        String message = String.format(ResponseMessage.DATA_FETCHED_BY_ID, ResponseMessage.LOAN_TYPES);
        Response<LoanType> response = new Response<>(message, loanTypeService.getLoanTypeById(id));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    public ResponseEntity<Response<LoanType>> updateLoanType(@RequestBody LoanType loanType){
        String message = String.format(ResponseMessage.DATA_UPDATED, ResponseMessage.LOAN_TYPES);
        Response<LoanType> response = new Response<>(message,loanTypeService.updateLoanType(loanType));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> deleteLoanTypeById(@PathVariable String id){
        loanTypeService.deleteLoanType(id);
        String message = String.format(ResponseMessage.DATA_DELETED, ResponseMessage.LOAN_TYPES);
        Response<?> response = new Response<>(message,null);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON).
                body(response);
    }
}

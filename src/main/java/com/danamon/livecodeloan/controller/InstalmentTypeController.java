package com.danamon.livecodeloan.controller;

import com.danamon.livecodeloan.constant.ResponseMessage;
import com.danamon.livecodeloan.dto.loan.CustomerDTO;
import com.danamon.livecodeloan.entity.loan_entity.Customer;
import com.danamon.livecodeloan.entity.loan_entity.InstalmentType;
import com.danamon.livecodeloan.service.loan.InstalmentTypeService;
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
@RequestMapping("/api/instalment-types")
public class InstalmentTypeController {
    InstalmentTypeService instalmentTypeService;

    @Autowired
    public InstalmentTypeController(InstalmentTypeService instalmentTypeService) {
        this.instalmentTypeService = instalmentTypeService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    public ResponseEntity<Response<InstalmentType>> createInstalmentType(@RequestBody InstalmentType instalmentType){
        String message = String.format(ResponseMessage.DATA_CREATED, ResponseMessage.INSTALMENT_TYPES);
        InstalmentType instalmentType1 = instalmentTypeService.saveInstalmentType(instalmentType);
        Response<InstalmentType> response = new Response<>(message, instalmentType1);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<InstalmentType>>> getAllInstalmentType(){
        String message = String.format(ResponseMessage.DATA_FETCHED_ALL, ResponseMessage.INSTALMENT_TYPES );
        List<InstalmentType> instalmentTypeList = new ArrayList<>(instalmentTypeService.getAllInstalmentType());
        Response<List<InstalmentType>> response = new Response<>(message, instalmentTypeList);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<InstalmentType>> getInstalmentTypeById(@PathVariable String id){
        String message = String.format(ResponseMessage.DATA_FETCHED_BY_ID, ResponseMessage.INSTALMENT_TYPES);
        Response<InstalmentType> response = new Response<>(message, instalmentTypeService.getInstalmentTypeById(id));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    public ResponseEntity<Response<InstalmentType>> updateInstalmentType(@RequestBody InstalmentType instalmentType){
        String message = String.format(ResponseMessage.DATA_UPDATED, ResponseMessage.INSTALMENT_TYPES);
        Response<InstalmentType> response = new Response<>(message,instalmentTypeService.updateInstalmentType(instalmentType));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> deleteInstalmentTypeById(@PathVariable String id){
        instalmentTypeService.deleteInstalmentType(id);
        String message = String.format(ResponseMessage.DATA_DELETED, ResponseMessage.INSTALMENT_TYPES);
        Response<?> response = new Response<>(message,null);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON).
                body(response);
    }
}

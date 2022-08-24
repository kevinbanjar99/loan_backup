package com.danamon.livecodeloan.controller;

import com.danamon.livecodeloan.constant.ResponseMessage;
import com.danamon.livecodeloan.dto.loan.AdminApprovalDTO;
import com.danamon.livecodeloan.dto.loan.FileResponseDTO;
import com.danamon.livecodeloan.dto.loan.LoanTransactionDTO;
import com.danamon.livecodeloan.entity.loan_entity.LoanTransaction;
import com.danamon.livecodeloan.entity.loan_entity.LoanType;
import com.danamon.livecodeloan.service.loan.GuaranteePictureService;
import com.danamon.livecodeloan.service.loan.LoanTransactionService;
import com.danamon.livecodeloan.service.loan.LoanTypeService;
import com.danamon.livecodeloan.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    LoanTransactionService loanTransactionService;
    GuaranteePictureService guaranteePictureService;

    @Autowired
    public TransactionController(LoanTransactionService loanTransactionService, GuaranteePictureService guaranteePictureService) {
        this.loanTransactionService = loanTransactionService;
        this.guaranteePictureService = guaranteePictureService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<Response<LoanTransactionDTO>> requestLoan(@RequestBody LoanTransaction loanTransaction){
        String message = String.format(ResponseMessage.DATA_CREATED, ResponseMessage.LOAN_REQUEST);
        LoanTransactionDTO loanTransactionDTO = new LoanTransactionDTO(loanTransactionService.requestLoan(loanTransaction));
        Response<LoanTransactionDTO> response = new Response<>(message, loanTransactionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<LoanTransactionDTO>> getTransactionById(@PathVariable String id){
        String message = String.format(ResponseMessage.DATA_FETCHED_BY_ID, ResponseMessage.LOAN_TRANSACTION);
        Response<LoanTransactionDTO> response = new Response<>(message, new LoanTransactionDTO(loanTransactionService.getTransactionById(id)));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PutMapping("/{adminId}/approve")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Response<LoanTransactionDTO>> ApproveTransactionRequestByAdminId(@PathVariable String adminId,
                                                                                           @RequestBody AdminApprovalDTO adminApprovalDTO){
        String message = String.format(ResponseMessage.REQUEST_APPROVED, ResponseMessage.ADMIN);
        LoanTransaction loanTransaction = loanTransactionService.approveTransactionRequestByAdminId(adminId, adminApprovalDTO);
        Response<LoanTransactionDTO> response = new Response<>(message, new LoanTransactionDTO(loanTransaction));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PutMapping("/{trxId}/pay")
    public ResponseEntity<?> payInstalment(@PathVariable String trxId,
                                         @RequestPart MultipartFile guaranteePicture) {
        FileResponseDTO upload = guaranteePictureService.uploadGuaranteePicture(guaranteePicture, trxId);
        Response<FileResponseDTO> response = new Response<>("successfully upload avatar", upload);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{fileId}/files")
    public ResponseEntity<?>downloadGuaranteePicture(@PathVariable String fileId){
        Resource download = guaranteePictureService.downloadGuaranteePicture(fileId);
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" +
                        download.getFilename() + "\"").body(download);
    }


}

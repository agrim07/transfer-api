package com.ixaris.interview.transfers.controller;

import com.ixaris.interview.transfers.model.TransactionResponse;
import com.ixaris.interview.transfers.service.TransferService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
@Log4j2
public class TransferController {

    private TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping("/transfer/{fileName}")
    public ResponseEntity<TransactionResponse> transfer(@PathVariable(required = true) String fileName) {
        log.info("Request recieved for file {}.", fileName);
        TransactionResponse response = this.transferService.processTransfer(fileName);
        return ResponseEntity.ok(response);
    }
}

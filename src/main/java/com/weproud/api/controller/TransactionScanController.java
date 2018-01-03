package com.weproud.api.controller;

import com.weproud.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Logan. 81k
 */
@RestController
@RequestMapping("/tx")
public class TransactionScanController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/{hash}")
    public ResponseEntity<?> getTx(@PathVariable String hash) {
        return new ResponseEntity<>(this.transactionService.getTransaction(hash), HttpStatus.OK);
    }
}

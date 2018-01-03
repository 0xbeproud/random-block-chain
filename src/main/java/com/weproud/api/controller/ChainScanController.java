package com.weproud.api.controller;

import com.weproud.api.service.ChainScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Logan. 81k
 */
@RestController
@RequestMapping("/chain")
public class ChainScanController {
    @Autowired
    private ChainScanService chainScanService;

    @GetMapping(value = "")
    public ResponseEntity<?> getBlock() {
        return new ResponseEntity<>(this.chainScanService.getChain(), HttpStatus.OK);
    }
}

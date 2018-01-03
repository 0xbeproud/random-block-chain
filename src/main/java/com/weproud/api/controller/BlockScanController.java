package com.weproud.api.controller;

import com.weproud.api.service.BlockScanService;
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
@RequestMapping("/blocks")
public class BlockScanController {

    @Autowired
    private BlockScanService blockScanService;

    @GetMapping(value = "/{height}")
    public ResponseEntity<?> getBlock(@PathVariable Integer height) {
        return new ResponseEntity<>(this.blockScanService.getBlockByHeight(height), HttpStatus.OK);
    }
}

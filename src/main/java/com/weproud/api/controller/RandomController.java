package com.weproud.api.controller;

import com.weproud.api.service.RandomService;
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
@RequestMapping("/random")
public class RandomController {
    @Autowired
    private RandomService randomService;

    @GetMapping(value = "/{limit}")
    public ResponseEntity<?> getTx(@PathVariable int limit) {
        ;
        return new ResponseEntity<>(this.randomService.getRandomNumber(limit), HttpStatus.OK);
    }
}

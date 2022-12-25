package com.bimbitsoft.hexagonal.eidv.adapter.in;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EIDVController {
    ResponseEntity createNewApplicant() {
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

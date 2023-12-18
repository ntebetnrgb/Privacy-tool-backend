package com.anonymous.privacytool.controller;

import com.anonymous.privacytool.entity.CredentialVault;
import com.anonymous.privacytool.service.CredentialVaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/credentials")
public class CredentialVaultController {
    private final CredentialVaultService service;

    @Autowired
    public CredentialVaultController(CredentialVaultService service) {
        this.service = service;
    }

    @GetMapping
    public List<CredentialVault> getAllCredentials() {
        return service.getAllCredentials();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CredentialVault> getCredentialById(@PathVariable UUID id) {
        Optional<CredentialVault> credential = service.getCredentialById(id);
        return credential.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<CredentialVault> saveCredential(@RequestBody CredentialVault credentialVault) {
        CredentialVault savedCredential = service.saveCredential(credentialVault);
        return new ResponseEntity<>(savedCredential, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredential(@PathVariable UUID id) {
        service.deleteCredential(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

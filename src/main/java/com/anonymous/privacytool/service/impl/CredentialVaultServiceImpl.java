package com.anonymous.privacytool.service.impl;

import com.anonymous.privacytool.service.CredentialVaultService;
import org.springframework.stereotype.Service;

import com.anonymous.privacytool.entity.CredentialVault;
import com.anonymous.privacytool.repository.CredentialVaultRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CredentialVaultServiceImpl implements CredentialVaultService {
    private final CredentialVaultRepository repository;

    @Autowired
    public CredentialVaultServiceImpl(CredentialVaultRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CredentialVault> getAllCredentials() {

        return repository.findAll();
    }

    @Override
    public Optional<CredentialVault> getCredentialById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public CredentialVault saveCredential(CredentialVault credentialVault) {
        return repository.save(credentialVault);
    }

    @Override
    public void deleteCredential(UUID id) {
        repository.deleteById(id);
    }
}

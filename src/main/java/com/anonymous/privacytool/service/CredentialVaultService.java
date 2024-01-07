package com.anonymous.privacytool.service;

import com.anonymous.privacytool.entity.CredentialVault;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CredentialVaultService {
    public List<CredentialVault> getAllCredentials();
    CredentialVault saveCredential(CredentialVault credentialVault);
    void deleteCredential(UUID id);

    Optional<CredentialVault> getCredentialById(UUID id);

}

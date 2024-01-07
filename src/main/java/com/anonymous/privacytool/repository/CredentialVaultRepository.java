package com.anonymous.privacytool.repository;

import com.anonymous.privacytool.entity.CredentialVault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CredentialVaultRepository extends JpaRepository<CredentialVault,UUID> {
    Optional<CredentialVault> findByAppName(String appName);
}

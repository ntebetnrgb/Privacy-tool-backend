package com.anonymous.privacytool.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "auth_user")
@Getter
@Setter
@AllArgsConstructor
public class AuthUser {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "master_pub_key")
    private String masterPubKey;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "verification_code")
    private String verificationCode;

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @Column(name = "updated_by")
    private String updatedBy;
}

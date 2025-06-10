package com.project.shopapp.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="social_accounts")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn (name="user_id")
    private User userId;

    @Column(name="provider_id")
    private String  providerId;

    @Column(name="provider")
    private String  provider;

    @Column(name="email")
    private String  email;

    @Column(name="name")
    private String  name;

}

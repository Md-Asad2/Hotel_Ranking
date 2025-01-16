package com.maspez.user_service.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @Column(name = "id")
    private String userId;

    @Column(length = 20)
    private String name;

    @Email(message = "Please provide a valid email address")
    @Column(unique = true)
    private String email;
    private String city;

    @Transient
    private List<Rating> ratings;
}

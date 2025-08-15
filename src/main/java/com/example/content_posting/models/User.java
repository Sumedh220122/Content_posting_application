package com.example.content_posting.models;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Generates default constructor
public class User {
    @Id
    public UUID id;

    @Column(name = "username")
    public String username;

    @Column(name = "email_id")
    public String emailId;

    @Column(name = "password")
    public String password;

    @Column(name = "num_followers")
    public int num_followers;

    @Column(name = "num_following")
    public int num_following;

    public User(UUID id, String username, String emailId,
                String password, int nf, int n_fol){
        this.id = id;
        this.username = username;
        this.emailId = emailId;
        this.password = password;
        this.num_followers = nf;
        this.num_following = n_fol;
    }
}
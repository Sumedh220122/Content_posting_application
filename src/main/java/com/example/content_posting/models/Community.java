package com.example.content_posting.models;

import java.util.UUID;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "communities")
@Data // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Generates default constructor
public class Community {
    @Id
    UUID id;

    @Column(name = "name")
    String name;

    @Column(name = "tags")
    String tags;

    public Community(UUID id, String name, String tags){
        this.id = id;
        this.name = name;
        this.tags = tags;
    }
}

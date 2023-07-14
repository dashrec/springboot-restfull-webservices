package net.javaguides.springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // automatically creates constructor
@AllArgsConstructor
@Entity // specifies this class has gpa entity
@Table(name = "users") // creates table users
public class User {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // increments primary key
    private Long id;
    @Column(nullable = false) // firstName not null
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true) // make email unique
    private String email;
}

// Client--> controller layer  --> service layer --> repository layer/jpa --> DB
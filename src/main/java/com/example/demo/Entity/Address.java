package com.example.demo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "TIMESTAMP NOT NULL" +
        " DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false
    )
    private LocalDateTime insertTime;
    private String detail;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private User user;
    public Address(String detail) {
        this.detail = detail;
    }
}

package com.example.demo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "timestamp not null " +
            "default current_timestamp",
            updatable = false ,
            insertable = false
    )
    private LocalDateTime insertTime;
    private String name;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    private List<Address> addresses;
    public User(String name) {
        this.name = name;
    }
}

package com.mult.database.multdatabase.db2.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Pessoa implements Serializable {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    private String name;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

}

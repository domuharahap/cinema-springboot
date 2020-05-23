package com.cinema.authentication.domain;

import com.cinema.authentication.utils.RoleName;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    private RoleName name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
    public Roles() {
        super();
    }

    public Roles(Long id, RoleName name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}

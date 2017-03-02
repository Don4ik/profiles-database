package com.roganin.cv.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "profiles")
@Data
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    @ElementCollection(targetClass = Skills.class)
    @JoinTable(name = "skills")
    @Enumerated(EnumType.STRING)
    private Set<Skills> skills = new HashSet<>();
    @Lob
    private byte[] cv;

}

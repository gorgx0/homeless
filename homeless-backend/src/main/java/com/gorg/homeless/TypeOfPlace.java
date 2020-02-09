package com.gorg.homeless;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="TYPES")
@AllArgsConstructor
@NoArgsConstructor
public class TypeOfPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String icon;
    private String name;
    private String shortname;
    private int ordering;

}

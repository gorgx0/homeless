package com.gorg.homeless;

import lombok.Data;

@Data
public class TypeOfPlace {
    private final long id;
    private final String icon;
    private final String name;
    private final String shortname;
    private final int ordering;
}

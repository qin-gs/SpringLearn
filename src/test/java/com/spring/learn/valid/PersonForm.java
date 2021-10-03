package com.spring.learn.valid;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonForm {

    @NotNull
    @Size(max = 64)
    private String name;

    @Min(0)
    private int age;
}

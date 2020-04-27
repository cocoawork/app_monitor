package com.cocoawork.appstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String uid;

    @NotNull
    private String userName;

    @NotNull
    @Length(min = 8, max = 16)
    @JsonIgnore
    private String password;

    private String phoneNum;

    private Integer gender;

    private Integer age;
}

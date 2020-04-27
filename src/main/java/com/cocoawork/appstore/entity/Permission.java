package com.cocoawork.appstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Permission {

    private Integer id;
    private String perAuth;
    private String perTitle;

}

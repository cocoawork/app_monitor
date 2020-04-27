package com.cocoawork.appstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {
    private Integer id;
    private String roleName;
    private String roleTitle;
    private List<Permission> permissions;
}

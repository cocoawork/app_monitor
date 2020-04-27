package com.cocoawork.appstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feed {

    private Integer mediaValue;
    private String feedName;
    private String feedValue;

}

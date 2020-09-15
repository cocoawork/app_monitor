package top.cocoawork.monitor.email.domain;


import lombok.Data;

import java.io.Serializable;

@Data
public class Email implements Serializable {
    private String from;
    private String to;
    private String subject;
    private String content;

}

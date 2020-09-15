package top.cocoawork.monitor.fetcher.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Email implements Serializable {
    private String to;
    private String subject;
    private String content;

}
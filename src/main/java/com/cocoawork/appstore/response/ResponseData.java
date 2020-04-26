package com.cocoawork.appstore.response;

import lombok.Data;

@Data
public class ResponseData<T> extends Response {
    private T data;

    public ResponseData(T data) {
        super();
        this.data = data;
    }

}

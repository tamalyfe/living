package com.example.living.data.remote.response.requestCustomer;

import java.util.List;

public class ResponseRequestCustomer {
    String value;
    String message;
    List<ItemResponseRequestCustomer> result;

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public List<ItemResponseRequestCustomer> getResult() {
        return result;
    }
}

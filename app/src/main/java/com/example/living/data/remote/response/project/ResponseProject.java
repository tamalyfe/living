package com.example.living.data.remote.response.project;

import java.util.List;

public class ResponseProject {
    String value;
    String message;
    List<ItemResponseProject> result;

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public List<ItemResponseProject> getResult() {
        return result;
    }
}

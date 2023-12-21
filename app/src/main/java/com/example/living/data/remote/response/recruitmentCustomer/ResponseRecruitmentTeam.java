package com.example.living.data.remote.response.recruitmentCustomer;

import java.util.List;

public class ResponseRecruitmentTeam {
    String value;
    String message;
    List<ItemResponseRecruitmentTeam> result;

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public List<ItemResponseRecruitmentTeam> getResult() {
        return result;
    }
}

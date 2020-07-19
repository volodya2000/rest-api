package com.udemy.restudemy.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OperationStatusModel {

    public String operationResult;

    public String operatonName;

    public OperationStatusModel setOperationResult(String operationResult)
    {
        this.operationResult=operationResult;
        return this;
    }

    public OperationStatusModel setOperationName(String operationName)
    {
        this.operatonName=operationName;
        return this;
    }
}

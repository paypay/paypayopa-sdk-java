package jp.ne.paypay.model;

import com.squareup.okhttp.Response;

import java.lang.reflect.Type;

public class ResponseParameters {
    Response response;
    Type returnType;
    String apiName;

    public Response getResponse() {
        return response;
    }

    public ResponseParameters setResponse(Response response) {
        this.response = response;
        return this;
    }

    public Type getReturnType() {
        return returnType;
    }

    public ResponseParameters setReturnType(Type returnType) {
        this.returnType = returnType;
        return this;
    }

    public String getApiName() {
        return apiName;
    }

    public ResponseParameters setApiName(String apiName) {
        this.apiName = apiName;
        return this;
    }
}

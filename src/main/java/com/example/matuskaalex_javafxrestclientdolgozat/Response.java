package com.example.matuskaalex_javafxrestclientdolgozat;

public class Response {
    private int responseCode;
    private String contnt;

    public Response(int responseCode, String contnt) {
        this.responseCode = responseCode;
        this.contnt = contnt;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getContent() {
        return contnt;
    }
}

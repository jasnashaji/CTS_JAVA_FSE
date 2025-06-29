package com.example;

public interface ExternalApi {
    String getData();                 // Existing
    void sendData(String payload);    // New method for verifying arguments
}

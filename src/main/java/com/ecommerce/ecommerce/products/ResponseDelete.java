package com.ecommerce.ecommerce.products;

public class ResponseDelete {
    private String message;
    
    public ResponseDelete(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.alpaca.library_base.event;

public class DefaultEvent {
    private String message;

    public DefaultEvent(String message) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}

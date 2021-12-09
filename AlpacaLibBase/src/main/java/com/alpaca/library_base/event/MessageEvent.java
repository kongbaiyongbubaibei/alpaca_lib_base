package com.alpaca.library_base.event;

public class MessageEvent {
    private String message;
    private int isCollection;
    private int id;

    public MessageEvent(String message, int isCollection, int id) {
        this.message = message;
        this.isCollection = isCollection;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(int isCollection) {
        this.isCollection = isCollection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

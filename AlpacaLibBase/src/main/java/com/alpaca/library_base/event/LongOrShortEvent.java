package com.alpaca.library_base.event;

public class LongOrShortEvent {
    private int epodes;
    private int sentence;


    public LongOrShortEvent(int epodes, int sentence) {
        this.epodes = epodes;
        this.sentence = sentence;
    }


    public int getEpodes() {
        return epodes;
    }

    public void setEpodes(int epodes) {
        this.epodes = epodes;
    }

    public int getSentence() {
        return sentence;
    }

    public void setSentence(int sentence) {
        this.sentence = sentence;
    }
}

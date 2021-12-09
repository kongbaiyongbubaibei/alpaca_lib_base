package com.alpaca.library_base.event;

public class StopPlayingEvent {
    public int voiceType;

    public StopPlayingEvent() {
    }

    public StopPlayingEvent(int voiceType) {
        this.voiceType = voiceType;
    }

    public int getVoiceType() {
        return voiceType;
    }

    public void setVoiceType(int voiceType) {
        this.voiceType = voiceType;
    }


}

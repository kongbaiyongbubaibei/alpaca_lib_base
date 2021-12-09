package com.alpaca.library_base.event;

public class VideoProgressEvent {

    private int progress;
    private long position;
    private long duration;

    public VideoProgressEvent(int progress, long position, long duration) {
        this.progress = progress;
        this.position = position;
        this.duration = duration;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}

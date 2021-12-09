package com.alpaca.library_base.event;

public class InteractionSumtEvent {
    private int leaveMessageSum;
    private int interactionSum;
    private long currArticleId;

    public InteractionSumtEvent() {

    }

    public InteractionSumtEvent(int leaveMessageSum, int interactionSum, long currArticleId) {
        this.leaveMessageSum = leaveMessageSum;
        this.interactionSum = interactionSum;
        this.currArticleId = currArticleId;
    }


    public int getLeaveMessageSum() {
        return leaveMessageSum;
    }

    public void setLeaveMessageSum(int leaveMessageSum) {
        this.leaveMessageSum = leaveMessageSum;
    }

    public int getInteractionSum() {
        return interactionSum;
    }

    public void setInteractionSum(int interactionSum) {
        this.interactionSum = interactionSum;
    }

    public long getCurrArticleId() {
        return currArticleId;
    }

    public void setCurrArticleId(long currArticleId) {
        this.currArticleId = currArticleId;
    }
}

package com.alpaca.library_base.event;

public class DiQuestionNumberEvent {
    private int questionnNumber;
    private int showPgaerI;
    private int diScreenI;
    private int lastClick;


    public DiQuestionNumberEvent(int questionnNumber, int showPgaerI, int diScreenI, int lastClick) {
        this.questionnNumber = questionnNumber;
        this.showPgaerI = showPgaerI;
        this.diScreenI = diScreenI;
        this.lastClick = lastClick;
    }

    public int getQuestionnNumber() {
        return questionnNumber;
    }

    public void setQuestionnNumber(int questionnNumber) {
        this.questionnNumber = questionnNumber;
    }

    public int getShowPgaerI() {
        return showPgaerI;
    }

    public void setShowPgaerI(int showPgaerI) {
        this.showPgaerI = showPgaerI;
    }

    public int getDiScreenI() {
        return diScreenI;
    }

    public void setDiScreenI(int diScreenI) {
        this.diScreenI = diScreenI;
    }

    public int getLastClick() {
        return lastClick;
    }

    public void setLastClick(int lastClick) {
        this.lastClick = lastClick;
    }
}

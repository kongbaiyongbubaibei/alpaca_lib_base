package com.alpaca.library_base.net;

/**
 * @文件名: SqException
 * @功能描述:
 * @Date : 2018/3/1
 * @email:
 * @修改记录:
 */
public class SqException extends Exception {
    private int mErrorCode;
    private String mErrorMessage;

    public SqException(int errorCode, String errorMessage) {
        this.mErrorCode = errorCode;
        this.mErrorMessage = errorMessage;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public void setErrorCode(int mErrorCode) {
        this.mErrorCode = mErrorCode;
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    public void setErrorMessage(String mErrorMessage) {
        this.mErrorMessage = mErrorMessage;
    }
}

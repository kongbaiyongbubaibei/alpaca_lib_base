package com.alpaca.library_base.event;

import java.util.List;

/**
 * @文件名: CateListRefreshEvent
 * @功能描述:
 * @Date : 2018/2/23
 * @email:
 * @修改记录:
 */
public class UserCollectRefreshAcEvent {

    public String qid;
    public List<String> wordList;

    public UserCollectRefreshAcEvent() {

    }

    public UserCollectRefreshAcEvent(String qid, List<String> wordList) {
        this.qid = qid;
        this.wordList = wordList;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public List<String> getWordList() {
        return wordList;
    }

    public void setWordList(List<String> wordList) {
        this.wordList = wordList;
    }
}

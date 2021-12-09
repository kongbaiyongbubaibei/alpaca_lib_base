package com.alpaca.library_base.module.lexicon;

import java.util.List;

/**
 * @文件名: LineLexiconData
 * @功能描述:
 * @Date : 2019/12/4
 * @email:
 * @修改记录:
 */
public class LineLexiconData {

    private int id;
    private String title;
    private String icon;

    public LineLexiconData(int id, String title, String icon) {
        this.id = id;
        this.title = title;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    private List<LexiconInfoBean.DataBean.LexiconListBean> lexiconList;

    public List<LexiconInfoBean.DataBean.LexiconListBean> getLexiconList() {
        return lexiconList;
    }

    public void setLexiconList(List<LexiconInfoBean.DataBean.LexiconListBean> lexiconList) {
        this.lexiconList = lexiconList;
    }

    public LineLexiconData(List<LexiconInfoBean.DataBean.LexiconListBean> lexiconList) {
        this.lexiconList = lexiconList;
    }

    public LineLexiconData() {
    }
}

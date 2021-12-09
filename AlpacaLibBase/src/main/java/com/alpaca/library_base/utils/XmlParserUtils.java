package com.alpaca.library_base.utils;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * class desc :
 *
 * @author : zzh
 * date : 2020/4/17
 */
public class XmlParserUtils {

    private static final String TAG = "XmlParserUtils";

    public static Xml2RATestBean parseXMLWithPull2RATest(String xmlData) throws Exception {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(new StringReader(xmlData));
        int eventType = parser.getEventType();

        Xml2RATestBean xml2RATestBean = new Xml2RATestBean();

        Xml2RATestBean.Sentence sentence = null;
        Xml2RATestBean.Word word = null;
        Xml2RATestBean.Syll syll = null;
        Xml2RATestBean.Phone phone = null;

        double totalScore = 0;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String nodeName = parser.getName();
            switch (eventType) {
                // 开始解析某个结点
                case XmlPullParser.START_TAG: {
                    if ("read_word".equals(nodeName)) {
                        totalScore = Double.parseDouble(nullReplace(parser.getAttributeValue(null, "total_score"), 0));
                    } else if ("sentence".equals(nodeName)) {
                        sentence = new Xml2RATestBean.Sentence();
                        double score = Double.parseDouble(nullReplace(parser.getAttributeValue(null, "total_score"), 0));
                        if (totalScore != 0 && score == 0) {
                            sentence.setTotal_score(totalScore);
                        } else {
                            sentence.setTotal_score(Double.parseDouble(nullReplace(parser.getAttributeValue(null, "total_score"), 0)));
                        }
                        sentence.setFluency_score(Double.parseDouble(nullReplace(parser.getAttributeValue(null, "fluency_score"), 0)));
                        sentence.setAccuracy_score(Double.parseDouble(nullReplace(parser.getAttributeValue(null, "accuracy_score"), 0)));
                        sentence.setBeg_pos(Integer.parseInt(nullReplace(parser.getAttributeValue(null, "beg_pos"), 0)));
                        sentence.setContent(parser.getAttributeValue(null, "content"));
                        sentence.setEnd_pos(Integer.parseInt(nullReplace(parser.getAttributeValue(null, "end_pos"), 0)));
                        sentence.setIndex(Integer.parseInt(nullReplace(parser.getAttributeValue(null, "index"), 0)));
                        sentence.setWord_count(Integer.parseInt(nullReplace(parser.getAttributeValue(null, "word_count"), 0)));
                    } else if ("word".equals(nodeName)) {
                        word = new Xml2RATestBean.Word();
                        word.setBeg_pos(Integer.parseInt(nullReplace(parser.getAttributeValue(null, "beg_pos"), 0)));
                        word.setContent(parser.getAttributeValue(null, "content"));
                        word.setDp_message(Integer.parseInt(nullReplace(parser.getAttributeValue(null, "dp_message"), 0)));
                        word.setEnd_pos(Integer.parseInt(nullReplace(parser.getAttributeValue(null, "end_pos"), 0)));
                        word.setGlobal_index(Integer.parseInt(nullReplace(parser.getAttributeValue(null, "global_index"), 0)));
                        word.setIndex(Integer.parseInt(nullReplace(parser.getAttributeValue(null, "index"), 0)));
                        word.setProperty(Integer.parseInt(nullReplace(parser.getAttributeValue(null, "property"), 0)));
                        word.setTotal_score(Double.parseDouble(nullReplace(parser.getAttributeValue(null, "total_score"), 0)));
                    } else if ("syll".equals(nodeName)) {
                        syll = new Xml2RATestBean.Syll();
                        syll.setBeg_pos(Integer.parseInt(nullReplace(parser.getAttributeValue(null, "beg_pos"), 0)));
                        syll.setContent(parser.getAttributeValue(null, "content"));
                        syll.setEnd_pos(Integer.parseInt(nullReplace(parser.getAttributeValue(null, "end_pos"), 0)));
                        syll.setSerr_msg(Integer.parseInt(nullReplace(parser.getAttributeValue(null, "serr_msg"), 0)));
                        syll.setSyll_accent(Integer.parseInt(nullReplace(parser.getAttributeValue(null, "syll_accent"), 0)));
                        syll.setSyll_score(Double.parseDouble(nullReplace(parser.getAttributeValue(null, "syll_score"), 0)));
                    } else if ("phone".equals(nodeName)) {
                        phone = new Xml2RATestBean.Phone();
                        phone.setBeg_pos(Integer.parseInt(nullReplace(parser.getAttributeValue(null, "beg_pos"), 0)));
                        phone.setEnd_pos(Integer.parseInt(nullReplace(parser.getAttributeValue(null, "end_pos"), 0)));
                        phone.setContent(parser.getAttributeValue(null, "content"));
                    }
                    Log.i(TAG, "parseXMLWithPull2RATest-start: " + nodeName);
                    break;
                }
                // 完成解析某个结点
                case XmlPullParser.END_TAG: {
                    if ("sentence".equals(nodeName)) {
                        xml2RATestBean.getSentence().add(sentence);
                    } else if ("word".equals(nodeName)) {
                        if (sentence != null) {
                            sentence.getWord().add(word);
                        }
                    } else if ("syll".equals(nodeName)) {
                        if (word != null) {
                            word.getSyll().add(syll);
                        }
                    } else if ("phone".equals(nodeName)) {
                        if (syll != null) {
                            syll.getPhone().add(phone);
                        }
                    }
                    Log.i(TAG, "parseXMLWithPull2RATest-end: " + nodeName);
                    break;
                }
                default:
                    break;
            }
            eventType = parser.next();
        }

        Log.i(TAG, "parseXMLWithPull2RATest: " + GsonUtil.toJson(xml2RATestBean));
        return xml2RATestBean;
    }

    public static String nullReplace(Object object, Object defaultValue) {
        if (object == null) {
            return String.valueOf(defaultValue);
        } else {
            return String.valueOf(object);
        }
    }

    public static class Xml2RATestBean {
        private List<Sentence> sentence = new ArrayList<>();

        public List<Sentence> getSentence() {
            return sentence;
        }

        public void setSentence(List<Sentence> sentence) {
            this.sentence = sentence;
        }

        public static class Sentence {
            private double accuracy_score;
            private double fluency_score;
            private double standard_score;
            private double total_score;
            private int beg_pos;
            private int end_pos;
            private int index;
            private int word_count;
            private String content;
            private List<Word> word = new ArrayList<>();

            public List<Word> getWord() {
                return word;
            }

            public void setWord(List<Word> word) {
                this.word = word;
            }

            public double getAccuracy_score() {
                return accuracy_score;
            }

            public void setAccuracy_score(double accuracy_score) {
                this.accuracy_score = accuracy_score;
            }

            public double getFluency_score() {
                return fluency_score;
            }

            public void setFluency_score(double fluency_score) {
                this.fluency_score = fluency_score;
            }

            public double getStandard_score() {
                return standard_score;
            }

            public void setStandard_score(double standard_score) {
                this.standard_score = standard_score;
            }

            public double getTotal_score() {
                return total_score;
            }

            public void setTotal_score(double total_score) {
                this.total_score = total_score;
            }

            public int getBeg_pos() {
                return beg_pos;
            }

            public void setBeg_pos(int beg_pos) {
                this.beg_pos = beg_pos;
            }

            public int getEnd_pos() {
                return end_pos;
            }

            public void setEnd_pos(int end_pos) {
                this.end_pos = end_pos;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public int getWord_count() {
                return word_count;
            }

            public void setWord_count(int word_count) {
                this.word_count = word_count;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public static class Word {
            private int beg_pos;
            private int end_pos;
            private int index;
            private int property;
            private int global_index;
            private int dp_message;
            private double total_score;
            private String content;
            private List<Syll> syll = new ArrayList<>();

            public int getBeg_pos() {
                return beg_pos;
            }

            public void setBeg_pos(int beg_pos) {
                this.beg_pos = beg_pos;
            }

            public int getEnd_pos() {
                return end_pos;
            }

            public void setEnd_pos(int end_pos) {
                this.end_pos = end_pos;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public int getProperty() {
                return property;
            }

            public void setProperty(int property) {
                this.property = property;
            }

            public int getGlobal_index() {
                return global_index;
            }

            public void setGlobal_index(int global_index) {
                this.global_index = global_index;
            }

            public int getDp_message() {
                return dp_message;
            }

            public void setDp_message(int dp_message) {
                this.dp_message = dp_message;
            }

            public double getTotal_score() {
                return total_score;
            }

            public void setTotal_score(double total_score) {
                this.total_score = total_score;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public List<Syll> getSyll() {
                return syll;
            }

            public void setSyll(List<Syll> syll) {
                this.syll = syll;
            }
        }

        public static class Syll {
            private int beg_pos;
            private int end_pos;
            private int serr_msg;
            private int syll_accent;
            private double syll_score;
            private String content;
            private List<Phone> phone = new ArrayList<>();

            public int getBeg_pos() {
                return beg_pos;
            }

            public void setBeg_pos(int beg_pos) {
                this.beg_pos = beg_pos;
            }

            public int getEnd_pos() {
                return end_pos;
            }

            public void setEnd_pos(int end_pos) {
                this.end_pos = end_pos;
            }

            public int getSerr_msg() {
                return serr_msg;
            }

            public void setSerr_msg(int serr_msg) {
                this.serr_msg = serr_msg;
            }

            public int getSyll_accent() {
                return syll_accent;
            }

            public void setSyll_accent(int syll_accent) {
                this.syll_accent = syll_accent;
            }

            public double getSyll_score() {
                return syll_score;
            }

            public void setSyll_score(double syll_score) {
                this.syll_score = syll_score;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public List<Phone> getPhone() {
                return phone;
            }

            public void setPhone(List<Phone> phone) {
                this.phone = phone;
            }
        }

        public static class Phone {
            private int beg_pos;
            private int end_pos;
            private String content;

            public int getBeg_pos() {
                return beg_pos;
            }

            public void setBeg_pos(int beg_pos) {
                this.beg_pos = beg_pos;
            }

            public int getEnd_pos() {
                return end_pos;
            }

            public void setEnd_pos(int end_pos) {
                this.end_pos = end_pos;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}

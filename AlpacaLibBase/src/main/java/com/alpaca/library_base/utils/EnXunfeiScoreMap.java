package com.alpaca.library_base.utils;

import java.util.HashMap;

public class EnXunfeiScoreMap {

    private static HashMap<String, String> data = null;

    public EnXunfeiScoreMap() {
        if (data == null) {
            data = new HashMap<String, String>();
            data.put("aa", "ɑː");
            data.put("ae", "æ");
            data.put("ah", "ʌ");
            data.put("ao", "ɔː");
            data.put("ar", "eə");
            data.put("aw", "aʊ");
            data.put("ax", "ə");
            data.put("ay", "aɪ");
            data.put("eh", "e");
            data.put("er", "ɜː");
            data.put("ey", "eɪ");
            data.put("ih", "ɪ");
            data.put("ir", "ɪə");
            data.put("iy", "iː");
            data.put("oo", "ɒ");
            data.put("ow", "əʊ");
            data.put("oy", "ɒɪ");
            data.put("uh", "ʊ");
            data.put("uw", "uː");
            data.put("ur", "ʊə");
            data.put("b", "b");
            data.put("ch", "tʃ");
            data.put("d", "d");
            data.put("dh", "ð");
            data.put("f", "f");
            data.put("g", "g");
            data.put("hh", "h");
            data.put("jh", "dʒ");
            data.put("k", "k");
            data.put("l", "l");
            data.put("m", "m");
            data.put("n", "n");
            data.put("ng", "ŋ");
            data.put("p", "p");
            data.put("r", "r");
            data.put("s", "s");
            data.put("sh", "ʃ");
            data.put("t", "t");
            data.put("th", "θ");
            data.put("v", "v");
            data.put("w", "w");
            data.put("y", "j");
            data.put("z", "z");
            data.put("zh", "ʒ");
            data.put("dr", "dr");
        }
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public String get(String cString) {
        if (data.containsKey(cString)) {
            return data.get(cString);
        }
        return cString;
    }

}

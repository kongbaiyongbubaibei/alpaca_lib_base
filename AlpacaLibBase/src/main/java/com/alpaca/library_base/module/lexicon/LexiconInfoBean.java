package com.alpaca.library_base.module.lexicon;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @文件名: LexiconInfoBean
 * @功能描述:
 * @Date : 2019/12/3
 * @email:
 * @修改记录:
 */
public class LexiconInfoBean implements Serializable {


    /**
     * code : 0
     * msg : success
     * data : [{"id":1,"title":"分级词库","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191203/65cc49f769c3477aa448f9671e91c08f.png","lexiconList":[{"id":1,"typeId":1,"name":"PTE必考词库","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/a3678746cda549d9a871932d32f17322.png","practice":7,"sort":0,"wordCount":481,"createTime":1575016469000,"userLexiconInfo":{"total":481,"masterCount":20,"learningCount":20,"errorCount":0},"modeList":[{"id":4,"name":"听英文\u2014拼写单词","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":1},{"id":5,"name":"看中文\u2014选英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/b11c2fe55b374e18a420b9541b8c7393.png","title":"写作","lexiconId":1},{"id":6,"name":"看英文\u2014选中文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/babf18f21ac345d6afe51d8de741f1e0.png","title":"阅读","lexiconId":1}]},{"id":2,"typeId":1,"name":"听力FIB全库词","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/f9fbb66521864f90af5d518b948a65ef.png","practice":6,"sort":7,"wordCount":73,"createTime":1575443118000,"userLexiconInfo":{"total":73,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":4,"name":"听英文\u2014拼写单词","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":2}]},{"id":3,"typeId":1,"name":"RA高频难读词","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/f5aecd167bde4dc3b9ef74700de3c922.png","practice":7,"sort":2,"wordCount":31,"createTime":1575443105000,"userLexiconInfo":{"total":31,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":1,"name":"看英文\u2014读英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/f727fed312da4410a048e4883a10fc29.png","title":"口语","lexiconId":3}]},{"id":4,"typeId":1,"name":"RS全库难读词","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/0c15a6cbc12f4fd791a16535289f05ec.png","practice":2,"sort":3,"wordCount":76,"createTime":1575443108000,"userLexiconInfo":{"total":76,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":1,"name":"看英文\u2014读英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/f727fed312da4410a048e4883a10fc29.png","title":"口语","lexiconId":4},{"id":2,"name":"听英文\u2014读英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/f727fed312da4410a048e4883a10fc29.png","title":"口语","lexiconId":4}]},{"id":5,"typeId":1,"name":"RL高频难读词","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/5b081b3cd7db401e8c73232b9f429ffc.png","practice":2,"sort":4,"wordCount":119,"createTime":1575443110000,"userLexiconInfo":{"total":119,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":1,"name":"看英文\u2014读英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/f727fed312da4410a048e4883a10fc29.png","title":"口语","lexiconId":5},{"id":2,"name":"听英文\u2014读英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/f727fed312da4410a048e4883a10fc29.png","title":"口语","lexiconId":5}]},{"id":6,"typeId":1,"name":"DI高频难读词","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/3827acd883d44736a92d8734942a8e0f.png","practice":2,"sort":5,"wordCount":220,"createTime":1575443113000,"userLexiconInfo":{"total":220,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":1,"name":"看英文\u2014读英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/f727fed312da4410a048e4883a10fc29.png","title":"口语","lexiconId":6}]},{"id":7,"typeId":1,"name":"听力SST高频重点关键词","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/17a2a97b4b914956bcc5a37283a0d092.png","practice":1,"sort":6,"wordCount":0,"createTime":1575443116000,"userLexiconInfo":{"total":0,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":3,"name":"听英文\u2014选中文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":7},{"id":4,"name":"听英文\u2014拼写单词","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":7}]},{"id":8,"typeId":1,"name":"WFD高频难点易错词","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/5c568e091afa417c80318ec659edf44d.png","practice":1,"sort":8,"wordCount":0,"createTime":1575443132000,"userLexiconInfo":{"total":0,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":3,"name":"听英文\u2014选中文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":8},{"id":4,"name":"听英文\u2014拼写单词","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":8}]},{"id":9,"typeId":1,"name":"PTE官方词汇-TEST PLUS","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/f8882189c1584b6abe832f20f79a70f0.png","practice":1,"sort":9,"wordCount":0,"createTime":1575443129000,"userLexiconInfo":{"total":0,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":4,"name":"听英文\u2014拼写单词","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":9},{"id":5,"name":"看中文\u2014选英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/b11c2fe55b374e18a420b9541b8c7393.png","title":"写作","lexiconId":9},{"id":6,"name":"看英文\u2014选中文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/babf18f21ac345d6afe51d8de741f1e0.png","title":"阅读","lexiconId":9}]},{"id":10,"typeId":1,"name":"PTE官方词汇-OFFICIAL GUIDE","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/6d4351372fd144dc9554b9d9eb6b7735.png","practice":2,"sort":10,"wordCount":0,"createTime":1575443122000,"userLexiconInfo":{"total":0,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":4,"name":"听英文\u2014拼写单词","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":10},{"id":5,"name":"看中文\u2014选英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/b11c2fe55b374e18a420b9541b8c7393.png","title":"写作","lexiconId":10},{"id":6,"name":"看英文\u2014选中文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/babf18f21ac345d6afe51d8de741f1e0.png","title":"阅读","lexiconId":10}]},{"id":11,"typeId":1,"name":"PTE官方词汇-TEST BUILDER","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/e1f17c9be93c46cba7c8c1dfa6613fd1.png","practice":2,"sort":11,"wordCount":0,"createTime":1575443126000,"userLexiconInfo":{"total":0,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":4,"name":"听英文\u2014拼写单词","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":11},{"id":5,"name":"看中文\u2014选英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/b11c2fe55b374e18a420b9541b8c7393.png","title":"写作","lexiconId":11},{"id":6,"name":"看英文\u2014选中文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/babf18f21ac345d6afe51d8de741f1e0.png","title":"阅读","lexiconId":11}]}]},{"id":2,"title":"常用词库","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191203/35fbf78a2aca40789882017fa6c1660e.png","lexiconList":[{"id":12,"typeId":2,"name":"30分目标词库","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/deddd003a6ce4f4099ec60472d0739f5.png","practice":1,"sort":12,"wordCount":0,"createTime":1575443137000,"userLexiconInfo":{"total":0,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":4,"name":"听英文\u2014拼写单词","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":12},{"id":5,"name":"看中文\u2014选英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/b11c2fe55b374e18a420b9541b8c7393.png","title":"写作","lexiconId":12},{"id":6,"name":"看英文\u2014选中文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/babf18f21ac345d6afe51d8de741f1e0.png","title":"阅读","lexiconId":12}]},{"id":13,"typeId":2,"name":"30-50分目标词库","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/c98f255b847e44279e2fcd018e6842ee.png","practice":2,"sort":13,"wordCount":0,"createTime":1575443140000,"userLexiconInfo":{"total":0,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":4,"name":"听英文\u2014拼写单词","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":13},{"id":5,"name":"看中文\u2014选英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/b11c2fe55b374e18a420b9541b8c7393.png","title":"写作","lexiconId":13},{"id":6,"name":"看英文\u2014选中文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/babf18f21ac345d6afe51d8de741f1e0.png","title":"阅读","lexiconId":13}]},{"id":14,"typeId":2,"name":"50-65分目标词库","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/d6f53d80f0574feda4a2f22c1266ae50.png","practice":0,"sort":14,"wordCount":0,"createTime":1575443134000,"userLexiconInfo":{"total":0,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":4,"name":"听英文\u2014拼写单词","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":14},{"id":5,"name":"看中文\u2014选英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/b11c2fe55b374e18a420b9541b8c7393.png","title":"写作","lexiconId":14},{"id":6,"name":"看英文\u2014选中文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/babf18f21ac345d6afe51d8de741f1e0.png","title":"阅读","lexiconId":14}]}]}]
     */

    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 1
         * title : 分级词库
         * icon : http://oyxe80s4l.bkt.clouddn.com/image/activity/20191203/65cc49f769c3477aa448f9671e91c08f.png
         * lexiconList : [{"id":1,"typeId":1,"name":"PTE必考词库","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/a3678746cda549d9a871932d32f17322.png","practice":7,"sort":0,"wordCount":481,"createTime":1575016469000,"userLexiconInfo":{"total":481,"masterCount":20,"learningCount":20,"errorCount":0},"modeList":[{"id":4,"name":"听英文\u2014拼写单词","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":1},{"id":5,"name":"看中文\u2014选英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/b11c2fe55b374e18a420b9541b8c7393.png","title":"写作","lexiconId":1},{"id":6,"name":"看英文\u2014选中文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/babf18f21ac345d6afe51d8de741f1e0.png","title":"阅读","lexiconId":1}]},{"id":2,"typeId":1,"name":"听力FIB全库词","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/f9fbb66521864f90af5d518b948a65ef.png","practice":6,"sort":7,"wordCount":73,"createTime":1575443118000,"userLexiconInfo":{"total":73,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":4,"name":"听英文\u2014拼写单词","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":2}]},{"id":3,"typeId":1,"name":"RA高频难读词","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/f5aecd167bde4dc3b9ef74700de3c922.png","practice":7,"sort":2,"wordCount":31,"createTime":1575443105000,"userLexiconInfo":{"total":31,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":1,"name":"看英文\u2014读英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/f727fed312da4410a048e4883a10fc29.png","title":"口语","lexiconId":3}]},{"id":4,"typeId":1,"name":"RS全库难读词","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/0c15a6cbc12f4fd791a16535289f05ec.png","practice":2,"sort":3,"wordCount":76,"createTime":1575443108000,"userLexiconInfo":{"total":76,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":1,"name":"看英文\u2014读英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/f727fed312da4410a048e4883a10fc29.png","title":"口语","lexiconId":4},{"id":2,"name":"听英文\u2014读英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/f727fed312da4410a048e4883a10fc29.png","title":"口语","lexiconId":4}]},{"id":5,"typeId":1,"name":"RL高频难读词","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/5b081b3cd7db401e8c73232b9f429ffc.png","practice":2,"sort":4,"wordCount":119,"createTime":1575443110000,"userLexiconInfo":{"total":119,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":1,"name":"看英文\u2014读英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/f727fed312da4410a048e4883a10fc29.png","title":"口语","lexiconId":5},{"id":2,"name":"听英文\u2014读英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/f727fed312da4410a048e4883a10fc29.png","title":"口语","lexiconId":5}]},{"id":6,"typeId":1,"name":"DI高频难读词","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/3827acd883d44736a92d8734942a8e0f.png","practice":2,"sort":5,"wordCount":220,"createTime":1575443113000,"userLexiconInfo":{"total":220,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":1,"name":"看英文\u2014读英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/f727fed312da4410a048e4883a10fc29.png","title":"口语","lexiconId":6}]},{"id":7,"typeId":1,"name":"听力SST高频重点关键词","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/17a2a97b4b914956bcc5a37283a0d092.png","practice":1,"sort":6,"wordCount":0,"createTime":1575443116000,"userLexiconInfo":{"total":0,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":3,"name":"听英文\u2014选中文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":7},{"id":4,"name":"听英文\u2014拼写单词","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":7}]},{"id":8,"typeId":1,"name":"WFD高频难点易错词","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/5c568e091afa417c80318ec659edf44d.png","practice":1,"sort":8,"wordCount":0,"createTime":1575443132000,"userLexiconInfo":{"total":0,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":3,"name":"听英文\u2014选中文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":8},{"id":4,"name":"听英文\u2014拼写单词","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":8}]},{"id":9,"typeId":1,"name":"PTE官方词汇-TEST PLUS","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/f8882189c1584b6abe832f20f79a70f0.png","practice":1,"sort":9,"wordCount":0,"createTime":1575443129000,"userLexiconInfo":{"total":0,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":4,"name":"听英文\u2014拼写单词","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":9},{"id":5,"name":"看中文\u2014选英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/b11c2fe55b374e18a420b9541b8c7393.png","title":"写作","lexiconId":9},{"id":6,"name":"看英文\u2014选中文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/babf18f21ac345d6afe51d8de741f1e0.png","title":"阅读","lexiconId":9}]},{"id":10,"typeId":1,"name":"PTE官方词汇-OFFICIAL GUIDE","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/6d4351372fd144dc9554b9d9eb6b7735.png","practice":2,"sort":10,"wordCount":0,"createTime":1575443122000,"userLexiconInfo":{"total":0,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":4,"name":"听英文\u2014拼写单词","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":10},{"id":5,"name":"看中文\u2014选英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/b11c2fe55b374e18a420b9541b8c7393.png","title":"写作","lexiconId":10},{"id":6,"name":"看英文\u2014选中文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/babf18f21ac345d6afe51d8de741f1e0.png","title":"阅读","lexiconId":10}]},{"id":11,"typeId":1,"name":"PTE官方词汇-TEST BUILDER","imgUrl":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/e1f17c9be93c46cba7c8c1dfa6613fd1.png","practice":2,"sort":11,"wordCount":0,"createTime":1575443126000,"userLexiconInfo":{"total":0,"masterCount":0,"learningCount":0,"errorCount":0},"modeList":[{"id":4,"name":"听英文\u2014拼写单词","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":11},{"id":5,"name":"看中文\u2014选英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/b11c2fe55b374e18a420b9541b8c7393.png","title":"写作","lexiconId":11},{"id":6,"name":"看英文\u2014选中文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/babf18f21ac345d6afe51d8de741f1e0.png","title":"阅读","lexiconId":11}]}]
         */

        @SerializedName("id")
        private int id;
        @SerializedName("title")
        private String title;
        @SerializedName("icon")
        private String icon;
        @SerializedName("lexiconList")
        private List<LexiconListBean> lexiconList;

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

        public List<LexiconListBean> getLexiconList() {
            return lexiconList;
        }

        public void setLexiconList(List<LexiconListBean> lexiconList) {
            this.lexiconList = lexiconList;
        }

        public static class LexiconListBean implements Serializable {
            /**
             * id : 1
             * typeId : 1
             * name : PTE必考词库
             * imgUrl : http://oyxe80s4l.bkt.clouddn.com/image/activity/20191205/a3678746cda549d9a871932d32f17322.png
             * practice : 7
             * sort : 0
             * wordCount : 481
             * createTime : 1575016469000
             * userLexiconInfo : {"total":481,"masterCount":20,"learningCount":20,"errorCount":0}
             * modeList : [{"id":4,"name":"听英文\u2014拼写单词","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png","title":"听力","lexiconId":1},{"id":5,"name":"看中文\u2014选英文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/b11c2fe55b374e18a420b9541b8c7393.png","title":"写作","lexiconId":1},{"id":6,"name":"看英文\u2014选中文","icon":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/babf18f21ac345d6afe51d8de741f1e0.png","title":"阅读","lexiconId":1}]
             */

            @SerializedName("id")
            private int id;
            @SerializedName("typeId")
            private int typeId;
            @SerializedName("name")
            private String name;
            @SerializedName("imgUrl")
            private String imgUrl;
            @SerializedName("practice")
            private int practice;
            @SerializedName("sort")
            private int sort;
            @SerializedName("wordCount")
            private int wordCount;
            @SerializedName("createTime")
            private long createTime;
            @SerializedName("userLexiconInfo")
            private UserLexiconInfoBean userLexiconInfo;
            @SerializedName("modeIds")
            private List<Integer> modeList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getTypeId() {
                return typeId;
            }

            public void setTypeId(int typeId) {
                this.typeId = typeId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public int getPractice() {
                return practice;
            }

            public void setPractice(int practice) {
                this.practice = practice;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getWordCount() {
                return wordCount;
            }

            public void setWordCount(int wordCount) {
                this.wordCount = wordCount;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public UserLexiconInfoBean getUserLexiconInfo() {
                return userLexiconInfo;
            }

            public void setUserLexiconInfo(UserLexiconInfoBean userLexiconInfo) {
                this.userLexiconInfo = userLexiconInfo;
            }

            public List<Integer> getModeList() {
                return modeList;
            }

            public void setModeList(List<Integer> modeList) {
                this.modeList = modeList;
            }

            public static class UserLexiconInfoBean implements Serializable {
                /**
                 * total : 481
                 * masterCount : 20
                 * learningCount : 20
                 * errorCount : 0
                 */

                @SerializedName("total")
                private int total;
                @SerializedName("masterCount")
                private int masterCount;
                @SerializedName("learningCount")
                private int learningCount;
                @SerializedName("errorCount")
                private int errorCount;
                @SerializedName("collectCount")
                private int collectCount;
                @SerializedName("reviewCount")
                private int reviewCount;
                @SerializedName("reviewSum")
                private int reviewSum;

                public int getReviewCount() {
                    return reviewCount;
                }

                public void setReviewCount(int reviewCount) {
                    this.reviewCount = reviewCount;
                }

                public int getReviewSum() {
                    return reviewSum;
                }

                public void setReviewSum(int reviewSum) {
                    this.reviewSum = reviewSum;
                }

                public int getCollectCount() {
                    return collectCount;
                }

                public void setCollectCount(int collectCount) {
                    this.collectCount = collectCount;
                }

                public int getTotal() {
                    return total;
                }

                public void setTotal(int total) {
                    this.total = total;
                }

                public int getMasterCount() {
                    return masterCount;
                }

                public void setMasterCount(int masterCount) {
                    this.masterCount = masterCount;
                }

                public int getLearningCount() {
                    return learningCount;
                }

                public void setLearningCount(int learningCount) {
                    this.learningCount = learningCount;
                }

                public int getErrorCount() {
                    return errorCount;
                }

                public void setErrorCount(int errorCount) {
                    this.errorCount = errorCount;
                }
            }

            public static class ModeListBean implements Serializable {
                /**
                 * id : 4
                 * name : 听英文—拼写单词
                 * icon : http://oyxe80s4l.bkt.clouddn.com/image/activity/20191209/92d227eaa6af4665aa55107eb88fd68b.png
                 * title : 听力
                 * lexiconId : 1
                 */

                @SerializedName("id")
                private int id;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }


            }
        }
    }
}

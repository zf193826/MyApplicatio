package lianxi.com.week_03.bean;

import java.util.List;

/**
 * Created by 张峰 on 2017/10/21.
 */

public class DataDataBean {

    /**
     * error : false
     * results : [{"_id":"59cb9375421aa972879d1218","createdAt":"2017-09-27T20:03:01.65Z","desc":"Transition学习笔记","publishedAt":"2017-10-09T13:07:56.458Z","source":"web","type":"Android","url":"http://rkhcy.github.io/2017/09/27/TransitionNote2/","used":true,"who":"HuYounger"},{"_id":"59cf9a87421aa972845f20aa","createdAt":"2017-09-30T21:22:15.570Z","desc":"A retrofit extension written in kotlin","publishedAt":"2017-10-09T13:07:56.458Z","source":"web","type":"Android","url":"https://github.com/jaychang0917/SimpleApiClient","used":true,"who":"Jay"},{"_id":"59d05467421aa974b047d425","createdAt":"2017-10-01T10:35:19.789Z","desc":"知乎 x RxJava Meetup 活动 PPT。","images":["http://img.gank.io/404c29df-df48-4c43-aaec-b60fa1256388"],"publishedAt":"2017-10-09T13:07:56.458Z","source":"chrome","type":"Android","url":"https://github.com/zhihu/zhihu-rxjava-meetup","used":true,"who":"代码家"},{"_id":"59d45ebe421aa94e04c2adb7","createdAt":"2017-10-04T12:08:30.118Z","desc":"What are Coroutines in Kotlin?","publishedAt":"2017-10-09T13:07:56.458Z","source":"web","type":"Android","url":"https://blog.mindorks.com/what-are-coroutines-in-kotlin-bf4fecd476e9","used":true,"who":"AMIT SHEKHAR"},{"_id":"59c3d43a421aa9727ddd19c0","createdAt":"2017-09-21T23:01:14.453Z","desc":"灵活的ShadowView，可替代CardView使用","images":["http://img.gank.io/5ca55569-7b78-4691-aa1a-a1165eb8a164"],"publishedAt":"2017-09-29T11:21:16.116Z","source":"web","type":"Android","url":"https://github.com/loopeer/shadow","used":true,"who":null},{"_id":"59c3db8d421aa9727ddd19c1","createdAt":"2017-09-21T23:32:29.351Z","desc":"Android Transition学习笔记","images":["http://img.gank.io/a553aa37-9a54-4bb6-bdc2-9bfc2737e065"],"publishedAt":"2017-09-29T11:21:16.116Z","source":"web","type":"Android","url":"http://rkhcy.github.io/2017/09/21/TransitionNote/","used":true,"who":"HuYounger"},{"_id":"59cc8cac421aa972879d121a","createdAt":"2017-09-28T13:46:20.656Z","desc":"Android三种姿势带你玩转360度全景图功能","publishedAt":"2017-09-29T11:21:16.116Z","source":"web","type":"Android","url":"https://github.com/CN-ZPH/Three360panorama","used":true,"who":"张鹏辉"},{"_id":"59cc9df6421aa9727ddd19db","createdAt":"2017-09-28T15:00:06.42Z","desc":"比官方更像官方的NationalGeographic国家地理-每日精选客户端，重要的是用了最新的Android Architecture Components(Lifecycle) & Kotlin，给大家探路填坑","publishedAt":"2017-09-29T11:21:16.116Z","source":"web","type":"Android","url":"https://www.github.com/wheat7/NationalGeographic","used":true,"who":"麦田哥"},{"_id":"59b74f4a421aa911847a0390","createdAt":"2017-09-12T11:06:50.144Z","desc":"基于Tesseract-OCR实现自动扫描识别手机号","images":["http://img.gank.io/d2c557fe-7d3b-4330-9c51-9c427178f633"],"publishedAt":"2017-09-26T12:12:07.813Z","source":"web","type":"Android","url":"https://github.com/simplezhli/Tesseract-OCR-Scanner","used":true,"who":"唯鹿"},{"_id":"59b9da64421aa911847a039a","createdAt":"2017-09-14T09:24:52.83Z","desc":"详细讲解Launcher整个开发流程的系列教程。","publishedAt":"2017-09-26T12:12:07.813Z","source":"web","type":"Android","url":"http://www.codemx.cn/tags/Launcher/","used":true,"who":"YUCHUAN"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 59cb9375421aa972879d1218
         * createdAt : 2017-09-27T20:03:01.65Z
         * desc : Transition学习笔记
         * publishedAt : 2017-10-09T13:07:56.458Z
         * source : web
         * type : Android
         * url : http://rkhcy.github.io/2017/09/27/TransitionNote2/
         * used : true
         * who : HuYounger
         * images : ["http://img.gank.io/404c29df-df48-4c43-aaec-b60fa1256388"]
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}

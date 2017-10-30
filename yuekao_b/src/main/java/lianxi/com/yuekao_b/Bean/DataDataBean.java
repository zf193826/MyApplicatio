package lianxi.com.yuekao_b.Bean;

import java.util.List;

/**
 * Created by 张峰 on 2017/10/24.
 */

public class DataDataBean {

    /**
     * error : false
     * results : [{"_id":"59ee0dd5421aa90fe50c0199","createdAt":"2017-10-23T23:42:13.769Z","desc":"设计模式之 Kotlin 实现","publishedAt":"2017-10-24T11:50:49.1Z","source":"web","type":"Android","url":"https://github.com/volodymyrprokopyuk/kotlin-sdp","used":true,"who":null},{"_id":"59ee9a4e421aa90fe72535bb","createdAt":"2017-10-24T09:41:34.774Z","desc":"如何简单高效的学会Smali语法?","publishedAt":"2017-10-24T11:50:49.1Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247487635&idx=1&sn=407576fc688f4bef4bb689fd5c025e33","used":true,"who":"陈宇明"},{"_id":"59eeb78f421aa90fe72535bd","createdAt":"2017-10-24T11:46:23.809Z","desc":"Java 实现的自然语言处理 中文分词 词性标注 命名实体识别 依存句法分析 关键词提取 自动摘要 短语提取 拼音 简繁转换。","publishedAt":"2017-10-24T11:50:49.1Z","source":"chrome","type":"Android","url":"https://github.com/hankcs/HanLP","used":true,"who":"代码家"},{"_id":"59e9756c421aa90fe2f02bd6","createdAt":"2017-10-20T12:02:52.349Z","desc":"about-page 2.0，全新的卡片风格，支持 Android DayNight，使用更加简单，API 更加丰富，新增「Android 应用友链」类型","images":["http://img.gank.io/662d3679-fdcc-4a27-b398-ac7946364de5"],"publishedAt":"2017-10-23T12:44:23.660Z","source":"web","type":"Android","url":"https://github.com/android-links/about-page","used":true,"who":"drakeet"},{"_id":"59eaf683421aa90fef2034af","createdAt":"2017-10-21T15:25:55.496Z","desc":"关于ConstraintLayout的约束性布局详解，实现布局的扁平化。","publishedAt":"2017-10-23T12:44:23.660Z","source":"web","type":"Android","url":"http://blog.csdn.net/qq_34902522/article/details/78303211","used":true,"who":null},{"_id":"59ec8af1421aa90fef2034b6","createdAt":"2017-10-22T20:11:29.249Z","desc":"直接在 Android 设备上训练 SGD 模型识别 MNIST 手写数字图片","images":["http://img.gank.io/78a4790f-d6d1-47c0-b5b1-aca2566e87fa"],"publishedAt":"2017-10-23T12:44:23.660Z","source":"web","type":"Android","url":"https://github.com/huazhouwang/Synapse","used":true,"who":"JOJO"},{"_id":"59ec8b3c421aa90fe50c0190","createdAt":"2017-10-22T20:12:44.783Z","desc":"你应该切换到Kotlin开发","publishedAt":"2017-10-23T12:44:23.660Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIyMjQ0MTU0NA==&mid=2247484456&idx=1&sn=216ac6f03d38163df77634c10f1405dc&chksm=e82c3d0fdf5bb419f4b08b24dff1d03c4c8721b7ba9129032b95cf9611ba68572297570e27f0&mpshare=1&scene=1&srcid=1022hoLJmgeou5r9xiqfZqr6&key=f5328c960621fc0debd63e1ddef2613994e064570491f5f4cab0f4361e3d1e771cbf1a382a9201e0c20253d03d6a4bbee2d320863d982133ee749626de805dd87f7d140d3248b13069e0eee669149f01&ascene=0&uin=MjMzMzgwOTEwMQ%3D%3D&devicetype=iMac+MacBookPro12%2C1+OSX+OSX+10.10.5+build(14F27)&version=11020201&pass_ticket=RbPeABtwsUTtfggpuj%2B4Buku%2BITC0OGsr8yYwHtlEbeqf4k6r6Bd13wD6g74xX%2Fn","used":true,"who":"Tamic (码小白)"},{"_id":"59e6e601421aa90fe50c017c","createdAt":"2017-10-18T13:26:25.243Z","desc":"轻量级 Android Socket 通信框架。","publishedAt":"2017-10-20T10:26:24.673Z","source":"chrome","type":"Android","url":"https://github.com/xuuhaoo/OkSocket","used":true,"who":"代码家"},{"_id":"59e948c6421aa90fe2f02bd1","createdAt":"2017-10-20T08:52:22.906Z","desc":"图文并茂全面总结Android Studio好用的插件(IDE通用)","images":["http://img.gank.io/3a2dfc7c-f579-409b-86c5-1e94ecddeba9"],"publishedAt":"2017-10-20T10:26:24.673Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/269a48d7508d","used":true,"who":"阿韦"},{"_id":"59de2f22421aa90fe50c015c","createdAt":"2017-10-11T22:48:02.721Z","desc":"用 Kotlin 实现的基于物理的动画","images":["http://img.gank.io/58925abb-3e11-4d6e-9e44-a4567c03d03f"],"publishedAt":"2017-10-17T13:10:43.731Z","source":"web","type":"Android","url":"https://github.com/sagar-viradiya/AndroidPhysicsAnimation","used":true,"who":" Thunder Bouble"}]
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
         * _id : 59ee0dd5421aa90fe50c0199
         * createdAt : 2017-10-23T23:42:13.769Z
         * desc : 设计模式之 Kotlin 实现
         * publishedAt : 2017-10-24T11:50:49.1Z
         * source : web
         * type : Android
         * url : https://github.com/volodymyrprokopyuk/kotlin-sdp
         * used : true
         * who : null
         * images : ["http://img.gank.io/662d3679-fdcc-4a27-b398-ac7946364de5"]
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private Object who;
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

        public Object getWho() {
            return who;
        }

        public void setWho(Object who) {
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

package WebCrawler;


import cn.hutool.http.HttpUtil;

public class WCHutool {
    public static void main(String[] args) {
        String familyNameNet = "https://hanyu.baidu.com/shici/detail?pid=0b2f26d4c0ddb3ee693fdb1137ee1b0d&from=kg0";
        String boyNameNet = "http://www.haoming8.cn/baobao/10881.html";
        String girlNameNet = "http://www.haoming8.cn/baobao/7641.html";
        String girlNameStr = HttpUtil.get(girlNameNet);
        String girlNameStr = HttpUtil.get(girlNameNet);
        HttpUtil.get(boyNameNet);
    }


}

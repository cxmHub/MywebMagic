package com.cxm;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.utils.HttpConstant;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * post请求， 解析XHR, ajax
 */
public class CourtPro implements PageProcessor {

    Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setCharset("UTF-8")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");


    public void process(Page page) {
//        JsonPathSelector json = new JsonPathSelector(page.getRawText());
//        List<String> list = json.selectList("$.list[*].CaseNo");
//        for (String s : list) {
//            System.out.println(s);
//        }
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("===========" + localHost);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        List<String> ids = new JsonPathSelector("$.list[*].CaseNo").selectList(page.getRawText());

        // List<String> all = page.getJson().jsonPath("$.list[*].CaseNo").all();
        for (String s : ids) {
            System.out.println(s);
        }
    }

    public Site getSite() {
        return this.site;
    }

    public static void main(String[] args) {
//        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
//        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(
//                new Proxy("202.85.213.219",3128)));

//        for (int i=0; i<10; i++){
//            /**
//             * 这个url是看不见的， 见浏览器中， 要根据参数自己推出来
//             */
//            String url = url = "http://www.zjsfgkw.cn/Notice/NoticeSD?pageno="+String.valueOf(i)+"&pagesize=10&cbfy=";
//            Request request = new Request(url);
//            request.setMethod(HttpConstant.Method.POST);
//            request.setRequestBody(HttpRequestBody.json("{'id':1}","utf-8"));
//            Spider.create(new CourtPro())
////                    .setDownloader(httpClientDownloader)
//                    .addRequest(request)
//                    .thread(3)
//                    .run();
//        }

        Spider spider = new Spider(new CourtPro());
        for (int i = 0; i < 10; i++) {
//            String url = null;
//            Map<String, Object> nameValuePair = new HashMap<String, Object>();
//            NameValuePair[] values = new NameValuePair[3];
//            values[0] = new BasicNameValuePair("pageno", String.valueOf(i));
//            values[1] = new BasicNameValuePair("pagesize", "10");
//            values[2] = new BasicNameValuePair("cbfy", "");
//            nameValuePair.put("nameValuePair", values);
            String url = "http://www.zjsfgkw.cn/Notice/NoticeSD";
            Request request = new Request(url);
            request.setMethod(HttpConstant.Method.POST);
            request.setRequestBody(HttpRequestBody.json(String.format("{'pageno':%s}", i+""), "utf-8"));
            request.setRequestBody(HttpRequestBody.json("{'pagesize','10'}","utf-8" ));
            request.setRequestBody(HttpRequestBody.json("{'cbfy',''}","utf-8" ));
            spider.addRequest(request);
        }

        spider.thread(3).run();

    }
}

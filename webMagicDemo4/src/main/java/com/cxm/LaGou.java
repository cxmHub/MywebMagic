package com.cxm;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.util.List;

public class LaGou implements PageProcessor {

    Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(50)
            .setCharset("UTF-8")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");

    public void process(Page page) {
        //page.putField("content", new JsonPathSelector("$.content.positionResult.result[1].positionName").select(page.getRawText()));
        System.out.println("---------------------");
        // 发现新的target再次进入
//        page.addTargetRequests(
//                page.getHtml().xpath("//*[contains(@id,'8682')]/a/text()").all());
//        page.addTargetRequests(
//                page.getHtml().xpath("//*[contains(@id,'qiushi_tag_')]/a[1]/div/span/text()").all());


        List<String> u_names = page.getHtml().xpath("//*[contains(@id,'qiushi_tag_')]/div[1]/a[2]/h2/text()").all();
        List<String> ilikes = page.getHtml().xpath("//*[contains(@id,'qiushi_tag_')]/div[2]/span[1]/i/text()").all();
        List<String> contents = page.getHtml().xpath("//*[contains(@id,'qiushi_tag_')]/a[1]/div/span/text()").all();

        System.out.println("===================================");
        for (int i = 0; i < u_names.size(); i++) {
            String u_name = u_names.get(i);
            int ilike = Integer.parseInt(ilikes.get(i));
            String content = contents.get(i);
            // 封装
            QiuShi qiuShi = new QiuShi();
            qiuShi.setU_name(u_name);
            qiuShi.setContent(content);
            qiuShi.setIlike(ilike);
            // 插入
            LaGouDao laGouDao = new LaGouDao();
            laGouDao.add(qiuShi);
        }



//        List<String> list = page.getHtml().xpath("//*[contains(@id,'qiushi_tag_')]/a[1]/div/span/text()").all();
//        // 存文件
//        page.putField("span", list);

    }

    public Site getSite() {
        return this.site;
    }

    public static void main(String[] args) {

        String ip = "96.80.89.69";
        int port = 8080;
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy(ip,port)));



        String url = "https://www.qiushibaike.com/8hr/page/";
        for (int i = 1; i <= 13; i++) {
            Spider.create(new LaGou())
                    .addUrl(url + i + "/")
                    .setDownloader(httpClientDownloader)
                    .thread(13)
                    .run();
        }
    }
}
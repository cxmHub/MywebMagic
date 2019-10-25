package com.cxm;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class MyCnblogsSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");

    public Site getSite() {
        return site;
    }

    public void process(Page page) {

        page.addTargetRequests(
                page.getHtml().xpath("//*[@id=\"qiushi_tag_121248310\"]/a/div/span/text()").all());

//
//        if (!page.getUrl().regex("http://www.cnblogs.com/[a-z 0-9 -]+/p/[0-9]{7}.html").match()) {
//            page.addTargetRequests(
//                    page.getHtml().xpath("//*[@id=\"mainContent\"]/div/div/div[@class=\"postTitle\"]/a/@href").all());
//        } else {
//            page.putField(page.getHtml().xpath("//*[@id=\"cb_post_title_url\"]/text()").toString(),
//                    page.getHtml().xpath("//*[@id=\"cb_post_title_url\"]/@href").toString());
//        }
    }

    public static void main(String[] args) {
        Spider.create(new MyCnblogsSpider()).addUrl("https://www.qiushibaike.com/")
                .addPipeline(new ConsolePipeline()).run();
    }
}

package com.cxm.service;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author cxm
 * @description
 * @date 2019-10-18 11:06
 **/
public class WeatherService implements PageProcessor {

    Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setCharset("UTF-8")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");



    public Site getSite() {
        return this.site;
    }

    public void process(Page page) {

        // 日期白天 //*[@id="today"]/div[2]/ul/li[1]/h1
        String day = page.getHtml().xpath("//*[@id=\"today\"]/div[2]/ul/li[1]/h1/text()").get();
        // 天气 //*[@id="today"]/div[2]/ul/li[1]/p[1]
        String dayWeather = page.getHtml().xpath("//*[@id=\"today\"]/div[2]/ul/li[1]/p[1]/text()").get();
        // 温度 //*[@id="today"]/div[2]/ul/li[1]/p[2]/span
        String dayTemp = page.getHtml().xpath("//*[@id=\"today\"]/div[2]/ul/li[1]/p[2]/span/text()").get();
        // 风级 //*[@id="today"]/div[2]/ul/li[1]/p[3]/span
        String dayWind = page.getHtml().xpath("//*[@id=\"today\"]/div[2]/ul/li[1]/p[3]/span/text()").get();

        // 日期夜晚 //*[@id="today"]/div[2]/ul/li[2]/h1
        String night = page.getHtml().xpath("//*[@id=\"today\"]/div[2]/ul/li[2]/h1/text()").get();
        // 天气 //*[@id="today"]/div[2]/ul/li[2]/p[1]
        String nightWeather = page.getHtml().xpath("//*[@id=\"today\"]/div[2]/ul/li[1]/p[1]/text()").get();
        // 温度  //*[@id="today"]/div[2]/ul/li[2]/p[2]/span
        String nightTemp = page.getHtml().xpath("//*[@id=\"today\"]/div[2]/ul/li[2]/p[2]/span/text()").get();
        // 风级 //*[@id="today"]/div[2]/ul/li[2]/p[3]/span
        String nightWind = page.getHtml().xpath("//*[@id=\"today\"]/div[2]/ul/li[2]/p[3]/span/text()").get();


    }

    public static void main(String[] args) {
        String url = "http://www.weather.com.cn/weather1d/101010100.shtml";

        Spider.create(new WeatherService())
                .addUrl(url)
                .run();

    }
}

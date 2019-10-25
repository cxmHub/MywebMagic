package com.cxm.service;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author cxm
 * @description
 * @date 2019-10-18 11:48
 **/
public class SentenceService implements PageProcessor {

    Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setCharset("UTF-8")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");

    public Site getSite() {
        return this.site;
    }


    public void process(Page page) {
        String rawText = page.getRawText();
        String substring = rawText.substring(rawText.indexOf("(") + 1, rawText.lastIndexOf(")"));
        String content = new JsonPathSelector("$.content").select(substring);
        String note = new JsonPathSelector("$.note").select(substring);
        String translation = new JsonPathSelector("$.translation").select(substring);

        System.out.println("_______________________");
        System.out.println(content);
        System.out.println(note);
        System.out.println(translation);
        System.out.println("_______________________");

    }

    public static void main(String[] args) {


        for (int i = 1; i < 10; i++) {
            Date date = new Date();//获取当前时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -i);//当前时间减去一年，即一年前的时间
            String time = new SimpleDateFormat("yyyy-MM-dd ").format(calendar.getTime());//获取一年前的时间，或者一个月前的时间
            String url = "http://sentence.iciba.com/index.php?callback=jQuery19006980600652004427_1571377838833&c=dailysentence&m=getdetail&title=" + time;

            // String url = "http://news.iciba.com/views/dailysentence/daily.html#!/detail/title/2019-10-18";
            Spider.create(new SentenceService())
                    .addUrl(url)
                    .run();
        }





    }


}

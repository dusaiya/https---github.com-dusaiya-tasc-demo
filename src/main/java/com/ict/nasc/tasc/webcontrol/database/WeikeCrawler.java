/**
 * ICT NASC
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.ict.nasc.tasc.webcontrol.database;

import java.sql.ResultSet;
import java.sql.Statement;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequesterImpl;

import com.ict.nasc.tasc.webcontrol.model.WeikeTask;
import com.ict.nasc.tasc.webcontrol.tools.DbConnectionTool;
import com.ict.nasc.tasc.webcontrol.tools.TaskTool;

/**
 * 
 * @author xueye.duanxy
 * @version $Id: zhubajie.java, v 0.1 2015-11-10 上午10:01:03  Exp $
 */
public class WeikeCrawler extends DeepCrawler {
    /**
     * 数据库连接
     */
    private static Statement stmt;

    /**
     * 构造类
     * @param crawlPath
     * @throws Exception
     */
    public WeikeCrawler(String crawlPath) throws Exception {
        super(crawlPath);
        /**
         * 获取新浪微博的cookie，账号密码以明文形式传输，请使用小号
        StringBuilder cookie = new StringBuilder();
        cookie.append(LoginCookieConstant.userIdCookieAttrName + "="
                      + LoginCookieConstant.userIdValue + "; ");
        cookie.append(LoginCookieConstant.userKeyCookieAttrName + "="
                      + LoginCookieConstant.userKeyValue + "; ");
         */
        HttpRequesterImpl myRequester = (HttpRequesterImpl) this.getHttpRequester();
        myRequester
            .setCookie("__utma=149590013.933053285.1447139339.1447139339.1447139339.1; __utmb=149590013.4.10.1447139339; __utmc=149590013; __utmz=149590013.1447139339.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmt=1; Hm_lvt_20e969969d6befc1cc3a1bf81f6786ef=1447139339; Hm_lpvt_20e969969d6befc1cc3a1bf81f6786ef=1447139435; uniqid=4f54ae15df6f37d1ecbf1f0fecda7f9d; _analysis=74cf%2Fb3IzGoqDE3b252Nc5ZXkNbze88mnyffhnyJeMpEBRG3wOKQ6w3vAcQ4kNROol6GVM8Uy4lC3f6JbAOVKjRd231O6R09%2F3zFARaPtSULIlZRDUWg9pBgrN7b1zWOEZ3DcksXG6yBfKjV5dAqgPFypKvdboJrzmtFb4MnrSlua2xP8mowX%2F8SnLeHJhKIYB8fjSytyRDqLsqbn23IDrxFRjLNsUyKvEP%2BE0cgdFeF2GUxn1tOueUMX0fBuWNNxQ; fvtime=b97dGOq7lIuAkS8tKB6adz315xV6YgwBoSzbFwsmoDbebV1ixcYG; _uq=a25207e19ba0a417e83d12075e1dbc12; footerBarStateTask=0; _uv=2; userkey=7f17Pa2UMYVPkq4TXiQ6AHEYJRhz9%2B4x33zVMLT2DOp3vzrrL3I32lCLGecgsZIzlUssv3JVRCDve4Z3B8Odn2mZus4cjAsI8prdKyoo3aY4VEC4%2FNOyDF8AteyyMRvpq7wSxgRBaVMSeaqd%2BE%2FN43IR97YehGPpMFryw58dMxWmzwYEMGDye0%2BSY267BSY%2BMwPnrykOr5Q40hKz%2BIMSqV1t3rHrmYQxC9H%2B4McJQaRUMtAzUaIARFURGXzNEeZKhCWuKuIKz4qT; userid=13870023; nickname=crowdextract; brandname=crowdextract; viewed_task=13870023%3A6452414; _zbj_chat_=102; webimMainPage=go7lc025k1f");
        stmt = DbConnectionTool.getDbStatement();
    }

    @Override
    public Links visitAndGetNextLinks(Page page) {
        try {
            //实际处理
            fileOutput(page);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * 
     * @param page
     * @return
     */
    private Links fileOutput(Page page) {
        try {
            WeikeTask task = new WeikeTask();
            task.setTaskLink(page.getUrl());
            //文章标题
            Elements dls = page.getDoc().select("h1");
            task.setTaskTitle(dls.get(0).text());
            //发布者信息
            dls = page.getDoc().select("div.tctitle").select("a");
            TaskTool.releaseInfo(task, dls);
            //任务酬劳
            dls = page.getDoc().select("div.money-operate");
            TaskTool.paymentInfo(dls, task);
            //任务属性
            dls = page.getDoc().select("div.taskmode-block");
            TaskTool.weikeModeAnalyze(dls, task);

            //page.getDoc().select("div.nts") 下注信息
            dls = page.getDoc().select("div.grid").select("ul.ui-breadcrumb").select("li");
            task.setFirstCatagory(dls.get(0).select("a").attr("title"));
            if (dls.size() > 1) {
                task.setSecondCatagory(dls.get(1).select("a").attr("title"));
            }
            dls = page.getDoc().select("strong");
            for (Element dl : dls) {
                String text = dl.text();
                if (null != text && text.startsWith("需求")) {
                    task.setTaskId(dl.text().replace("需求号：", ""));
                }
            }
            //发布实际
            dls = page.getDoc().select("span.time");
            task.setTaskReleaseDate(dls.get(0).text());
            dls = page.getDoc().select("span.ads");
            for (Element dl : dls) {
                String sourcePlace = dl.text();
                String[] placelist = sourcePlace.split(" ");
                task.setReleaseProvince(placelist[0]);
                if (placelist.length > 1) {
                    task.setReleaseCity(placelist[1]);
                }
            }
            dls = page.getDoc().select("a.likt");
            task.setReleaseSource(dls.get(0).text());
            dls = page.getDoc().select("li.condition_item");
            for (Element dl : dls) {
                if (dl.text().contains("实名")) {
                    task.setRealNameAuthen(true);
                }
                if (dl.text().contains("手机认证")) {
                    task.setMobileAuthen(true);
                }
                if (dl.text().contains("保证完成")) {
                    task.setCompletementSecurity(true);
                }
                if (dl.text().contains("保证原创")) {
                    task.setOriginWork(true);
                }
            }
            //子页面信息
            dls = page.getDoc().select("div.pagination").select("a");
            TaskTool.subUrlInfo(page, task, dls);

            TaskTool.insertSql(task, stmt);
            //System.out.println(task);

        } catch (Exception e) {
            System.out.println(page.getUrl() + "【处理异常】" + e);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {

        try {
            WeikeCrawler crawler = new WeikeCrawler("/Users/alibaba/Desktop/zhubajie");

            crawler.setThreads(1);
            //年度跨度为了保证 标签属性相同
            String sql = "select task_link from task_list_final  where task_id not in(select task_id from task) limit 50";
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()) {
                crawler.addSeed(result.getString("task_link"));
            }
            //crawler.addSeed("http://task.zhubajie.com/4330315/");
            /**
             *
            crawler.addSeed("http://task.zhubajie.com/4330315/");//计件 粉丝数交付【非常特殊，需要单独处理，包括子任务】
            crawler.addSeed("http://task.zhubajie.com/741200/");//计件 没有每人限制
            crawler.addSeed("http://task.zhubajie.com/46963/");//比稿 比赛 2008
            crawler.addSeed("http://task.zhubajie.com/5157910");//计件 全部完成
            crawler.addSeed("http://task.zhubajie.com/6452414/");//计件
            crawler.addSeed("http://task.zhubajie.com/6585339/");//一人独享该赏金, 4项保障
            crawler.addSeed("http://task.zhubajie.com/6587921/");//招标 2015
            **/
            crawler.start(1);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Getter method for property <tt>stmt</tt>.
     * 
     * @return property value of stmt
     */
    public Statement getStmt() {
        return stmt;
    }

}

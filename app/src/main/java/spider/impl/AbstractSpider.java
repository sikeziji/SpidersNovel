package spider.impl;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * FileName: AbstractSpider
 * Author:   Wangj
 * Date:     2018/6/21 14:20
 */
public abstract class AbstractSpider {


    private static final String TAG = "AbstractSpider";

    /**
     * 2018/6/21
     * 抓取指定小说网站的内容
     *
     * @param url
     * @return
     * @throws Exception
     */
    protected String crawl(String url) throws Exception {
        String body = null;
        OkHttpClient client = new OkHttpClient();
        //请求
        Request request = new Request.Builder().url(url).build();
        //发起请求
        try {
            Response response = client.newCall(request).execute();
            body = new String(response.body().bytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;//取得目标
    }
}
package spider.impl.chapter;

import spider.Enum.NovelSiteEnum;
import spider.entitys.ChapterDetail;
import spider.impl.AbstractSpider;
import spider.interfaces.IChapterDetailSpider;
import spider.util.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.Map;

/**
 * FileName: AbstactChapterDetailSpider
 * Author:   Wangj
 * Date:     2018/6/21 14:18
 */
public class AbstractChapterDetailSpider extends AbstractSpider implements IChapterDetailSpider {

    private static final String TAG = "AbstactChapterDetailSpi";

    @Override
    public ChapterDetail getChapterDetail(String url) {
        try {
            String result = super.crawl(url);
            Document document = Jsoup.parse(result);
            document.setBaseUri(url);
            Map<String, String> contexts = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));


            //获取章节标题
            String titleSelector = contexts.get("chapter-detail-title-selector");
            String[] splits = titleSelector.split("\\,");
            splits = parseSelector(splits);
            ChapterDetail detail = new ChapterDetail();
            detail.setTitle(document.select(splits[0]).get(Integer.parseInt(splits[1])).text());

            //获取章节内容
            String contentSelector = contexts.get("chapter-detail-content-selector");
            Element first = document.select(contentSelector).first();
            String textContent1 = first.text().replaceAll("\\s[1,5]*","\n\t");
            detail.setContent(textContent1);

            //获取前一章地址
            String prevSelector = contexts.get("chapter-detail-prev-selector");
            splits = prevSelector.split("\\,");
            splits = parseSelector(splits);
            detail.setPrev(document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));

            //获取后一章地址
            String nextSelector = contexts.get("chapter-detail-next-selector");
            splits = nextSelector.split("\\,");
            splits = parseSelector(splits);
            detail.setNext(document.select(splits[0]).get(0).absUrl("href"));

            //设置默认的地址
            detail.setCurrentUrl(url);

            return detail;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * 处理集体元素的下标索引
     *
     * @param splits
     * @return
     */
    private String[] parseSelector(String[] splits) {
        String[] newSplits = new String[2];
        if (splits.length == 1) {
            newSplits[0] = splits[0];
            newSplits[1] = "0";
            return newSplits;
        } else {
            return splits;
        }
    }

}
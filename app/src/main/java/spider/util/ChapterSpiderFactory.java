package spider.util;

import spider.Enum.NovelSiteEnum;
import spider.impl.chapter.BxwxChapterSpider;
import spider.impl.chapter.DefaultChapterSpider;
import spider.interfaces.IChapterSpider;

/**
 * FileName: ChapterSpiderFactory
 * Author:   Wangj
 * Date:     2018/6/29 14:18
 */
public class ChapterSpiderFactory {

    private ChapterSpiderFactory(){}

    /**
     * 通过给定的url，返回一个实现了IChapterSpider接口的实现类
     * @param url
     * @return
     */
    public static IChapterSpider getChapterSpider(String url){
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
        IChapterSpider chapterSpider = null;
        switch (novelSiteEnum) {
            case BiXiaWenXue:
                chapterSpider = new BxwxChapterSpider();
                break;
            case DingDianXiaoShuo:
                chapterSpider = new BxwxChapterSpider();
                break;
            case DingDianXiaoShuoWang:
                chapterSpider = new BxwxChapterSpider();
                break;
            case BiQuGe:
                chapterSpider = new BxwxChapterSpider();
                break;
        }
        return chapterSpider;
    }
}
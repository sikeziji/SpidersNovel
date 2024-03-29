package spider.interfaces;

import spider.entitys.ChapterDetail;

public interface IChapterDetailSpider {

    /**
     * 给我一个url，我就给你一个对应网站的章节内容实体
     *
     * // 2018/6/21
     * @param url
     * @return
     */
    public ChapterDetail getChapterDetail(String url);
}

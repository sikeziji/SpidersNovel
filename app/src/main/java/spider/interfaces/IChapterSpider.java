package spider.interfaces;

import spider.entitys.Chapter;
import spider.entitys.ChapterDetail;

import java.util.List;

public interface IChapterSpider {

    /**
     * 给我们一个完整的url，我们就给你返回所有的章节列表
     * @param url
     * @return
     */
    public List<Chapter> getsChapters(String url);

    /**
     * 给我们一个完整的url，我们就给你当前的章节
     * @param url
     * @return
     */
    public ChapterDetail getsChapte(String url);


}

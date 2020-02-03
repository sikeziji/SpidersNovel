package com.example.spidersnovel;

import org.junit.Test;

import java.util.List;

import spider.Enum.NovelSiteEnum;
import spider.configuration.configuration;
import spider.entitys.Chapter;
import spider.entitys.Novel;
import spider.impl.AbstractSpider;
import spider.impl.chapter.BxwxChapterSpider;
import spider.impl.chapter.DefaultChapterDetailSpider;
import spider.impl.chapter.DefaultChapterSpider;
import spider.impl.download.NovelDownload;
import spider.interfaces.IChapterDetailSpider;
import spider.interfaces.IChapterSpider;
import spider.interfaces.INovelDownload;
import spider.interfaces.INovelSpider;
import spider.util.NovelSpiderFactory;
import spider.util.NovelSpiderUtil;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void test(){
        AbstractSpider abstractSpider = new BxwxChapterSpider();
        ((BxwxChapterSpider) abstractSpider).getsChapters("https://www.bxwx9.org/b/5/5740/41270028.html");
    }


    /**
     * 测试拿到顶点小说的章节列表
     */
    @Test
    public void testGetsChapter() throws Exception {
        IChapterSpider spider = new DefaultChapterSpider();
        List<Chapter> chapters = spider.getsChapters("http://www.23us.so/files/article/html/1/1969/index.html");
        for (Chapter chapter : chapters) {
            System.out.println(chapter);
        }
    }

    /**
     * 测试拿到顶点文学网的爬取方式
     */
    @Test
    public void testGetSiteContext() {
        System.out.println(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl("https://www.booktxt.net/2_2096/")));
    }


    /**
     * 测试拿到笔下文学的章节列表
     */
    @Test
    public void testGetsChapter3() {
        IChapterSpider spider = new BxwxChapterSpider();
        List<Chapter> chapters = spider.getsChapters("https://www.bxwx9.org/b/5/5740/");
        for (Chapter chapter : chapters) {
            System.out.println(chapter);
        }
    }

    /**
     * 爬取顶点小说的完美世界章节内容
     */
    @Test
    public void testGetChapterDetail() {
        IChapterDetailSpider spider = new DefaultChapterDetailSpider();
        System.out.println(spider.getChapterDetail("http://www.23us.so/files/article/html/19/19198/10917119.html"));
    }

    /**
     * 爬取笔下文学的元尊章节内容
     */
    @Test
    public void testGetChapterDetail2() {
        IChapterDetailSpider spider = new DefaultChapterDetailSpider();
        System.out.println(spider.getChapterDetail("https://www.bxwx9.org/b/5/5740/41270028.html").getContent());
    }

    /**
     * 爬取顶点文学的元尊章节内容
     */
    @Test
    public void testGetChapterDetail3() {
        IChapterDetailSpider spider = new DefaultChapterDetailSpider();
        System.out.println(spider.getChapterDetail("https://www.kanshuzhong.cc/book/21838/19532774.html").getContent());
    }


    @Test
    public void testGetChapterDetail4() {
        String url = "https://www.biquge.tw/479_479421/2785637.html";
        INovelDownload download = new NovelDownload();
        configuration config = new configuration();
        config.setPath("D:/小说");
        config.setTryTimes(10);

        System.out.println("下载好了，文件保存在：" + download.downloadChapterDetail(url, config) + "这里，赶紧去看看吧！");
    }

    @Test
    public void testDownload() {
        INovelDownload download = new NovelDownload();
        configuration config = new configuration();
        config.setPath("D:/小说");
        config.setSize(100);
        config.setTryTimes(10);
//        download.download("http://www.23us.so/files/article/html/1/1969/", config);
        //http://www.23us.cn/html/1/1980/
        System.out.println("下载好了，文件保存在：" + download.download("http://www.bxwx9.org/b/5/5740/index.html", config) + "这里，赶紧去看看吧！");
    }


    @Test
    public void testMultiFileMerge() {
        NovelSpiderUtil.multiFileMerge("D:/小说", null, true, "测试");
    }

    @Test
    public void testBxwxGetsNovels() {
        INovelSpider spider = NovelSpiderFactory.getNovelSpider("http://www.bxwx9.org");
        List<Novel> novels = spider.getsNovel("https://www.bxwx9.org/modules/article/index.php");
        for (Novel novel : novels) {
            System.out.println(novel);
        }
    }

    @Test
    public void testDdxsGetsNovels() {
        INovelSpider spider = NovelSpiderFactory.getNovelSpider("https://www.23us.so/top/allvote_1.html");
        List<Novel> novels = spider.getsNovel("https://www.23us.so/top/allvote_1.html");
        for (Novel novel : novels) {
            System.out.println(novel);
        }
    }

    @Test
    public void test36Ker() {

    }
}
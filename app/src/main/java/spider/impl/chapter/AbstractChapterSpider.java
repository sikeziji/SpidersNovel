package spider.impl.chapter;

import spider.Enum.NovelSiteEnum;
import spider.entitys.Chapter;
import spider.entitys.ChapterDetail;
import spider.impl.AbstractSpider;
import spider.interfaces.IChapterSpider;
import spider.util.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AbstractChapterSpider extends AbstractSpider implements IChapterSpider {

    private static final String TAG = "AbstractChapterSpiderSp";

    public List<Chapter> getsChapters(String url) {
        try {
            String resutlt = crawl(url);
            Document document = Jsoup.parse(resutlt);
            document.setBaseUri(url);
            Elements elements = document.select(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("chapter-list-selector"));
            List<Chapter> chapters = new ArrayList<>();
            for (Element a : elements) {
                Chapter character = new Chapter();
                character.setTitle(a.text());
                character.setUrl(a.absUrl("href"));
                chapters.add(character);
            }
            return chapters;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ChapterDetail getsChapte(String url) {
        String resutlt = null;
        try {
            resutlt = crawl(url);
            Document document = Jsoup.parse(resutlt);
            document.setBaseUri(url);
            Map<String, String> context = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
            System.out.println(context.get("chapter-list-selector"));
            Elements select = document.select(context.get("chapter-list-selector"));
            for (Element element : select) {
                System.out.println(element.text());
            }
//            System.out.println(select);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

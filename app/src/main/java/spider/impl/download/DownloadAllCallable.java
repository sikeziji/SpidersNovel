package spider.impl.download;

import spider.entitys.Chapter;
import spider.entitys.ChapterDetail;
import spider.interfaces.IChapterDetailSpider;
import spider.util.ChapterDetailSpiderFactor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.Callable;

public class DownloadAllCallable implements Callable<String> {
    private List<Chapter> chapters;
    private String path;
    private int trytimes;


    public DownloadAllCallable(String path, List<Chapter> chapters, int trytimes) {
        this.path = path;
        this.chapters = chapters;
        this.trytimes = trytimes;
    }


    @Override
    public String call() throws Exception {
        try (
                PrintWriter out = new PrintWriter(new File(path), "UTF-8");
        ) {
            for (Chapter chapter : chapters) {
                IChapterDetailSpider spider = ChapterDetailSpiderFactor.getChapterDetailSpider(chapter.getUrl());
                ChapterDetail detail = null;
                for (int i = 0; i < trytimes; i++) {
                    try {
                        detail = spider.getChapterDetail(chapter.getUrl());
                        out.println(detail.getTitle());
                        out.println(detail.getContent());
                        break;
                    } catch (RuntimeException e) {
                        System.out.println("尝试第 " + (i + 1) + "/" + trytimes + "次下载失败");
                    }

                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }
}

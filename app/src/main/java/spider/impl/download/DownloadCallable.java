package spider.impl.download;

import spider.entitys.Chapter;
import spider.entitys.ChapterDetail;
import spider.interfaces.IChapterDetailSpider;
import spider.util.ChapterDetailSpiderFactor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class DownloadCallable implements Callable<String> {
    private List<ChapterDetail> chapters = new ArrayList<>();
    private String path;
    private int trytimes;


    public DownloadCallable(String path, List<ChapterDetail> chapters, int trytimes) {
        chapters.clear();
        this.path = path;
        chapters.addAll(chapters);
        this.trytimes = trytimes;
    }

    public DownloadCallable(String path, ChapterDetail chapter, int trytimes) {
        this.path = path;
        this.trytimes = trytimes;
        if (!chapters.contains(chapter)) {
            chapters.add(chapter);
        }
    }


    @Override
    public String call() throws Exception {
        try (
                PrintWriter out = new PrintWriter(new File(path), "UTF-8");
        ) {
            for (ChapterDetail chapter : chapters) {
                for (int i = 0; i < trytimes; i++) {
                    try {
                        out.println(chapter.getTitle());
                        out.println(chapter.getContent());
                        out.println(chapter.getNext());
                        out.println(chapter.getPrev());
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

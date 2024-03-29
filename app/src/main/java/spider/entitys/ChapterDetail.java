package spider.entitys;


import org.apache.commons.lang.StringUtils;


import java.io.Serializable;
import java.util.Objects;

/**
 * FileName: ChapterDetail
 * Author:   Wangj
 * Date:     2018/6/21 14:01
 * 章节详细内容实体
 */
public class ChapterDetail implements Serializable {
    private String title;//标题
    private String content;//文章内容
    private String prev;//上一章地址
    private String next;//下一章地址
    private String currentUrl;//当前地址


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((next == null) ? 0 : next.hashCode());
        result = prime * result + ((prev == null) ? 0 : prev.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChapterDetail other = (ChapterDetail) obj;
        if (next == null) {
            if (other.next != null)
                return false;
        } else if (!next.equals(other.next))
            return false;
        if (prev == null) {
            if (other.prev != null)
                return false;
        } else if (!prev.equals(other.prev))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ChapterDetail{" +
                "title='" + title + '\'' +
                ", content='" + StringUtils.abbreviate(content, 30) + '\'' +
                ", prev='" + prev + '\'' +
                ", next='" + next + '\'' +
                '}';
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

    public void setCurrentUrl(String currentUrl) {
        this.currentUrl = currentUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
package com.haike.web.util;

import java.io.Serializable;

public class Page implements Serializable {

    private static final long serialVersionUID = 145646464664632L;

    private int               pageNow          = 1;               // 当前页数

    private int               pageSize         = 16;              // 每页显示记录的条数

    private int               totalCount;                         // 总的记录条数

    private int               totalPageCount;                     // 总的页数

    private String            id;

    private String            obj;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private int     startPos; // 开始位置，从0开始

    private boolean hasFirst; // 是否有首页

    private boolean hasPre;   // 是否有前一页

    private boolean hasNext;  // 是否有下一页

    private boolean hasLast;  // 是否有最后一页

    public Page(int totalCount, int pageNow) {
        this.totalCount = totalCount;
        this.pageNow = pageNow;
    }

    public Page(int totalCount, int pageNow, int pageSize) {
        this.totalCount = totalCount;
        this.pageNow = pageNow;
        this.pageSize = pageSize;
    }

    public int getTotalPageCount() {
        totalPageCount = getTotalCount() / getPageSize();
        return (totalCount % pageSize == 0) ? totalPageCount : totalPageCount + 1;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getStartPos() {
        return (pageNow - 1) * pageSize;
    }

    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    /**
     * 是否是第一页
     * 
     * @return
     */
    public boolean isHasFirst() {
        return (pageNow == 1) ? false : true;
    }

    public void setHasFirst(boolean hasFirst) {
        this.hasFirst = hasFirst;
    }

    public boolean isHasPre() {
        // 如果有首页就有前一页，因为有首页就不是第一页
        return isHasFirst() ? true : false;
    }

    public void setHasPre(boolean hasPre) {
        this.hasPre = hasPre;
    }

    public boolean isHasNext() {
        // 如果有尾页就有下一页，因为有尾页表明不是最后一页
        return isHasLast() ? true : false;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasLast() {
        // 如果不是最后一页就有尾页
        return (pageNow == getTotalCount()) ? false : true;
    }

    public void setHasLast(boolean hasLast) {
        this.hasLast = hasLast;
    }

    public Page() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Page(int pageNow, int pageSize, int totalCount, int totalPageCount, String id, String obj, int startPos,
            boolean hasFirst, boolean hasPre, boolean hasNext, boolean hasLast) {
        super();
        this.pageNow = pageNow;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPageCount = totalPageCount;
        this.id = id;
        this.obj = obj;
        this.startPos = startPos;
        this.hasFirst = hasFirst;
        this.hasPre = hasPre;
        this.hasNext = hasNext;
        this.hasLast = hasLast;
    }

    @Override
    public String toString() {
        return "Page [pageNow=" + pageNow + ", pageSize=" + pageSize + ", totalCount=" + totalCount
                + ", totalPageCount=" + totalPageCount + ", id=" + id + ", obj=" + obj + ", startPos=" + startPos
                + ", hasFirst=" + hasFirst + ", hasPre=" + hasPre + ", hasNext=" + hasNext + ", hasLast=" + hasLast
                + "]";
    }

}

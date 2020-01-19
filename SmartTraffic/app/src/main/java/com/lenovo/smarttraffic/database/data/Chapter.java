package com.lenovo.smarttraffic.database.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：gzj
 * @CreateDate: 2019/11/6
 */
public class Chapter {
    private String name;
    private List<ChapterItem> children = new ArrayList<>();


    public Chapter(String name) {
        this.name = name;
    }

    public  void addChild(ChapterItem chapterItem){
        children.add(chapterItem);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChapterItem> getChildren() {
        return children;
    }

    public void setChildren(List<ChapterItem> children) {
        this.children = children;
    }
}

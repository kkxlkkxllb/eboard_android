package org.eboard.app.core;

import java.io.Serializable;

/**
 * Created by libiao on 14-5-13.
 */
public class Word implements Serializable {
    private String title;
    private String content;
    private String objectId;

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

}

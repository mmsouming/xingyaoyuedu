package com.fancyfamily.primarylibrary.commentlibrary.commentbean;
/**
 * 消息vo
 * @author qhfang
 * */
public class MsgVo {
    private String name;// 消息标题

    private String content;// 消息内容

    private String time;// 消息时间

    private Long timestamp;// 时间戳

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}

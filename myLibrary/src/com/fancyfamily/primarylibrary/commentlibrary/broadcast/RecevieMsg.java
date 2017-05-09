package com.fancyfamily.primarylibrary.commentlibrary.broadcast;

import java.io.Serializable;

public class RecevieMsg implements Serializable{

    public int showType;
    public int pushMsgType;
    public String msg;
    public String title;
}

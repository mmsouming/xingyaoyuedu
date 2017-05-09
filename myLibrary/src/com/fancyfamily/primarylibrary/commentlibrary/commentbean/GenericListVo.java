package com.fancyfamily.primarylibrary.commentlibrary.commentbean;


import android.os.Parcel;

import java.io.Serializable;

/**
 * 通用列表vo
 * 
 * @author qhfang
 * */
public class GenericListVo implements Serializable {

    private Long id;
    private String name;
    private boolean choosed = false;
    protected GenericListVo(Parcel in) {
        id = in.readLong();
        name = in.readString();
    }

    public GenericListVo(Long id, String name, boolean choosed) {
        this.id = id;
        this.name = name;
        this.choosed = choosed;
    }



    public Long getId() {
        return id;
    }

    public boolean isChoosed() {
        return choosed;
    }

    public void setChoosed(boolean choosed) {
        this.choosed = choosed;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

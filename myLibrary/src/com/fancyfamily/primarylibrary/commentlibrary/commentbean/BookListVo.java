package com.fancyfamily.primarylibrary.commentlibrary.commentbean;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * 图书列表显示vo
 * @author qhfang
 * */
public class BookListVo implements Parcelable {
    private Long id;// 书目id


    public String name;// 书目名

    public String coverUrl;// 封面URL

    public String locationTags;// 定位标签

    private Integer recommend;// 推荐指数(满分100)


    public String author;//作者
    
    public	String introduction;//简介
    private boolean choosed = false;
    
    public BookListVo() {
        // TODO Auto-generated constructor stub
    }

    public BookListVo(Long id, String name, String coverUrl, String locationTags, Integer recommend, String author, String introduction, boolean choosed) {
        this.id = id;
        this.name = name;
        this.coverUrl = coverUrl;
        this.locationTags = locationTags;
        this.recommend = recommend;
        this.author = author;
        this.introduction = introduction;
        this.choosed = choosed;
    }

    protected BookListVo(Parcel in) {
        id = in.readLong();
        name = in.readString() ;
        coverUrl = in.readString() ;
        locationTags = in.readString();
        recommend = in.readInt() ;
        author = in.readString() ;
        introduction = in.readString() ;
    }

    public static final Creator<BookListVo> CREATOR = new Creator<BookListVo>() {
        @Override
        public BookListVo createFromParcel(Parcel in) {
            return new BookListVo(in);
        }

        @Override
        public BookListVo[] newArray(int size) {
            return new BookListVo[size];
        }
    };

    public Long getId() {
        return id;
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

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getLocationTags() {
        return locationTags;
    }

    public void setLocationTags(String locationTags) {
        this.locationTags = locationTags;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public boolean isChoosed() {
        return choosed;
    }

    public void setChoosed(boolean choosed) {
        this.choosed = choosed;
    }

    public static Creator<BookListVo> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(coverUrl);
        dest.writeString(locationTags);
        dest.writeInt(recommend);
        dest.writeString(author);
        dest.writeString(introduction);
    }

//
//    private void readFromParcel(Parcel in) {
//        id = in.readLong();
//        name = in.readString() ;
//        coverUrl = in.readString() ;
//        locationTags = in.readString();
//        recommend = in.readInt() ;
//        author = in.readString() ;
//        introduction = in.readString() ;
//    }
}

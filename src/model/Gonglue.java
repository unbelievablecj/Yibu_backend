package model;

import com.google.gson.annotations.SerializedName;
/**
 * 攻略model类
 * @author unbel
 *
 */
public class Gonglue {
    /**
     * 总攻略id
     */
    @SerializedName("Gid")
    private int Gid;
    /**
     * 原定于表示点集，已废弃
     */
    @SerializedName("Gpoint")
    private String Gpoint;
    /**
     * 用户id
     */
    @SerializedName("Guser")
    private String Guser;
    /**
     * 经度，已废弃
     */
    @SerializedName("Gjing")
    private double Gjing;
    /**
     * 维度，已废弃
     */
    @SerializedName("Gwei")
    private double Gwei;
    /**
     * 评论
     */
    @SerializedName("comment")
    private String comment;
    /**
     * 图片url集
     */
    @SerializedName("picture")
    private String picture;
    /**
     * 路线点集
     */
    @SerializedName("route")
    private String route;
    /**
     * 点赞数
     */
    @SerializedName("num_likes")
    private String num_likes;
    /**
     * 发布时间
     */
    @SerializedName("publish_time")
    private String publish_time;
    /**
     * 标题
     */
    @SerializedName("title")
    private String title;
    /**
     * 经度
     */
    @SerializedName("Longitude")
    private double  Longitude;
    /**
     * 维度
     */
    @SerializedName("Latitude")
    private double  Latitude;

    @SerializedName("dotStrategy")
    private String dotStrategy;
    
    @SerializedName("label")
    private String label;
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

   

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getNum_likes() {
        return num_likes;
    }

    public void setNum_likes(String num_likes) {
        this.num_likes = num_likes;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public String getDotStrategy() {
        return dotStrategy;
    }

    public void setDotStrategy(String dotStrategy) {
        this.dotStrategy = dotStrategy;
    }

    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public int getGid() {
        return Gid;
    }
    public void setGid(int gid) {
        Gid = gid;
    }
    public String getGpoint() {
        return Gpoint;
    }
    public void setGpoint(String gpoint) {
        Gpoint = gpoint;
    }
    public String getGuser() {
        return Guser;
    }
    public void setGuser(String guser) {
        Guser = guser;
    }
    public double getGjing() {
        return Gjing;
    }
    public void setGjing(double gjing) {
        Gjing = gjing;
    }
    public double getGwei() {
        return Gwei;
    }
    public void setGwei(double gwei) {
        Gwei = gwei;
    }


}

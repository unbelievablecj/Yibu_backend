package model;

import com.google.gson.annotations.SerializedName;
/**
 * ����model��
 * @author unbel
 *
 */
public class Gonglue {
    /**
     * �ܹ���id
     */
    @SerializedName("Gid")
    private int Gid;
    /**
     * ԭ���ڱ�ʾ�㼯���ѷ���
     */
    @SerializedName("Gpoint")
    private String Gpoint;
    /**
     * �û�id
     */
    @SerializedName("Guser")
    private String Guser;
    /**
     * ���ȣ��ѷ���
     */
    @SerializedName("Gjing")
    private double Gjing;
    /**
     * ά�ȣ��ѷ���
     */
    @SerializedName("Gwei")
    private double Gwei;
    /**
     * ����
     */
    @SerializedName("comment")
    private String comment;
    /**
     * ͼƬurl��
     */
    @SerializedName("picture")
    private String picture;
    /**
     * ·�ߵ㼯
     */
    @SerializedName("route")
    private String route;
    /**
     * ������
     */
    @SerializedName("num_likes")
    private String num_likes;
    /**
     * ����ʱ��
     */
    @SerializedName("publish_time")
    private String publish_time;
    /**
     * ����
     */
    @SerializedName("title")
    private String title;
    /**
     * ����
     */
    @SerializedName("Longitude")
    private double  Longitude;
    /**
     * ά��
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

package model;
/**
 * 用于从json转化出信息的类
 * @author unbel
 *
 */
public class Ask {
    private int begin;
    private int end;
    private double jing;
    private double wei;

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public double getJing() {
        return jing;
    }

    public void setJing(double jing) {
        this.jing = jing;
    }

    public double getWei() {
        return wei;
    }

    public void setWei(double wei) {
        this.wei = wei;
    }
}
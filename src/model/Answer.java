package model;

import com.google.gson.annotations.SerializedName;
/**
 * ���ڷ���json��ģ����
 * @author unbel
 *
 */
public class Answer {
	@SerializedName("res")
	private String res;
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res= res;
	}
}

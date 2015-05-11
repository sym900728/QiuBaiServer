package com.qiubai.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PictureDetail {

	private int pic_id;
	private int id;
	private String pic_address;
	private String pic_describe;

	public PictureDetail() {
		super();
	}

	public PictureDetail(int pic_id, int id, String pic_address,
			String pic_describe) {
		super();
		this.pic_id = pic_id;
		this.id = id;
		this.pic_address = pic_address;
		this.pic_describe = pic_describe;
	}

	public int getPic_id() {
		return pic_id;
	}

	public void setPic_id(int pic_id) {
		this.pic_id = pic_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPic_address() {
		return pic_address;
	}

	public void setPic_address(String pic_address) {
		this.pic_address = pic_address;
	}

	public String getPic_describe() {
		return pic_describe;
	}

	public void setPic_describe(String pic_describe) {
		this.pic_describe = pic_describe;
	}

}

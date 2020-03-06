package com.Flipbook.model;

import java.awt.image.BufferedImage;

public class PicInfo {

	private String picName;
	private BufferedImage pic;

	// 경로와 이미지
	public PicInfo() {

		this.picName = "";
		this.pic = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);

	}

	public PicInfo(String picName, BufferedImage pic) {
		super();
		this.picName = picName;
		this.pic = pic;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public BufferedImage getPic() {
		return pic;
	}

	public void setPic(BufferedImage pic) {
		this.pic = pic;
	}

	@Override
	public String toString() {
		return "PicInfo [picName=" + picName + ", pic=" + pic + "]";
	}

}

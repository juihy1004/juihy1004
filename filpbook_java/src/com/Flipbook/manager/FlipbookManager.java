package com.Flipbook.manager;

import java.awt.Color;

import java.awt.image.BufferedImage;

import com.Flipbook.controller.ColorBoardFunction;
import com.Flipbook.controller.FBFunction;
import com.Flipbook.controller.ImageInOutFunction;
import com.Flipbook.model.Flipbook;
import com.Flipbook.model.PicInfo;

public class FlipbookManager {

	private ColorBoardFunction cbf = new ColorBoardFunction();
	private FBFunction fbf = new FBFunction();
	private ImageInOutFunction imagef = new ImageInOutFunction();
	
	private PicInfo pi = new PicInfo();

	public ImageInOutFunction getImagef() {
		return imagef;
	}

	public void setImagef(ImageInOutFunction imagef) {
		this.imagef = imagef;
	}

	// FBFunction 에 있는 메소드를 실행하는 메소드들

	public String showList() {

		String str = fbf.showList(getImagef().getFb().getPicString(), getImagef().getPicInfo().getPicName(),
				getImagef().getFb().getTitle());

		return str;

	}

	// ImageInOutFunction 에 있는 메소드를 실행하는 메소드들

	public BufferedImage[] imageList() {
		return imagef.imageList();
	}

	public void passTitle(String title) {
		imagef.passTitle(title);
	}

	public BufferedImage firstpic() {
		BufferedImage pic = imagef.firstPic();
		return pic;
	}

	public BufferedImage newPic() {
		BufferedImage pic = imagef.newPic();
		return pic;
	}

	public BufferedImage takeNextPic() {

		return imagef.takeNextPic();

	}

	public BufferedImage takePrePic() {

		return imagef.takePrePic();
	}

	public void savePic() {
		imagef.savePic();
	}

	// ColorBoardFunction 에 있는 메소드를 실행하는 메소드들

	public Color changeBlackPen() {

		return cbf.changeBlackPen();

	}

	public Color changeRedPen() {

		return cbf.changeRedPen();
	}

	public Color changeGreedPen() {

		return cbf.changeGreedPen();

	}

	public Color changeBluePen() {

		return cbf.changeBluePen();

	}

	public Color changeEraser() {

		return cbf.changeEraser();

	}

	public BufferedImage allEraser(BufferedImage bi) {
		BufferedImage bi2 = cbf.allEraser(bi);
		return bi2;
	}
	
	public BufferedImage allEraser_b(BufferedImage bi) {
		BufferedImage bi2 = cbf.allEraser_b(bi);
		return bi2;
	}

}

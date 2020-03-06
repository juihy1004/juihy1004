package com.Flipbook.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import com.Flipbook.model.Brush;
import com.Flipbook.model.Flipbook;
import com.Flipbook.model.PicInfo;

public class ImageInOutFunction {

	private Flipbook fb;
	private PicInfo picInfo = new PicInfo();
	private String jarLoad = ClassLoader.getSystemClassLoader().getResource(".").getPath();

	public Flipbook getFb() {
		return fb;
	}

	public void setFb(Flipbook fb) {
		this.fb = fb;
	}

	public PicInfo getPicInfo() {
		return picInfo;
	}

	public void setPicInfo(PicInfo picInfo) {
		this.picInfo = picInfo;
	}

	// method
	public void passTitle(String title) {

		fb = new Flipbook(title);

	}

	public BufferedImage firstPic() {

		String firstPicName = null;

		if (fb.getPicString().size() > 0) {

			firstPicName = fb.getPicString().get(0);
			// getpicString - arrayList, getTitle -> string

		} else {

			fb.getPicString().add(jarLoad+"flipbookImages\\" + fb.getTitle() + "\\" + fb.getTitle() + "0000" + ".png");

			firstPicName = fb.getPicString().get(0);
			BufferedImage bi = new BufferedImage(840, 750, BufferedImage.TYPE_INT_RGB);

			for (int i = 0; i < bi.getWidth(); i++)
				for (int j = 0; j < bi.getHeight(); j++) {
					bi.setRGB(i, j, Color.white.getRGB());
				}

			picInfo.setPicName(firstPicName);
			picInfo.setPic(bi);

			try {
				ImageIO.write(picInfo.getPic(), "PNG", new File(picInfo.getPicName()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			return picInfo.getPic();

		}

		BufferedImage bi = new BufferedImage(840, 750, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < bi.getWidth(); i++)
			for (int j = 0; j < bi.getHeight(); j++) {
				bi.setRGB(i, j, Color.white.getRGB());
			}

		BufferedImage bi2 = null;

		try {
			bi2 = ImageIO.read(new File(firstPicName));
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		for (int x = 0; x < 840; x++) {
			for (int y = 0; y < 750; y++) {
				bi.setRGB(x, y, bi2.getRGB(x, y));
			}
		}

		// getter, setter
		picInfo.setPicName(firstPicName);
		picInfo.setPic(bi);

		return picInfo.getPic();

	}

	public BufferedImage newPic() {

		// 아마 처음 실행하면 예외 발생할듯
		// try-catch 써서 예외처리 해주자!
		
		String picName = "";
		BufferedImage newPic = null;
		
		if(fb.getPicString().size()<10) {
			
			picName = jarLoad+"flipbookImages\\" + fb.getTitle() + "\\" + fb.getTitle() +"000"+ fb.getPicString().size() + ".png";
			newPic = new BufferedImage(840, 750, BufferedImage.TYPE_INT_RGB);
			for (int i = 0; i < newPic.getWidth(); i++)
				for (int j = 0; j < newPic.getHeight(); j++) {
					newPic.setRGB(i, j, Color.white.getRGB());
				}

			fb.getPicString()
					.add(jarLoad+"flipbookImages\\" + fb.getTitle() + "\\" + fb.getTitle() +"000"+ fb.getPicString().size() + ".png");
			
		}else if(fb.getPicString().size()>=10 && fb.getPicString().size()<100) {
			
			picName = jarLoad+"flipbookImages\\" + fb.getTitle() + "\\" + fb.getTitle() +"00"+ fb.getPicString().size() + ".png";
			newPic = new BufferedImage(840, 750, BufferedImage.TYPE_INT_RGB);
			for (int i = 0; i < newPic.getWidth(); i++)
				for (int j = 0; j < newPic.getHeight(); j++) {
					newPic.setRGB(i, j, Color.white.getRGB());
				}

			fb.getPicString()
					.add(jarLoad+"flipbookImages\\" + fb.getTitle() + "\\" + fb.getTitle() +"00"+ fb.getPicString().size() + ".png");
			
		}else {
			
			picName = jarLoad+"flipbookImages\\" + fb.getTitle() + "\\" + fb.getTitle() + fb.getPicString().size() + ".png";
			newPic = new BufferedImage(840, 750, BufferedImage.TYPE_INT_RGB);
			for (int i = 0; i < newPic.getWidth(); i++)
				for (int j = 0; j < newPic.getHeight(); j++) {
					newPic.setRGB(i, j, Color.white.getRGB());
				}

			fb.getPicString()
					.add(jarLoad+"flipbookImages\\" + fb.getTitle() + "\\" + fb.getTitle() + fb.getPicString().size() + ".png");
			
		}
		
//		picName = "flipbookImages\\" + fb.getTitle() + "\\" + fb.getTitle() + fb.getPicString().size() + ".png";
//		newPic = new BufferedImage(840, 750, BufferedImage.TYPE_INT_RGB);
//		for (int i = 0; i < newPic.getWidth(); i++)
//			for (int j = 0; j < newPic.getHeight(); j++) {
//				newPic.setRGB(i, j, Color.white.getRGB());
//			}
//
//		fb.getPicString()
//				.add("flipbookImages\\" + fb.getTitle() + "\\" + fb.getTitle() + fb.getPicString().size() + ".png");

		picInfo.setPicName(picName);
		picInfo.setPic(newPic);

		try {
			ImageIO.write(picInfo.getPic(), "PNG", new File(picInfo.getPicName()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return picInfo.getPic();

	}

	public BufferedImage takeNextPic() {

		String nextPicName = "";
		try {

			for (int i = 0; i < fb.getPicString().size(); i++) {
				if (fb.getPicString().get(i).equals(picInfo.getPicName())) {
					if (i == fb.getPicString().size()) {
						return null;// 현재 그림이 마지막 그림이라면 다음 그림은 없으니 null을 리턴.
					}
					nextPicName = fb.getPicString().get(i + 1);
				}
			}

			BufferedImage bi2 = null;

			try {
				bi2 = ImageIO.read(new File(nextPicName));
			} catch (IOException e2) {
				e2.printStackTrace();
			}

			picInfo.setPicName(nextPicName);
			picInfo.setPic(bi2); 

		} catch (Exception e) {

		}
		return picInfo.getPic();

	}

	public BufferedImage takePrePic() {

		String prePicName = "";
		try {

			for (int i = 0; i < fb.getPicString().size(); i++) {
				if (fb.getPicString().get(i).equals(picInfo.getPicName())) {
					if (i == 0) {
						return null; // 현재 그림이 처음 그림이여서 이전그림이 없으니 null 리턴.
					}
					prePicName = fb.getPicString().get(i - 1);
				}
			}

			BufferedImage bi2 = null;
			try {
				bi2 = ImageIO.read(new File(prePicName));
			} catch (IOException e2) {
				e2.printStackTrace();
			}

			picInfo.setPicName(prePicName);
			picInfo.setPic(bi2);

		} catch (Exception e) {

		}
		return picInfo.getPic();

	}

	public void savePic() {

		try {
			ImageIO.write(picInfo.getPic(), "PNG", new File(picInfo.getPicName()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public BufferedImage[] imageList() {

		BufferedImage[] barr = new BufferedImage[fb.getPicString().size()];

		for (int i = 0; i < fb.getPicString().size(); i++) {

			try {
				barr[i] = ImageIO.read(new File(fb.getPicString().get(i)));
			} catch (IOException e) {

				e.printStackTrace();
			}

		}

		return barr;

	}

}

package com.Flipbook.model;

import java.io.File;
import java.util.ArrayList;

public class Flipbook {

	private String title;
	private ArrayList<String> picString;

	public Flipbook() {
	}

	public Flipbook(String title) {
		
		File flipbookfolder = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath()+"\\flipbookImages");
		ArrayList<String> pic = new ArrayList<String>();

		for (File fileEntry : flipbookfolder.listFiles()) {
			if (fileEntry.getName().equals(title)) {
				File[] fPic = fileEntry.listFiles();
				for (int i = 0; i < fPic.length; i++) {
					pic.add(ClassLoader.getSystemClassLoader().getResource(".").getPath()+"\\flipbookImages\\" + fileEntry.getName() + "\\" + fPic[i].getName());
				}

				this.title = title;
				this.picString = pic;
				return;
			}
		}

		File newfolder = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath()+"\\flipbookImages\\" + title);
		newfolder.mkdir();// 경로생성
		this.title = title;
		this.picString = pic;

		// 폴더 이름을 키로 가지고,
		// image의 경로 문자열을 객체를 리스트로 가지는 ArrayList 가 값을 가짐.

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<String> getPicString() {
		return picString;
	}

	public void setPicString(ArrayList<String> picString) {
		this.picString = picString;
	}

	@Override
	public String toString() {
		return "Flipbook [title=" + title + ", picString=" + picString + "]";
	}

}

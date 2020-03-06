package com.Flipbook.controller;

import java.util.ArrayList;

import com.Flipbook.manager.FlipbookManager;
import com.Flipbook.model.Flipbook;
import com.Flipbook.model.PicInfo;

public class FBFunction {

	public String showList(ArrayList<String> arr2, String picName, String title) {

		ArrayList<String> arr = arr2;

		String totalStr = "";
		String str = "";

		for (int i = 0; i < arr.size(); i++) {

			if (i == 0) {
				if (arr.get(i).equals(picName)) {
					str = "<html><body> 현재파일 : " + title + "0" + i + "<br>";
					totalStr += str;
				} else {
					str = "<html><body>" + title + "0" + i + "<br>";
					totalStr += str;
				}
			} else if (i == arr.size() - 1) {
				if (arr.get(i).equals(picName)) {
					str = "현재 파일 : " + title + "0" + i + "<br></body></html>";
					totalStr += str;
				} else {
					str = title + "0" + i + "<br></body></html>";
					totalStr += str;
				}
			} else {
				if (arr.get(i).equals(picName)) {
					str = "현재 파일 : " + title + "0" + i + "<br>";
					totalStr += str;
				} else {
					str = title + "0" + i + "<br>";
					totalStr += str;
				}
			}

		}

		return totalStr;

	}

}

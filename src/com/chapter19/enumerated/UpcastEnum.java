package com.chapter19.enumerated;

enum Search { HITHER, YON }

public class UpcastEnum {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Search[] vals = Search.values();
		Enum<Search> e = Search.HITHER;
		for(Enum<Search> en : e.getClass().getEnumConstants())
			System.out.println(en);
	}
}

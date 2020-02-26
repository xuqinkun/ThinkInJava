package com.chapter18.io;

import java.util.prefs.Preferences;

public class PreferencesDemo {
	public static void main(String[] args) throws Exception {
		Preferences prefs = Preferences.
				userNodeForPackage(PreferencesDemo.class);
		prefs.put("Location", "CD");
		prefs.put("Date", "2017/8/9");
		prefs.putInt("Age", 21);
		prefs.putBoolean("Are you ok?", true);
		int usageCount = prefs.getInt("UsageCount", 0);
		usageCount++;
		prefs.putInt("UsageCount", usageCount);
		
		for(String key : prefs.keys())
			System.out.println(key + ": " + prefs.get(key, null));

		// You must always provide a default value:
		System.out.println("How old are you? " + prefs.getInt("Age", 0));
		
	}
}

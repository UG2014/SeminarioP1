package com.sp2014.demo3.data;

public class Helper {
	public static String INSTAGRAM_API_KEY = "ba908140df544237bd49ceb67c750329";
	public static String BASE_API_URL="https://api.instagram.com/v1";
	
	public static String getRecentMediaUrl(String tag){
			return BASE_API_URL + "/tags/" + tag + "/media/recent?client_id=" + INSTAGRAM_API_KEY; 
	}
}

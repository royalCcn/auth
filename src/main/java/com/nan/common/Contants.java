package com.nan.common;

/**
 * 常量类
 * @author nan
 *
 */
public class Contants {
	public static final boolean isSuccess = false;
	public static final boolean isDelete = false;

	public static final String code_success = "1";
	public static final String code_fail = "-1";
	
	public static final String msg_success = "success";
	public static final String msg_fail = "fail";							

	public static final int BATCH_COUNT = 20;								//Excel数据量
	
	public static final String freeMark = "templates/freeMark.ftl";

	public static final String ffmpegpath = "E:\\work\\videoConvert\\ffmpeg\\bin\\ffmpeg.exe"; 	// ffmpeg.exe的目录
	public static final String mencoderpath = "E:\\work\\videoConvert\\mencoder"; 				// mencoder工具安装的位置

	public static final String videofolder = "F:/video/flvTemp/"; 			// 需要被转换格式的视频目录
	public static final String videoRealPath = "F:/video/other/"; 			// 其他的视频目录

	public static final String targetfolder = "F:/video/flv/"; 				// 转码后视频保存的目录
	public static final String imageRealPath = "F:/video/img/"; 				// 截图的存放目录

}

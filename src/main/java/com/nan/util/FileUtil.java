package com.nan.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.nan.common.Contants;

/**
 * 文件处理工具
 * @author nan
 *
 */
public class FileUtil {
	
	/**
	 * 获取项目路径
	 * @return
	 */
	public static String getContext(HttpServletRequest request){
	    return request.getSession().getServletContext().getRealPath("/");
	}
	
	/**
	 * 视频上传
	 * @param request 
	 * @return
	 * @throws IOException 
	 */
	public static Map<String, String> mediaUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Map<String, String> map = new HashMap<String, String>();
		map.clear();
		MultipartFile multipartFile = null;
		// 转型为MultipartHttpRequest
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			// 获取对应file对象
			Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
			Iterator<String> fileIterator = multipartHttpServletRequest.getFileNames();

			while (fileIterator.hasNext()) {
				String fileKey = fileIterator.next();
				// 获取对应文件
				multipartFile = fileMap.get(fileKey);
				if (multipartFile.getSize() != 0L) {
					
					//开始
					System.out.println("进入视频上传控制层");
					//上传的多格式的视频文件-作为临时路径保存，转码以后删除-路径不能写'//'
					//String path = "E:/video/flvTemp/";
					String path = getContext(request) + Contants.videoRealPath;
					
					//是否创建视频临时存储文件位置
					File tempFile = new File(path);
					if (tempFile.exists()) {
						if (tempFile.isDirectory()) {
							System.out.println("该文件夹存在。");
						}else {
							 System.out.println("同名的文件存在，不能创建文件夹。");
						}
					}else {
						 System.out.println("文件夹不存在，创建该文件夹。");
						 tempFile.mkdir();
					}
					
					// 获取上传时候的文件名
					String filename = multipartFile.getOriginalFilename();
					// 获取文件后缀名
					String filename_extension = filename.substring(filename.lastIndexOf(".") + 1);
					System.out.println("视频的后缀名:" + filename_extension);
					
					//时间戳做新的文件名，避免中文乱码-重新生成filename
					long filename1 = new Date().getTime();
					filename = Long.toString(filename1) + "." + filename_extension;
					
					//去掉后缀的文件名
					String filename2 = filename.substring(0, filename.lastIndexOf("."));
					System.out.println("视频名为:"+filename2);
					
					//源视频地址+重命名后的视频名+视频后缀
					String yuanPATH = (path + filename);  
					
					System.out.println("视频的完整文件名1:"+filename);
					System.out.println("源视频路径为:"+yuanPATH);
					
					//上传到本地磁盘/服务器
					try {
						System.out.println("写入本地磁盘/服务器");
						InputStream is = multipartFile.getInputStream();
						OutputStream os = new FileOutputStream(new File(path, filename));
						int len = 0;
						byte[] buffer = new byte[2048];
						
						while ((len = is.read(buffer)) != -1) {
							os.write(buffer, 0, len);
						}
						os.close();
						os.flush();
						is.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					System.out.println("========上传完成，开始调用转码工具类=======");
					//调用转码机制flv mp4 f4v m3u8 webm ogg放行直接播放，
					//asx，asf，mpg，wmv，3gp，mov，avi，wmv9，rm，rmvb等进行其他转码为mp4
					if (filename_extension.equals("avi") || filename_extension.equals("rm") 
						|| filename_extension.equals("rmvb") || filename_extension.equals("wmv")
						|| filename_extension.equals("3gp")  || filename_extension.equals("mov")
						||filename_extension.equals("flv")   || filename_extension.equals("ogg")
						
						) {
						//调用转码
						try {
				            //转码和截图功能开始
				        	String filePath = yuanPATH;								//web传入的源视频
				        	System.out.println("ConverVideoTest说:传入工具类的源视频为:" + filePath);
				        	
				            ConvertUtil cvu = new ConvertUtil(filePath);  //传入path
				            String targetExtension = ".mp4";  						//设置转换的格式
				            boolean isDelSourseFile = true;
				            
				            //删除源文件
				            boolean beginConver = cvu.beginConver(targetExtension,isDelSourseFile,request);
				            System.out.println("转换是否完成：" + beginConver);
				            if (beginConver) {
				            	String videoPath = "video/flv/" + filename2 + targetExtension;
								map.put(fileKey, videoPath);
								map.put("message", "上传成功");
							}
				        } catch (Exception e) {
				            e.printStackTrace();
				        }  
						System.out.println("=================转码过程彻底结束=====================");
					}
					
				}
			}
		}
		return map;
	}
	
	/**
	 * 图片上传
	 * @param request 
	 * @return
	 * @throws IOException 
	 */
	public static Map<String, String> imgUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {

		Map<String, String> map = new HashMap<String, String>();
		map.clear();
		MultipartFile multipartFile = null;
		// 转型为MultipartHttpRequest
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			// 获取对应file对象
			Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
			Iterator<String> fileIterator = multipartHttpServletRequest.getFileNames();

			while (fileIterator.hasNext()) {
				String fileKey = fileIterator.next();
				// 获取对应文件
				multipartFile = fileMap.get(fileKey);
				if (multipartFile.getSize() != 0L) {
					// 文件目录
					String fileName = multipartFile.getOriginalFilename();
					String ext = fileName.substring(fileName.lastIndexOf("."));
					// 验证图片类型
					String tempExt = ext.toLowerCase();
					if (!tempExt.contains(".jpg") && !tempExt.contains(".gif") && !tempExt.contains(".png")
							&& !tempExt.contains(".jpeg")) {
						map.put("message", "只能上传jpg、gif、png类型的图片。");
						map.put("data", "");
						return map;
					}
					
					//D:\work\tomcat_jiangfang\webapps\jiangfang\WEB-INF\classes\static\images
					//String context = getContext(request);

					String realPath = System.getProperty("user.dir") + "/src/main/resources/static/image/";
					//String realPath = context + "WEB-INF/classes/static/images/front/";
					File dir = new File(realPath);
					if (!dir.exists()) {
						dir.mkdirs();
					}

					// 重新给文件命名
					String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
					String name = uuid + ext;

					File targetFile = new File(realPath, name);
					try {
						// 上传文件
						multipartFile.transferTo(targetFile);
						// 返回给前端页面的图片
						String path = "image/" + name;
						map.put(fileKey, path);
						map.put("message", "上传成功");
					} catch (Exception e) {
						map.put("data", "");
						map.put("message", "上传失败");
					}
				}
			}
		}
		return map;
	}
	
	/**
     * 删除某个文件夹下的所有文件夹和文件
     * @param delpath
     *            String
     * @throws FileNotFoundException
     * @throws IOException
     * @return boolean
     */
	public static boolean deleteFile(String delpath) throws Exception {
        try {
        	if (delpath != null && !"".equals(delpath)) {
        		delpath = delpath.replace("\\","/");
			}
        	//"D:/projects/skCustoms/src/main/resources/upload/excel/c649833243fb4ecdb28cb5e90335d25c.xlsx"
            File file = new File(delpath);
            // 当且仅当此抽象路径名表示的文件存在且是一个目录时，返回 true
            if (!file.isDirectory()) {
                file.delete();
            } else if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File delfile = new File(delpath + "\\" + filelist[i]);
                    if (!delfile.isDirectory()) {
                        delfile.delete();
                        System.out.println(delfile.getAbsolutePath() + "删除文件成功");
                    } else if (delfile.isDirectory()) {
                    	deleteFile(delpath + "\\" + filelist[i]);
                    }
                }
                System.out.println(file.getAbsolutePath() + "删除成功");
                file.delete();
            }

        } catch (FileNotFoundException e) {
            System.out.println("deletefile() Exception:" + e.getMessage());
        }
        return true;
    }

	/**
	 * 文件上传
	 * @param request 
	 * @return
	 * @throws IOException 
	 */
	public static Map<String, String> upload(HttpServletRequest request) throws IOException {
		//文件路径 
		String filePath = "";
		//String context = request.getSession().getServletContext().getRealPath("/");
		String context = System.getProperty("user.dir") + "/src/main/resources/static/file/";
		//String context = getContext(request) + "WEB-INF/classes/";
		
		Map<String, String> map = new TreeMap<String, String>();
		
		if (request instanceof DefaultMultipartHttpServletRequest) {
			//用于接收客户端上传的文件
			DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = (DefaultMultipartHttpServletRequest) request;
			for (Iterator<Entry<String, MultipartFile>> iterator = defaultMultipartHttpServletRequest.getFileMap().entrySet().iterator(); iterator.hasNext();) {
				Entry<String, MultipartFile> entry = iterator.next();
				String inputName = entry.getKey();
				MultipartFile file = entry.getValue();
				
				//文件名
				String fileName = file.getOriginalFilename();
				if (StringUtils.isNotBlank(fileName)) {
					String last = "";
					String path[] = fileName.split("\\.");
					for (int i = 0; i < path.length; i++) {
						last = path[i];
					}
					
					String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
					String name = uuid + "." + last;
					
					if (StringUtils.isNotBlank(last)) {
						if (last.contains("do")) {
							filePath = "word/" + name;
						}else if (last.contains("xl")) {
							filePath = "excel/" + name;
						}else if (last.contains("pp")) {
							filePath = "ppt/" + name;
						}else if (last.contains("pdf")) {
							filePath = "pdf/" + name;
						}else if (last.contains("txt")) {
							filePath = "txt/" + name;
						}else {
							filePath = "other/" + name;
						}
					}
					
					FileOutputStream fileOutputStream = new FileOutputStream(new File(context + filePath));
					fileOutputStream.write(file.getBytes());
					fileOutputStream.close();
				}
				map.put(inputName, context + filePath);
			}
		}
		return map;
	}
	
	/**
	 * 文件下载
	 * @param request 
	 * @param response 
	 * @return
	 */
	public void download(HttpServletRequest request,HttpServletResponse response) {
        try {
            //response.setContentType("application/vnd.ms-excel");//这里指定是excel格式
            String filePath = request.getParameter("filePath");
            if (StringUtils.isNotBlank(filePath)) {
            	String context = request.getSession().getServletContext().getRealPath("/");//得到上传服务器的路径
                filePath = context + "\\" + filePath; //这里的文件名称是注入进来的
                File file = new File(filePath);
                
                response.reset();
                response.setHeader("Content-Disposition", "attachment;filePath=" + new String(filePath.getBytes("UTF-8"),"iso-8859-1"));
            
                InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                byte[] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
                inputStream.close();
                
                OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                outputStream.write(buffer);
                outputStream.flush();
                outputStream.close();
			}
        } catch (Exception e) {
            e.printStackTrace();
        }     
    }
	
}

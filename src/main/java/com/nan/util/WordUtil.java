package com.nan.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class WordUtil {

	/**
	 * 生成word文件
	 * @param dataMap			word中需要展示的动态数据，用map集合来保存
	 * @param templateName 		word模板名称，例如：test.ftl
	 * @param filePath 			文件生成的目标路径，例如：D:/wordFile/
	 * @param fileName 			生成的文件名称，例如：test.doc
	 */
	@SuppressWarnings({ "deprecation", "rawtypes" })
	public static void createWord(Map dataMap, String templateName, String filePath, String fileName) {
		try {
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			configuration.setClassForTemplateLoading(WordUtil.class, "/");

			// 获取模板
			Template template = configuration.getTemplate(templateName);

			File outFile = new File(filePath + File.separator + fileName);
			if (!outFile.getParentFile().exists()) {
				outFile.getParentFile().mkdirs();
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));

			// 生成文件
			template.process(dataMap, out);

			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static FileInputStream getExcelTemplates(String tempName) throws FileNotFoundException {
		return new FileInputStream(ResourceUtils.getFile("classpath:templates/" + tempName));
	}
	
	public static String getWordTemplates(String tempName) {
		return "classpath:templates/" + tempName;
	}

}

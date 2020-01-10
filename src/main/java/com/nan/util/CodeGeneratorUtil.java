package com.nan.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * 代码生成器
 * @author nan
 *
 */
public class CodeGeneratorUtil {
	
	private static String driverName = "com.mysql.cj.jdbc.Driver";
	private static String db = "auth";
    private static String url = "jdbc:mysql://localhost:3306/" + db + "?characterEncoding=utf8&serverTimezone=UTC";
    private static String username = "root";
    private static String password = "123456";
	private static String outPutDir = System.getProperty("user.dir") + "/src/main/java";
    private static String projectPath = System.getProperty("user.dir");
	private static String[] tableName = new String[]{"log"};
	
	/*
	  "log","house","application","employee","employee_employeegroup",
			"employee_post","employeegroup","menu","menu_permission","permission","post","role","role_employee",
			"role_employeegroup","role_menu","role_permission"
	 */
	

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(outPutDir);
        globalConfig.setFileOverride(true);
        globalConfig.setActiveRecord(true);		// 不需要ActiveRecord特性的请改为false
        globalConfig.setEnableCache(false);		// XML 二级缓存
        globalConfig.setBaseResultMap(true);	// XML ResultMap
        globalConfig.setBaseColumnList(false);	// XML columList
        globalConfig.setAuthor("nan");
        globalConfig.setOpen(false);
        globalConfig.setSwagger2(true);
        globalConfig.setMapperName("%sMapper");	// 自定义文件命名，注意 %s 会自动填充表实体属性
        globalConfig.setXmlName("%sMapper");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setControllerName("%sController");
        
        mpg.setGlobalConfig(globalConfig);
        
        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName(driverName);
        dataSourceConfig.setUrl(url);
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        
        mpg.setDataSource(dataSourceConfig);
        
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.nan");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("serviceImpl");
        pc.setController("controller");
        
        mpg.setPackageInfo(pc);
        
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(tableName);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setEntityColumnConstant(true);
        strategy.setEntityBuilderModel(true);
        
        mpg.setStrategy(strategy);
        
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("autoConfig", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        mpg.setCfg(cfg);
        
        String templatePath = "/templates/mapper.xml.ftl";
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() 
                + "Mapper" + StringPool.DOT_XML;
            }
        });
        
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        
        // 关闭默认xml生成，调整生成xx至xx根目录
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
        
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
        
        System.err.println(mpg.getCfg().getMap().get("autoConfig"));
    }

}
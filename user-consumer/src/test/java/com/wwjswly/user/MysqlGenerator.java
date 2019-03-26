package com.wwjswly.user;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * 代码生成
 */
@RunWith(SpringRunner.class)
public class MysqlGenerator {
    /*** 数据源配置 */
    public static DataSourceConfig getDataSourceConfig() {
        return new DataSourceConfig().setDbType(DbType.MYSQL) // 数据库类型
                .setUrl("jdbc:mysql://localhost:3306/test?characterEncoding=utf8")
                .setDriverName("com.mysql.jdbc.Driver")
                .setUsername("root")
                .setPassword("root")
                .setTypeConvert(new MySqlTypeConvert() {
                    // 自定义数据库表字段类型转换【可选】
//                    @Override
//                    public DbColumnType processTypeConvert(String fieldType) {
//                        //System.out.println("转换类型：" + fieldType);
//                        // if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
//                        //    return DbColumnType.BOOLEAN;
//                        // }
//                        return super.processTypeConvert(fieldType);
//                    }
                });
    }

    @Test
    public void generator() {
        String author = "ZhaoFa";
        String controllerName = "web";
        String packagePathParent = "com.wwjswly.user"; // 包路径
        String packageModuleName = "";
        String packageApiModuleName = "";
        String packageControllerName = "web"; //这里是控制器包名，默认 web
        String baseOutPutDir = "D:/workspace/wwjswly/";
        String outputDir = baseOutPutDir + packageModuleName + "/";

        String[] tablePrefix = new String[]{}; // 表前缀（生成代码）
        String[] includeTableNames = {"user"}; // 需要生成的表 "ord_api_access_log", "ord_order_invoice", "ord_order_invoice_item"

        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<TableFill>();
        //tableFillList.add(new TableFill("ASDD_SS", FieldFill.INSERT_UPDATE));
        String controllerTemplatePath = "/templates/controller.java";
        String entityTemplatePath = "/templates/entity.java";
        String mapperTemplatePath = "/templates/mapper.java";
        String serviceTemplatePath = "/templates/service.java";
        String serviceImplTemplatePath = "/templates/serviceImpl.java";

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator()
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .setDataSource(getDataSourceConfig()) // 数据源配置
                .setGlobalConfig(getGlobalConfig(author, outputDir, controllerName)) // 全局配置
                .setStrategy(getStrategyConfig(tableFillList, tablePrefix, includeTableNames)) // 策略配置
                .setPackageInfo(getPackageConfig(packagePathParent, packageModuleName, packageControllerName))
                .setCfg(getInjectionConfig(outputDir))
                .setTemplate(getTemplateConfig(controllerTemplatePath, entityTemplatePath,
                        mapperTemplatePath, serviceTemplatePath, serviceImplTemplatePath));
        // 执行生成
        mpg.execute();

        // 生成自定义的 model
        controllerTemplatePath = "/templates/model.java";
        packageControllerName = "model"; //这里是控制器包名，默认 web
        controllerName = "Vo";
        String templateNull = null;
        outputDir = baseOutPutDir + packageApiModuleName + "/";
        mpg = new AutoGenerator()
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .setDataSource(getDataSourceConfig()) // 数据源配置
                .setGlobalConfig(getGlobalConfig(author, outputDir, controllerName)) // 全局配置
                .setStrategy(getStrategyConfig(tableFillList, tablePrefix, includeTableNames)) // 策略配置
                .setPackageInfo(getPackageConfig(packagePathParent, packageModuleName, packageControllerName))
                .setCfg(getInjectionConfig(outputDir))
                .setTemplate(getTemplateConfig(controllerTemplatePath, templateNull, templateNull, templateNull, templateNull));
        mpg.execute();

        /*
        // 生成自定义的 model
        controllerTemplatePath = "/templates/attributeMethod.java";
        packageControllerName = "attributeMethod"; //这里是控制器包名，默认 web
        controllerName = "";
        templateNull = null;
        mpg = new AutoGenerator()
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .setDataSource(getDataSourceConfig()) // 数据源配置
                .setGlobalConfig(getGlobalConfig(author, outputDir, controllerName)) // 全局配置
                .setStrategy(getStrategyConfig(tableFillList, tablePrefix, includeTableNames)) // 策略配置
                .setPackageInfo(getPackageConfig(packagePathParent, packageModuleName, packageControllerName))
                .setCfg(getInjectionConfig(outputDir))
                .setTemplate(getTemplateConfig(controllerTemplatePath, templateNull, templateNull, templateNull, templateNull));
        mpg.execute();
        */
    }

    /*** 包配置 */
    public static PackageConfig getPackageConfig(String packagePathParent, String moduleName, String controllerName) {
        return new PackageConfig()
                .setParent(packagePathParent) // 自定义包路径
                .setModuleName(moduleName)
                .setController(controllerName);// 这里是控制器包名，默认 web;
    }

    /*** 全局配置 */
    public static GlobalConfig getGlobalConfig(String author, String outputDir, String controllerName) {
        return new GlobalConfig()
                .setAuthor(author)
                .setOutputDir(outputDir)//输出目录
                .setFileOverride(true)// 是否覆盖文件
                .setActiveRecord(false)// 开启 activeRecord 模式
                .setEnableCache(false)// XML 二级缓存
                .setBaseResultMap(true)// XML ResultMap
                .setBaseColumnList(true)// XML columList
                //.setKotlin(true) 是否生成 kotlin 代码
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                // .setMapperName("%sDao")
                // .setXmlName("%sDao")
                // .setServiceName("MP%sService")
                // .setServiceImplName("%sServiceDiy")
                .setControllerName("%s" + controllerName);
    }

    /*** 策略配置 */
    public static StrategyConfig getStrategyConfig(List<TableFill> tableFillList, String[] tablePrefix, String[] includeTableNames) {
        return new StrategyConfig()
                // .setCapitalMode(true) // 全局大写命名
                // .setDbColumnUnderline(true) //全局下划线命名
                .setTablePrefix(tablePrefix) // 此处可以修改为您的表前缀
                .setNaming(NamingStrategy.underline_to_camel) // 表名生成策略
                .setTableFillList(tableFillList)
                .setInclude(includeTableNames) // 需要生成的表
                // .setExclude(new String[]{"test"}) // 排除生成的表
                // 自定义实体父类
                // .setSuperEntityClass("com.baomidou.demo.TestEntity")
                // 自定义实体，公共字段
                //.setSuperEntityColumns(new String[]{"test_id"})
                // 自定义 mapper 父类
                // .setSuperMapperClass("com.baomidou.demo.TestMapper")
                // 自定义 service 父类
                // .setSuperServiceClass("com.baomidou.demo.TestService")
                // 自定义 service 实现类父类
                // .setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl")
                // 自定义 controller 父类
                // .setSuperControllerClass("com.baomidou.demo.TestController")
                // 【实体】是否生成字段常量（默认 false）
                // public static final String ID = "test_id";
                // .setEntityColumnConstant(true)
                // 【实体】是否为构建者模型（默认 false）
                // public User setName(String name) {this.name = name; return this;}
                // .setEntityBuilderModel(true)
                // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                .setEntityLombokModel(true)
                // .setControllerMappingHyphenStyle(true)
                // Boolean类型字段是否移除is前缀处理
                .setEntityBooleanColumnRemoveIsPrefix(true)
                .setRestControllerStyle(true);
    }

    /*** 自定义配置 */
    public static InjectionConfig getInjectionConfig(String outputDir) {
        return new InjectionConfig() { // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                //map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        }.setFileOutConfigList(Collections.singletonList(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) { // 自定义输出文件目录
                return outputDir + "/resources/mapper/" + tableInfo.getEntityName() + ".xml";
            }
        }));

        // 打印注入设置，这里演示模板里面怎么获取注入内容【可无】
        //System.err.println(mpg.getCfg().getMap().get("abc"));
    }

    /*** 模板配置 */
    public static TemplateConfig getTemplateConfig(String controllerTemplatePath, String entityTemplatePath,
                                                   String mapperTemplatePath, String serviceTemplatePath, String serviceImplTemplatePath) {
        // 关闭默认xml生成，调整生成至根目录
        // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
        // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
        return new TemplateConfig()
                .setController(controllerTemplatePath)
                .setEntity(entityTemplatePath)
                .setMapper(mapperTemplatePath)
                .setService(serviceTemplatePath)
                .setServiceImpl(serviceImplTemplatePath)
                .setXml(null);
    }


}
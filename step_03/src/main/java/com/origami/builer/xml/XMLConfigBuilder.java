package com.origami.builer.xml;


import com.origami.builer.BaseBuilder;
import com.origami.io.Resources;
import com.origami.mapping.MappedStatement;
import com.origami.mapping.SqlCommandType;
import com.origami.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Origami
 * @description XML配置构建器 建造者模式 继承BaseBuilder 初始化Configuration
 * parse解析操作 通过 Configuration 配置类进行存放 包括 添加解析SQL 注册Mapper映射器
 * @create 2025-02-25 10:07
 **/
public class XMLConfigBuilder extends BaseBuilder {
    // XML文件根节点
    private Element root;

    public XMLConfigBuilder(Reader reader) {
        // 1.调用父类初始化Configuration
        super(new Configuration());

        // 2.dom4j解析xml文件
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new InputSource(reader));
            root = document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    public Configuration parse() {
        try {
            // 解析映射器
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException("解析映射器失败,", e);
        }
        return configuration;
    }

    private void mapperElement(Element mappers) throws Exception {
        // 解析mapper节点
        List<Element> mapperList = mappers.elements("mapper");
        for (Element mapper : mapperList) {
            // resource 指向具体的Mapper XML文件
            String resource = mapper.attributeValue("resource");
            Reader reader = Resources.getResourceAsReader(resource);
            // 解析XML文档 获取根元素<mapper> 提取namespace属性值
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new InputSource(reader));

            // 1.提取namespace属性值
            Element root = document.getRootElement();
            String namespace = root.attributeValue("namespace");

            // 2.提取所有 <select> 标签
            List<Element> selectList = root.elements("select");
            for (Element select : selectList) {
                String id = select.attributeValue("id");
                String parameterType = select.attributeValue("parameterType");
                String resultType = select.attributeValue("resultType");
                String sql = select.getTextTrim();

                // 3.?匹配（处理占位符）
                Map<Integer, String> parameters = new HashMap<>();
                Pattern pattern = Pattern.compile("(#\\{(.*?)})");
                Matcher matcher = pattern.matcher(sql);
                while (matcher.find()) {
                    String g1 = matcher.group(1);
                    String g2 = matcher.group(2);
                    parameters.put(parameters.size() + 1, g2);
                    sql = sql.replace(g1, "?");
                }
                // 4.注册Mapper映射器
                String msId = namespace + "." + id; // 命名空间.方法名
                String selectName = select.getName();
                SqlCommandType sqlCommandType = SqlCommandType.valueOf(selectName.toUpperCase(Locale.ENGLISH));
                MappedStatement mappedStatement = MappedStatement.builder()
                        .configuration(configuration)
                        .id(msId)
                        .sqlCommandType(sqlCommandType)
                        .parameterType(parameterType)
                        .resultType(resultType)
                        .sql(sql)
                        .parameter(parameters).build();
                // 注册映射器
                configuration.addMappedStatement(mappedStatement);

            }
            // 注册到 Mapper 映射器
            configuration.addMapper(Resources.classForName(namespace));
        }
    }
}

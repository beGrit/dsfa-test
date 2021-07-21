package com.dsfa.platform.service.core.web.test.lsf.nc.bqgl.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.activerecord.generator.MetaBuilder;

/**
 * @ClassName Generator
 * @Description TODO
 * @Author pocky
 * @Date 2021/7/21
 **/
public class JFinalGenerator {
    public static void main(String[] args) {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl("jdbc:mysql://125.69.82.54:13201/dsfa_dsfa5");
        dataSource.setUsername("dsfa5");
        dataSource.setPassword("Study_111111");

        // model 所使用的包名 (MappingKit 默认使用的包名)
        String modelPackageName = "com.dsfa.platform.service.core.web.test.lsf.nc.bqgl.model";

        // base model 所使用的包名
        String baseModelPackageName = modelPackageName + ".base";

        // base model 文件保存路径
        // 注意从 jfinal 4.9.12 版开始，PathKit.getWebRootPath() 在此的用法要改成 System.getProperty("user.dir")
        String baseModelOutputDir = System.getProperty("user.dir") + "/src/main/java/" + baseModelPackageName.replace('.', '/');

        // model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
        String modelOutputDir = baseModelOutputDir + "/..";

        System.out.println("输出路径：" + baseModelOutputDir);

        // 创建生成器
        Generator gen = new Generator(dataSource, baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);

        // 元数据构造规则
        MetaBuilder metaBuilder = new MetaBuilder(dataSource)
                // 使用 filter 方法定制过滤逻辑，返回 true 表示过滤掉当前 table
                .skip(tableName -> {
                    return !tableName.startsWith("test_lsf_nc_bqgl");
                });

        // 设置数据库方言
        gen.setDialect(new MysqlDialect());

        // 在 getter、setter 方法上生成字段备注内容
        gen.setGenerateRemarks(true);

        // 添加过滤器
        gen.setMetaBuilder(metaBuilder);

        // 设置是否生成链式 setter 方法
        gen.setGenerateChainSetter(true);

        // 设置是否在 Model 中生成 dao 对象
        gen.setGenerateDaoInModel(true);

        // 设置去除前缀
        gen.setRemovedTableNamePrefixes("test_lsf_nc_");

        // 开始生成代码
        gen.generate();
    }
}

package com.db117.adminstaging;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * mybatis代码自动生成
 *
 * @author 大兵
 * @date 2018-02-04 23:20
 **/
public class GeneratorServiceEntity {

    @Test
    public void generateCode() {
        String packageName = "com.db117.adminstaging.modules.sys";

        generateByTables(false, packageName);
    }

    /**
     * @param serviceNameStartWithI user -> UserService, 设置成true: user -> IUserService
     * @param packageName           包路径
     * @param tableNames            要生成的表名
     */
    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        //JDBC url
        String dbUrl = "jdbc:mysql://localhost:3306/admin";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();

        //数据库配置
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("root")
                .setDriverName("com.mysql.jdbc.Driver");

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                //是否大写命名
                .setCapitalMode(true)
                //【实体】是否为lombok模型（默认 false）
                .setEntityLombokModel(true)
                //【实体】是否为构建者模型（默认 false）
                .setEntityBuilderModel(true)
                //表名、字段名、是否使用下划线命名（默认 false）
                .setDbColumnUnderline(false)
                //数据库表映射到实体的命名策略
                .setNaming(NamingStrategy.underline_to_camel)
                //需要包含的表名（与exclude二选一配置）
                .setInclude(tableNames)

                //自定义继承的Entity类全称，带包名
                .setSuperEntityClass("com.db117.adminstaging.common.base.BaseEntity")
                //自定义基础的Entity类，公共字段
                .setSuperEntityColumns("id", "create_by", "create_date", "update_by", "update_date", "remarks", "del_flag")
                //自定义继承的Mapper类全称，带包名
                .setSuperMapperClass("com.db117.adminstaging.common.base.BaseDao")
                //自定义继承的Service类全称，带包名
                .setSuperServiceClass("com.db117.adminstaging.common.base.BaseService")
                //自定义继承的ServiceImpl类全称，带包名
                .setSuperServiceImplClass("com.db117.adminstaging.common.base.BaseServiceImpl")
                //自定义继承的Controller类全称，带包名
                .setSuperControllerClass("com.db117.adminstaging.common.base.BaseController");

        config
                //开发人员
                .setAuthor("db117")
                //生成文件的输出目录【默认 D 盘根目录】
                .setOutputDir("D:\\系统桌面")
                //是否覆盖已有文件
                .setFileOverride(true)
                // 不需要ActiveRecord特性的请改为false
                .setActiveRecord(false)
                // XML 二级缓存
                .setEnableCache(false)
                // XML ResultMap
                .setBaseResultMap(true)
                // XML columList
                .setBaseColumnList(true)
                //是否生成 kotlin 代码
                .setKotlin(false);

        //自定义文件命名，注意 %s 会自动填充表实体属性！
        config.setMapperName("%sDao");
        config.setXmlName("%sDao");
        config.setServiceName("%sService");
        config.setServiceImplName("%sServiceImpl");
        config.setControllerName("%sController");
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                //设置包名
                                .setParent(packageName)
                                .setMapper("dao")
                                .setController("controller")
                                .setEntity("entity")
                ).execute();
    }

    private void generateByTables(String packageName, String... tableNames) {
        generateByTables(true, packageName, tableNames);
    }
}


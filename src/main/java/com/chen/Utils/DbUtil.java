package com.chen.Utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ProjectName: xbjy
 * @Package: com.Utils
 * @ClassName: DbUtil
 * @Author: Chen
 * @Description: JdbcTemplate模板工具类
 * @Date: 2020/9/22 10:19
 * @Version: 1.0
 */
public class DbUtil {

    private static DruidDataSource druidDataSource;

    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        System.out.println("=======测试jdbcTemplate:" + jdbcTemplate);
    }

    static {
        Properties prop = new Properties();
        InputStream in = DbUtil.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            prop.load(in);
            druidDataSource = new DruidDataSource();
            druidDataSource.setUsername(prop.getProperty("jdbc.username"));
            druidDataSource.setPassword(prop.getProperty("jdbc.password"));
            druidDataSource.setUrl(prop.getProperty("jdbc.url"));
            druidDataSource.setDriverClassName(prop.getProperty("jdbc.driverClassName"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据源
     */
    public static DruidDataSource getDataSource() {
        return druidDataSource;
    }

}

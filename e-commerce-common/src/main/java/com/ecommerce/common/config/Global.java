package com.ecommerce.common.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.ecommerce.common.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局配置类
 *
 * @author huizhe yu
 */
@Slf4j
public class Global {

    private static String NAME = "application.properties";

    /**
     * 当前对象实例
     */
    private static Global global = null;

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = new HashMap<String, String>();

    private Global() {}

    /**
     * 静态工厂方法 获取当前对象实例 多线程安全单例模式(使用双重同步锁)
     */

    public static synchronized Global getInstance() {
        if (global == null) {
            synchronized (Global.class) {
                if (global == null) {
                    global = new Global();
                }
            }
        }
        return global;
    }

    /**
     * 获取配置
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            try {
                Properties properties = PropertiesLoaderUtils.loadAllProperties(NAME);
                value = String.valueOf(properties.getProperty(key));
                map.put(key, value != null ? value : StringUtils.EMPTY);
            } catch (Exception e) {
                log.error("获取全局配置异常 {}", key);
            }
        }
        return value;
    }

    /**
     * 获取项目名称
     */
    public static String getName() {
        return StringUtils.nvl(getConfig("project.name"), "Huizhe Yu");
    }

    /**
     * 获取项目版本
     */
    public static String getVersion() {
        return StringUtils.nvl(getConfig("project.version"), "1.0");
    }

    /**
     * 获取版权年份
     */
    public static String getCopyrightYear() {
        return StringUtils.nvl(getConfig("project.copyrightYear"), "2019");
    }

    /**
     * 获取ip地址开关
     */
    public static Boolean isAddressEnabled() {
        return Boolean.valueOf(getConfig("project.addressEnabled"));
    }

    /**
     * 获取文件上传路径
     */
    public static String getProfile() {
        return getConfig("project.profile");
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath() {
        return getConfig("project.profile") + "avatar/";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath() {
        return getConfig("project.profile") + "download/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath() {
        return getConfig("project.profile") + "upload/";
    }

    /**
     * 获取作者
     */
    public static String getAuthor() {
        return StringUtils.nvl(getConfig("gen.author"), "ruoyi");
    }

    /**
     * 生成包路径
     */
    public static String getPackageName() {
        return StringUtils.nvl(getConfig("gen.packageName"), "com.ruoyi.project.module");
    }

    /**
     * 是否自动去除表前缀
     */
    public static String getAutoRemovePre() {
        return StringUtils.nvl(getConfig("gen.autoRemovePre"), "true");
    }

    /**
     * 表前缀(类名不会包含表前缀)
     */
    public static String getTablePrefix() {
        return StringUtils.nvl(getConfig("gen.tablePrefix"), "sys_");
    }
}

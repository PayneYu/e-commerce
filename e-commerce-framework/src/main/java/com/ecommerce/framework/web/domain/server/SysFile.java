package com.ecommerce.framework.web.domain.server;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统文件相关信息
 *
 * @author huizhe yu
 */
@Getter
@Setter
@ToString
public class SysFile {
    /**
     * 盘符路径
     */
    private String dirName;

    /**
     * 盘符类型
     */
    private String sysTypeName;

    /**
     * 文件类型
     */
    private String typeName;

    /**
     * 总大小
     */
    private String total;

    /**
     * 剩余大小
     */
    private String free;

    /**
     * 已经使用量
     */
    private String used;

    /**
     * 资源的使用率
     */
    private double usage;
}

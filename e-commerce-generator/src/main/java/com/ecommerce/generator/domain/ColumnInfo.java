package com.ecommerce.generator.domain;

import com.ecommerce.common.json.JsonUtils;
import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 数据库表列信息
 *
 * @author huizhe.yu
 */
@Getter
@Setter
@ToString
public class ColumnInfo {

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 字段类型
     */
    private String dataType;

    /**
     * 列描述
     */
    private String columnComment;

    /**
     * 列配置
     */
    private ColumnConfigInfo configInfo;

    /**
     * Java属性类型
     */
    private String attrType;

    /**
     * Java属性名称(第一个字母大写)，如：user_name => UserName
     */
    private String attrName;

    /**
     * Java属性名称(第一个字母小写)，如：user_name => userName
     */
    private String attrname;

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrname() {
        return attrname;
    }

    public void setAttrname(String attrname) {
        this.attrname = attrname;
    }

    /**
     * 执行计划（包含了与索引相关的一些细节信息）
     */
    private String extra;

    private Boolean primaryKey;

    private String isNullable;
    private Integer characterMaximumLength;
    private Integer numericPrecision;
    private Integer numericScale;
    private Integer datetimePrecision;


    public void setColumnComment(String columnComment) throws Exception {
        // 根据列描述解析列的配置信息
        if (StringUtils.isNotEmpty(columnComment) && columnComment.startsWith("{")) {
            this.configInfo = JsonUtils.stringToObj(columnComment,ColumnConfigInfo.class);
            this.columnComment = configInfo.getTitle();
        } else {
            this.columnComment = columnComment;
        }
    }
}

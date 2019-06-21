package com.ecommerce.generator.service;

import com.ecommerce.generator.domain.TableInfo;

import java.util.List;


/**
 * 代码生成 服务层
 * 
 * @author huizhe.yu
 */
public interface IGenService {
    /**
     * 查询数据库表信息
     * 
     * @param tableInfo
     *            表信息
     * @return 数据库表列表
     */
     List<TableInfo> selectTableList(TableInfo tableInfo);

    /**
     * 生成代码
     * 
     * @param tableName
     *            表名称
     * @return 数据
     */
     byte[] generatorCode(String tableName);

    /**
     * 批量生成代码
     * 
     * @param tableNames
     *            表数组
     * @return 数据
     */
     byte[] generatorCode(String[] tableNames);
}

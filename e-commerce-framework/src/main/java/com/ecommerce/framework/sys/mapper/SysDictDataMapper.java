package com.ecommerce.framework.sys.mapper;

import org.apache.ibatis.annotations.Param;

import com.ecommerce.framework.base.mapper.BaseMapper;
import com.ecommerce.framework.sys.entity.SysDictData;

/**
 * 字典表 数据层
 * 
 * @author huizhe yu
 */
public interface SysDictDataMapper extends BaseMapper<SysDictData> {

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType
     *            字典类型
     * @param dictValue
     *            字典键值
     * @return 字典标签
     */
    String selectDictLabel(@Param("dictType") String dictType, @Param("dictValue") String dictValue);
}

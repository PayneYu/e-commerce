package com.ecommerce.framework.sys.mapper;

import com.ecommerce.framework.base.mapper.BaseMapper;
import com.ecommerce.framework.sys.entity.SysDictType;

import java.util.List;

/**
 * 字典表 数据层
 * 
 * @author huizhe yu
 */
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    List<SysDictType> selectDictTypeList(SysDictType dictType);

}

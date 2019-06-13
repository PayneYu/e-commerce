package com.ecommerce.framework.sys.service;

import com.ecommerce.framework.base.service.BaseService;
import com.ecommerce.framework.sys.entity.SysDictType;

import java.util.List;

/**
 * 字典 业务层
 * 
 * @author huizhe yu
 */
public interface ISysDictTypeService extends BaseService<SysDictType> {
    /**
     * 根据条件分页查询字典类型
     * 
     * @param dictType
     *            字典类型信息
     * @return 字典类型集合信息
     */
    List<SysDictType> selectDictTypeList(SysDictType dictType);
    /**
     * 批量删除字典类型
     * 
     * @param ids
     *            需要删除的数据
     * @return 结果
     * @throws Exception
     *             异常
     */
    void deleteDictTypeByIds(String ids) throws Exception;

    /**
     * 修改保存字典类型信息
     * 
     * @param dictType
     *            字典类型信息
     * @return 结果
     */
    void updateDictType(SysDictType dictType);

}

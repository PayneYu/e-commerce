package com.ecommerce.framework.sys.service;

import java.util.List;

import com.ecommerce.framework.base.service.BaseService;
import com.ecommerce.framework.sys.entity.SysDictData;

/**
 * 字典 业务层
 * 
 * @author huizhe yu
 */
public interface ISysDictDataService extends BaseService<SysDictData> {
    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType
     *            字典类型
     * @return 字典数据集合信息
     */
    List<SysDictData> selectDictDataByType(String dictType);

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType
     *            字典类型
     * @param dictValue
     *            字典键值
     * @return 字典标签
     */
    String selectDictLabel(String dictType, String dictValue);
}

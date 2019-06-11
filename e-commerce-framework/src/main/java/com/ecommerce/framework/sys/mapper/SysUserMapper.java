package com.ecommerce.framework.sys.mapper;

import com.ecommerce.framework.base.mapper.BaseMapper;
import com.ecommerce.framework.sys.entity.SysUser;

/**
 * 用户表 数据层
 * 
 * @author huizhe yu
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    void deleteByIds(Long[] ids);

}

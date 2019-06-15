package com.ecommerce.framework.sys.service.impl;

import com.ecommerce.framework.base.service.impl.BaseServiceImpl;
import com.ecommerce.framework.sys.entity.SysOrg;
import com.ecommerce.framework.sys.mapper.SysOrgMapper;
import com.ecommerce.framework.sys.service.ISysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 机构 服务层实现
 * 
 * @author huizhe yu
 * @date 2019-06-15
 */
@Service
public class SysOrgServiceImpl extends BaseServiceImpl<SysOrg,SysOrgMapper> implements ISysOrgService {
	@Autowired
	private SysOrgMapper sysOrgMapper;

	/**
     * 查询机构列表
     * 
     * @param sysOrg 机构信息
     * @return 机构集合
     */
	@Override
	public List<SysOrg> selectSysOrgList(SysOrg sysOrg){
	    return sysOrgMapper.selectSysOrgList(sysOrg);
	}

	
}

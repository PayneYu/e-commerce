package com.ecommerce.framework.sys.service.impl;

import com.ecommerce.framework.base.service.impl.BaseServiceImpl;
import com.ecommerce.framework.sys.entity.SysOrg;
import com.ecommerce.framework.sys.mapper.SysOrgMapper;
import com.ecommerce.framework.sys.service.ISysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<Map<String, Object>> selectOrgTree(SysOrg sysOrg) {
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		List<SysOrg> deptList = sysOrgMapper.selectSysOrgList(sysOrg);
		trees = getTrees(deptList, false, null);
		return trees;
	}

    /**
     * 对象转机构树
     *
     */
   public List<Map<String, Object>> getTrees(List<SysOrg> list, boolean isCheck, List<String> roleOrgList) {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
	   list.forEach(org->{
		   Map<String, Object> orgMap = new HashMap<String, Object>();
		   orgMap.put("id", org.getId());
		   orgMap.put("pId", org.getParentId());
		   orgMap.put("name", org.getOrgName());
		   orgMap.put("title", org.getOrgName());
		   if (isCheck) {
			   orgMap.put("checked", roleOrgList.contains(org.getId() + org.getOrgName()));
		   } else {
			   orgMap.put("checked", false);
		   }
		   trees.add(orgMap);
	   });
        return trees;
    }


}

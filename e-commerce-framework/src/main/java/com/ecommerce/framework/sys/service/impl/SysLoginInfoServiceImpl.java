package com.ecommerce.framework.sys.service.impl;

import com.ecommerce.framework.base.service.impl.BaseServiceImpl;
import com.ecommerce.framework.sys.entity.SysLoginInfo;
import com.ecommerce.framework.sys.mapper.SysLoginInfoMapper;
import com.ecommerce.framework.sys.service.ISysLoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层处理
 * 
 * @author huizhe yu
 */
@Service
public class SysLoginInfoServiceImpl extends BaseServiceImpl<SysLoginInfo, SysLoginInfoMapper>
    implements ISysLoginInfoService {

    @Autowired
    private SysLoginInfoMapper loginInfoMapper;

    /**
     * 查询系统登录日志集合
     * 
     * @param loginInfo
     *            访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLoginInfo> selectLoginInfoList(SysLoginInfo loginInfo) {
        return loginInfoMapper.selectLoginInfoList(loginInfo);
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLoginInfo() {
        loginInfoMapper.cleanLoginInfo();
    }
}

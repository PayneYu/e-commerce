package com.ecommerce.web.system.service.impl;

import com.ecommerce.framework.base.service.impl.BaseServiceImpl;
import com.ecommerce.web.system.entity.SysNotice;
import com.ecommerce.web.system.mapper.SysNoticeMapper;
import com.ecommerce.web.system.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公告 服务层实现
 *
 * @author huizhe yu
 */
@Service
public class SysNoticeServiceImpl extends BaseServiceImpl<SysNotice, SysNoticeMapper> implements ISysNoticeService {
    @Autowired
    private SysNoticeMapper noticeMapper;

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    @Override
    public List<SysNotice> selectNoticeList(SysNotice notice) {
        return noticeMapper.selectNoticeList(notice);
    }

}

package com.ecommerce.web.system.service;

import com.ecommerce.framework.base.service.BaseService;
import com.ecommerce.web.system.entity.SysNotice;

import java.util.List;

/**
 * 公告 服务层
 *
 * @author ruoyi
 */
public interface ISysNoticeService extends BaseService<SysNotice> {

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    List<SysNotice> selectNoticeList(SysNotice notice);
}

package com.ecommerce.web.system.mapper;

import com.ecommerce.framework.base.mapper.BaseMapper;
import com.ecommerce.web.system.entity.SysNotice;

import java.util.List;

/**
 * 公告 数据层
 * 
 * @author huizhe yu
 */
public interface SysNoticeMapper extends BaseMapper<SysNotice> {

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    List<SysNotice> selectNoticeList(SysNotice notice);

}
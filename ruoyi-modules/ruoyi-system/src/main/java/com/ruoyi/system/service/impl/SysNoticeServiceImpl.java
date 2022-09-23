package com.ruoyi.system.service.impl;

import java.util.List;

import cn.easyes.core.conditions.LambdaEsIndexWrapper;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.es.mapper.EsSysNoticeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysNoticeMapper;
import com.ruoyi.system.service.ISysNoticeService;

import javax.annotation.PostConstruct;

/**
 * 公告 服务层实现
 * 
 * @author ruoyi
 */
@Slf4j
@Service
public class SysNoticeServiceImpl implements ISysNoticeService
{
    @Autowired
    private SysNoticeMapper noticeMapper;

    @Autowired
    private EsSysNoticeMapper esSysNoticeMapper;

    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public SysNotice selectNoticeById(Long noticeId)
    {
        return noticeMapper.selectNoticeById(noticeId);
    }

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    @Override
    public List<SysNotice> selectNoticeList(SysNotice notice)
    {
        return noticeMapper.selectNoticeList(notice);
    }

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int insertNotice(SysNotice notice)
    {
        return noticeMapper.insertNotice(notice);
    }

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int updateNotice(SysNotice notice)
    {
        return noticeMapper.updateNotice(notice);
    }

    /**
     * 删除公告对象
     * 
     * @param noticeId 公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeById(Long noticeId)
    {
        return noticeMapper.deleteNoticeById(noticeId);
    }

    /**
     * 批量删除公告信息
     * 
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(Long[] noticeIds)
    {
        return noticeMapper.deleteNoticeByIds(noticeIds);
    }

//    @PostConstruct
    public void run(){
//        new Thread(this::test).start();
        test();
    }

    public void test(){
        int id = 966900;
        int step = 300;
        while (true) {
            List<SysNotice> list = noticeMapper.queryList(id,id+step);
            if(list.isEmpty())
                break;
            esSysNoticeMapper.insertBatch(list);
            id = id + step;
            log.error("es process is going on : {}" , id);
        }
    }
}

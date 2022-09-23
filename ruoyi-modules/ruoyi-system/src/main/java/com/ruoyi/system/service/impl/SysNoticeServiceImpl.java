package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.easyes.core.biz.PageInfo;
import cn.easyes.core.conditions.LambdaEsIndexWrapper;
import cn.easyes.core.conditions.LambdaEsQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.domain.vo.SysNoticePageVo;
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

    @Override
    public TableDataInfo selectNoticePage(SysNoticePageVo pageVo) {
        LambdaEsQueryWrapper<SysNotice> queryWrapper = new LambdaEsQueryWrapper<>();
        queryWrapper.match(StringUtils.isNotEmpty(pageVo.getNoticeContent()),SysNotice::getNoticeContent,pageVo.getNoticeContent());
        queryWrapper.match(StringUtils.isNotEmpty(pageVo.getNoticeTitle()),SysNotice::getNoticeTitle,pageVo.getNoticeTitle());
        queryWrapper.match(StringUtils.isNotEmpty(pageVo.getNoticeType()),SysNotice::getNoticeType,pageVo.getNoticeType());
        queryWrapper.orderByAsc("_id");
        PageInfo<SysNotice> pageInfo = esSysNoticeMapper.pageQuery(queryWrapper,pageVo.getPageNum(), pageVo.getPageSize());
        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setRows(pageInfo.getList());
        tableDataInfo.setTotal(pageInfo.getTotal());
        return tableDataInfo;
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

    @PostConstruct
    public void run(){
//        new Thread(this::test).start();
        test();
    }

    public void test(){
        int id = 76000;
        int step = 9900;
        while (true) {
            List<SysNotice> list = getList(id,step);
            if(list.isEmpty())
                break;
            esSysNoticeMapper.insertBatch(list);
            id = id + step;
            log.info("es process is going on : {}" , id);
        }
    }

    private List<SysNotice> getList(int s,int end){
        List<Integer> list = new ArrayList<>();
        end = end + s;
        while (s <= end) {
            list.add(s);
            s++;
        }
        return esSysNoticeMapper.selectBatchIds(list);
    }


}

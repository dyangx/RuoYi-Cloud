package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.SysNotice;
import lombok.Data;

@Data
public class SysNoticePageVo extends SysNotice {

    private Integer pageNum;

    private Integer pageSize;
}

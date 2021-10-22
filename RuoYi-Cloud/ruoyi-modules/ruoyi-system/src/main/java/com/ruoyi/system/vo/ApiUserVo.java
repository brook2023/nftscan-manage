package com.ruoyi.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author even
 * @Describe
 * @Time 2021/10/21 14:17:05
 * 用户调用api使用情况
 */

@Data
@Builder
public class ApiUserVo {

    @Excel(name = "邮箱地址")
    private String email;

    @Excel(name = "总数")
    private Long count;

    @Excel(name = "调用大小")
    private String size;

    /** 24小时 统计json */
    @Excel(name = "24小时 统计json")
    private String apiUser24Count;

    /**
     * 调用方法次数
     */
    private List<MethodCount> methodCount;


    /**
     * 调用时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "调用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date apiCallTime;



    @Data
    public static class MethodCount {
        private String method;
        private Integer count;
    }
}

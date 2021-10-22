package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * apiCount对象 nft_api_user_day_count
 * 
 * @author ruoyi
 * @date 2021-10-20
 */
public class NftApiUserDayCount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户ID */
    private Long apiUserId;

    /** 调用总数 */
    @Excel(name = "调用总数")
    private Long apiUserCount;

    /** 调用方法次数 */
    @Excel(name = "调用方法次数")
    private String apiUserMethodCount;

    /** 24小时 统计json */
    @Excel(name = "24小时 统计json")
    private String apiUser24Count;

    /** 调用大小 */
    @Excel(name = "调用大小")
    private Long apiUserSize;

    /** 调用时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "调用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date apiCallTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setApiUserId(Long apiUserId) 
    {
        this.apiUserId = apiUserId;
    }

    public Long getApiUserId() 
    {
        return apiUserId;
    }
    public void setApiUserCount(Long apiUserCount) 
    {
        this.apiUserCount = apiUserCount;
    }

    public Long getApiUserCount() 
    {
        return apiUserCount;
    }
    public void setApiUserMethodCount(String apiUserMethodCount) 
    {
        this.apiUserMethodCount = apiUserMethodCount;
    }

    public String getApiUserMethodCount() 
    {
        return apiUserMethodCount;
    }
    public void setApiUser24Count(String apiUser24Count) 
    {
        this.apiUser24Count = apiUser24Count;
    }

    public String getApiUser24Count() 
    {
        return apiUser24Count;
    }
    public void setApiUserSize(Long apiUserSize) 
    {
        this.apiUserSize = apiUserSize;
    }

    public Long getApiUserSize() 
    {
        return apiUserSize;
    }
    public void setApiCallTime(Date apiCallTime) 
    {
        this.apiCallTime = apiCallTime;
    }

    public Date getApiCallTime() 
    {
        return apiCallTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("apiUserId", getApiUserId())
            .append("apiUserCount", getApiUserCount())
            .append("apiUserMethodCount", getApiUserMethodCount())
            .append("apiUser24Count", getApiUser24Count())
            .append("apiUserSize", getApiUserSize())
            .append("apiCallTime", getApiCallTime())
            .toString();
    }
}

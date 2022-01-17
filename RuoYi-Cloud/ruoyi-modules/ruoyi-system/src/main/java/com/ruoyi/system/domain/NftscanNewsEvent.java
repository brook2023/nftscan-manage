package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * news对象 nftscan_news_event
 * 
 * @author ruoyi
 * @date 2021-11-15
 */
public class NftscanNewsEvent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Integer id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 时间 */
    @Excel(name = "时间")
    private String time;

    /** $column.columnComment */
    @Excel(name = "时间")
    private String introduction;

    /** $column.columnComment */
    @Excel(name = "时间")
    private String link;

    /** $column.columnComment */
    @Excel(name = "时间")
    private String creator;

    /** $column.columnComment */
    @Excel(name = "时间")
    private String status;

    private String logo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "事件时间")
    private Date eventDate;

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setTime(String time)
    {
        this.time = time;
    }

    public String getTime()
    {
        return time;
    }
    public void setIntroduction(String introduction) 
    {
        this.introduction = introduction;
    }

    public String getIntroduction() 
    {
        return introduction;
    }
    public void setLink(String link) 
    {
        this.link = link;
    }

    public String getLink() 
    {
        return link;
    }
    public void setCreator(String creator) 
    {
        this.creator = creator;
    }

    public String getCreator() 
    {
        return creator;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("time", getTime())
            .append("introduction", getIntroduction())
            .append("link", getLink())
            .append("creator", getCreator())
            .append("status", getStatus())
            .toString();
    }
}

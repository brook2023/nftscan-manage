package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * event对象 nftscan_mint_event
 * 
 * @author ruoyi
 * @date 2021-11-13
 */
public class NftscanMintEvent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Integer id;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 铸造时间 */
    @Excel(name = "铸造时间")
    private String mintTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "事件时间")
    private Date eventDate;

    /** 铸造价格 */
    @Excel(name = "铸造价格")
    private String price;

    /** 数量 */
    @Excel(name = "数量")
    private Integer quantity;

    /** 官网 */
    @Excel(name = "官网")
    private String website;

    /** twitter */
    @Excel(name = "twitter")
    private String twitter;

    /** discord */
    @Excel(name = "discord")
    private String discord;

    /** twitter关注人数 */
    @Excel(name = "twitter关注人数")
    private Integer twitterFollowers;

    /** discord在线人数 */
    @Excel(name = "discord在线人数")
    private Integer discordOnline;

    /** discord总人数 */
    @Excel(name = "discord总人数")
    private Integer discordTotal;

    /** 创建者 */
    @Excel(name = "创建者")
    private String creator;

    private String status;

    private String logo;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setMintTime(String mintTime)
    {
        this.mintTime = mintTime;
    }

    public String getMintTime()
    {
        return mintTime;
    }
    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getPrice()
    {
        return price;
    }
    public void setQuantity(Integer quantity) 
    {
        this.quantity = quantity;
    }

    public Integer getQuantity() 
    {
        return quantity;
    }
    public void setWebsite(String website) 
    {
        this.website = website;
    }

    public String getWebsite() 
    {
        return website;
    }
    public void setTwitter(String twitter) 
    {
        this.twitter = twitter;
    }

    public String getTwitter() 
    {
        return twitter;
    }
    public void setDiscord(String discord) 
    {
        this.discord = discord;
    }

    public String getDiscord() 
    {
        return discord;
    }
    public void setTwitterFollowers(Integer twitterFollowers) 
    {
        this.twitterFollowers = twitterFollowers;
    }

    public Integer getTwitterFollowers() 
    {
        return twitterFollowers;
    }
    public void setDiscordOnline(Integer discordOnline) 
    {
        this.discordOnline = discordOnline;
    }

    public Integer getDiscordOnline() 
    {
        return discordOnline;
    }
    public void setDiscordTotal(Integer discordTotal) 
    {
        this.discordTotal = discordTotal;
    }

    public Integer getDiscordTotal() 
    {
        return discordTotal;
    }
    public void setCreator(String creator) 
    {
        this.creator = creator;
    }

    public String getCreator() 
    {
        return creator;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectName", getProjectName())
            .append("mintTime", getMintTime())
            .append("price", getPrice())
            .append("quantity", getQuantity())
            .append("website", getWebsite())
            .append("twitter", getTwitter())
            .append("discord", getDiscord())
            .append("twitterFollowers", getTwitterFollowers())
            .append("discordOnline", getDiscordOnline())
            .append("discordTotal", getDiscordTotal())
            .append("creator", getCreator())
            .toString();
    }
}

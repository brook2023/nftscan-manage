package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * tradeContract对象 nft_trade_contract
 * 
 * @author ruoyi
 * @date 2021-11-27
 */
public class NftTradeContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合约地址 */
    private String contract;

    /** 合约名称 */
    @Excel(name = "合约名称")
    private String name;

    /** 官网 */
    @Excel(name = "官网")
    private String website;

    /** twitter */
    @Excel(name = "twitter")
    private String twitter;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** discord */
    @Excel(name = "discord")
    private String discord;

    /** telegram */
    @Excel(name = "telegram")
    private String telegram;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** github */
    @Excel(name = "github")
    private String github;

    /** instagram */
    @Excel(name = "instagram")
    private String instagram;

    /** 标签 */
    @Excel(name = "标签")
    private String tag;

    /** 余额 */
    @Excel(name = "余额")
    private String balance;

    /** 部署时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "部署时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deploymentTime;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private String createContract;

    /** 创建hash */
    @Excel(name = "创建hash")
    private String createHash;

    public void setContract(String contract) 
    {
        this.contract = contract;
    }

    public String getContract() 
    {
        return contract;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
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
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setDiscord(String discord) 
    {
        this.discord = discord;
    }

    public String getDiscord() 
    {
        return discord;
    }
    public void setTelegram(String telegram) 
    {
        this.telegram = telegram;
    }

    public String getTelegram() 
    {
        return telegram;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setGithub(String github) 
    {
        this.github = github;
    }

    public String getGithub() 
    {
        return github;
    }
    public void setInstagram(String instagram) 
    {
        this.instagram = instagram;
    }

    public String getInstagram() 
    {
        return instagram;
    }
    public void setTag(String tag) 
    {
        this.tag = tag;
    }

    public String getTag() 
    {
        return tag;
    }
    public void setBalance(String balance) 
    {
        this.balance = balance;
    }

    public String getBalance() 
    {
        return balance;
    }
    public void setDeploymentTime(Date deploymentTime) 
    {
        this.deploymentTime = deploymentTime;
    }

    public Date getDeploymentTime() 
    {
        return deploymentTime;
    }
    public void setCreateContract(String createContract) 
    {
        this.createContract = createContract;
    }

    public String getCreateContract() 
    {
        return createContract;
    }
    public void setCreateHash(String createHash) 
    {
        this.createHash = createHash;
    }

    public String getCreateHash() 
    {
        return createHash;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contract", getContract())
            .append("name", getName())
            .append("website", getWebsite())
            .append("twitter", getTwitter())
            .append("email", getEmail())
            .append("discord", getDiscord())
            .append("telegram", getTelegram())
            .append("description", getDescription())
            .append("github", getGithub())
            .append("instagram", getInstagram())
            .append("tag", getTag())
            .append("balance", getBalance())
            .append("deploymentTime", getDeploymentTime())
            .append("createContract", getCreateContract())
            .append("createHash", getCreateHash())
            .toString();
    }
}

package com.ruoyi.system.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * NFT项目信息提交对象 user_submitted_contract
 * 
 * @author ruoyi
 * @date 2022-01-13
 */
@Data
public class UserSubmittedContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Integer id;

    /** 合约地址 */
    @Excel(name = "合约地址")
    private String contract;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** NFT项目logo */
    @Excel(name = "NFT项目logo")
    private String logo;

    /** NFT项目名称 */
    @Excel(name = "NFT项目名称")
    private String contractName;

    /** 项目分类 */
    @Excel(name = "项目分类")
    private String contractCategory;

    /** 项目描述 */
    @Excel(name = "项目描述")
    private String description;

    /** twitter */
    @Excel(name = "twitter")
    private String twitter;

    /** discord */
    @Excel(name = "discord")
    private String discord;

    /** website */
    @Excel(name = "website")
    private String website;

    /** telegram */
    @Excel(name = "telegram")
    private String telegram;

    /** medium */
    @Excel(name = "medium")
    private String medium;

    /** mirror */
    @Excel(name = "mirror")
    private String mirror;

    /** facebook */
    @Excel(name = "facebook")
    private String facebook;

    /** github */
    @Excel(name = "github")
    private String github;

    /** youtube */
    @Excel(name = "youtube")
    private String youtube;

    /** twitch */
    @Excel(name = "twitch")
    private String twitch;

    /** metadata_url */
    @Excel(name = "metadata_url")
    private String metadataUrl;

    /** statement */
    @Excel(name = "statement")
    private String statement;

    private String email;

    private String ins;

}

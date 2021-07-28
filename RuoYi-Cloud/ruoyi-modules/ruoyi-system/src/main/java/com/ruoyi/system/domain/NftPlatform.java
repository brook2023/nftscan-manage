package com.ruoyi.system.domain;

import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.ibatis.type.Alias;

/**
 * @author king
 * @description TODO
 * @date 2021/6/7
 **/
@Alias("nftPlatform")
public class NftPlatform extends BaseEntity {

    private Long id;
    private String platform;
    private String website;
    private String contractAddress;
    private String tags;
    private String twitter;
    private String email;
    private String discord;
    private String telegram;
    private String github;
    private String medium;
    private String descriptionEn;
    private String descriptionCn;
    private String type;
    private String isHot;
    private String recommendEn;
    private String recommendCn;

    private String addressType;

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getRecommendEn() {
        return recommendEn;
    }

    public void setRecommendEn(String recommendEn) {
        this.recommendEn = recommendEn;
    }

    public String getRecommendCn() {
        return recommendCn;
    }

    public void setRecommendCn(String recommendCn) {
        this.recommendCn = recommendCn;
    }

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiscord() {
        return discord;
    }

    public void setDiscord(String discord) {
        this.discord = discord;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionCn() {
        return descriptionCn;
    }

    public void setDescriptionCn(String descriptionCn) {
        this.descriptionCn = descriptionCn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @author king
 * @description TODO
 * @date 2021/6/4
 **/
@Alias("nftIndustryInfo")
public class NftIndustryInfo extends BaseEntity{
    // 主键
    private Long id;
    // 标题
    @Excel(name = "标题")
    private String title;
    // 内容
    @Excel(name = "内容")
    private String content;
    // 类型，1-快讯，0-文章
    @Excel(name = "类型")
    private String type;
    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    // 缩略图url
    private String logo;
    // 标签
    @Excel(name = "标签")
    private String tags;
    // 作者
    @Excel(name = "作者")
    private String author;

    @Excel(name = "内容来源")
    private String contentSource;
    @Excel(name = "链接")
    private String link;

    private String cover;

    public String getContentSource() {
        return contentSource;
    }

    public void setContentSource(String contentSource) {
        this.contentSource = contentSource;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "NftIndustryInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", createTime=" + createTime +
                ", logo='" + logo + '\'' +
                ", tags='" + tags + '\'' +
                ", author='" + author + '\'' +
                ", contentSource='" + contentSource + '\'' +
                ", link='" + link + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }
}

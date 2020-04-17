package com.springboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
public class Draftsarticles {

  @TableId(type = IdType.AUTO)
  private Integer id;
  private String title;
  @JsonFormat(pattern="yyyy-MM-dd")
  private Date creattime;
  private String markdown;
  private String tags;
  private String category;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public Date getCreattime() {
    return creattime;
  }

  public void setCreattime(Date creattime) {
    this.creattime = creattime;
  }


  public String getMarkdown() {
    return markdown;
  }

  public void setMarkdown(String markdown) {
    this.markdown = markdown;
  }


  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }


  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  @Override
  public String toString() {
    return "Draftsarticles{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", creattime=" + creattime +
            ", markdown='" + markdown + '\'' +
            ", tags='" + tags + '\'' +
            ", category='" + category + '\'' +
            '}';
  }
}

package com.springboot.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Articls  implements Serializable {

  @TableId(type = IdType.AUTO)
  private Integer id;
  private String title;
  private String html;

  @JsonFormat(pattern="yyyy-MM-dd")
  private Date time;
  private Integer browse;
  private String category;
  private String tags;
  private String markdown;
  private String recommended;
  private Integer likearticls;



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


  public String getHtml() {
    return html;
  }

  public void setHtml(String html) {
    this.html = html;
  }


  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }


  public Integer getBrowse() {
    return browse;
  }

  public void setBrowse(Integer browse) {
    this.browse = browse;
  }


  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }


  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }


  public String getMarkdown() {
    return markdown;
  }

  public void setMarkdown(String markdown) {
    this.markdown = markdown;
  }


  public String getRecommended() {
    return recommended;
  }

  public void setRecommended(String recommended) {
    this.recommended = recommended;
  }


  public Integer getLikearticls() {
    return likearticls;
  }

  public void setLikearticls(Integer likearticls) {
    this.likearticls = likearticls;
  }

  @Override
  public String toString() {
    return "Articls{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", html='" + html + '\'' +
            ", time=" + time +
            ", browse='" + browse + '\'' +
            ", category='" + category + '\'' +
            ", tags='" + tags + '\'' +
            ", markdown='" + markdown + '\'' +
            ", recommended='" + recommended + '\'' +
            ", likearticls='" + likearticls + '\'' +
            '}';
  }
}

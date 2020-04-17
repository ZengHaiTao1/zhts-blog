package com.springboot.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Notice {

  @TableId(type = IdType.AUTO)
  private Integer id;
  private String notice;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getNotice() {
    return notice;
  }

  public void setNotice(String notice) {
    this.notice = notice;
  }

}

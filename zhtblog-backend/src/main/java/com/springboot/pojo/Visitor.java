package com.springboot.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Visitor {

  @TableId(type = IdType.AUTO)
  private Integer id;
  private String ip;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private Date intotime;
  private String place;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private Date leavetime;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public Date getIntotime() {
    return intotime;
  }

  public void setIntotime(Date intotime) {
    this.intotime = intotime;
  }


  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }


  public Date getLeavetime() {
    return leavetime;
  }

  public void setLeavetime(Date leavetime) {
    this.leavetime = leavetime;
  }

}

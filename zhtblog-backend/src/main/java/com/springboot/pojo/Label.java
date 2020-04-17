package com.springboot.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class Label implements Serializable {

  @TableId(type = IdType.AUTO)
  private Integer id;
  private String name;
  private String display;
  private Integer num;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getDisplay() {
    return display;
  }

  public void setDisplay(String display) {
    this.display = display;
  }


  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

}

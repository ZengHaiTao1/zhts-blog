package com.springboot.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Categories {

  @TableId(type = IdType.AUTO)
  private Integer id;
  private String categories;
  private String display;
  private Integer num;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getCategories() {
    return categories;
  }

  public void setCategories(String categories) {
    this.categories = categories;
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

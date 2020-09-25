package com.chen.entiy;

/**
 * @Author ChenZW
 * @Version  1.0
 * @Description 菜单实体类
 * @Return
 * @Date 2020/9/22 17:27
 *@版权归属于ChenZW
 */
public class Menu {

  private Integer id;
  private String name;
  private String parentId;
  private String type;
  private String sort;
  private String url;


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


  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}

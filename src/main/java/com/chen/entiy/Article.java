package com.chen.entiy;


public class Article {

  private Integer id;
  private String title;
  private String content;
  private Integer browseCount;
  private String publishDate;
  private String publishRealName;
  private Integer userId;


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


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public Integer getBrowseCount() {
    return browseCount;
  }

  public void setBrowseCount(Integer browseCount) {
    this.browseCount = browseCount;
  }


  public String getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(String publishDate) {
    this.publishDate = publishDate;
  }


  public String getPublishRealName() {
    return publishRealName;
  }

  public void setPublishRealName(String publishRealName) {
    this.publishRealName = publishRealName;
  }


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

}

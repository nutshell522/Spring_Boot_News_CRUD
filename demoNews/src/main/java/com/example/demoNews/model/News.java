package com.example.demoNews.model;

import java.sql.Date;
import java.sql.Timestamp;

public class News {
	private Integer id;
    private String topic;
    private String content;
    private Integer categoryId;
    private String categoryName;
    private Timestamp publishDate;
    private String authorName;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Boolean status;
    private String shortContent; 
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Timestamp getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Timestamp publishDate) {
		this.publishDate = publishDate;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public Timestamp  getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	} 
	public String getShortContent() {
        if (content != null && content.length() > 10) {
            return content.substring(0, 10) + "...";
        }
        return content;
    }
    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }
}

package com.demo.assessment.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable = true)
	private Date modifiedOn;

	@Column(nullable = true)
	private Date createdOn;

	@Column(nullable = false)
	private int createdby;

	@Column(nullable = true)
	private int modifiedby;

	public BaseEntity() {
	}

	@Override
	public String toString() {
		return "Record Added User : " + this.createdby + " | Record Added Date : " + this.createdOn
				+ " | Record Changed Date : " + this.modifiedOn + " | Selected Flag : " + this.modifiedby;
	}

	@PreUpdate
	protected void onUpdate() {
		modifiedOn = new java.util.Date();
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public int getCreatedby() {
		return createdby;
	}

	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}

	public int getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(int modifiedby) {
		this.modifiedby = modifiedby;
	}

}

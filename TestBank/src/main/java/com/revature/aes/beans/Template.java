/****************************************************************
 * Project Name: Associate Evaluation System - Test Bank
 * 
 * Description: A simple rest application that persists test
 * 		information into a database. Use to evaluate associates
 * 		performance both during and before employment with Revature 
 * 		LLC.
 * 
 * Authors: Matthew Beauregard, Conner Anderson, Travis Deshotels,
 * 		Edward Crader, Jon-Erik Williams 
 ****************************************************************/

package com.revature.aes.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="AES_TEMPLATES")
public class Template implements Serializable
{

	/**
	 * @serialVersionUID An auto-generated field that is used for serialization.
	 */
	private static final long serialVersionUID = 1956111351245852280L;

	/**
	 * @templateId The unique Identifier for the Class
	 */
	@Id
	@SequenceGenerator(name = "AES_TEMPLATES_SEQ", sequenceName = "AES_TEMPLATES_SEQ")
	@GeneratedValue(generator = "AES_TEMPLATES_SEQ", strategy = GenerationType.SEQUENCE)
	@Column(name = "TEMPLATE_ID")
	private Integer templateId;
	
	/**
	 * @timeStamp A timestamp used to determine the time the Template was created.
	 */
	@Column(name="CREATE_TIMESTAMP")
	private Timestamp timeStamp;
	
	/**
	 * @creator The creator of the template.
	 */
	@ManyToOne
	@JoinColumn(name="CREATOR_ID")
	private User creator;

	@OneToMany(mappedBy="template")
	private List<Assessment> assessments;
	
	@OneToMany(mappedBy="template")
	private List<TemplateQuestion> templateQuestions = new ArrayList<>();
	
	public Template()
	{
		super();
	}

	public Template(Integer templateId, Timestamp createTimeStamp, User creator)
	{
		this();
		this.templateId = templateId;
		this.timeStamp = createTimeStamp;
		this.creator = creator;
	}

	public Integer getTemplateId()
	{
		return templateId;
	}

	public void setTemplateId(Integer templateId)
	{
		this.templateId = templateId;
	}

	public Timestamp getCreateTimeStamp()
	{
		return timeStamp;
	}

	public void setCreateTimeStamp(Timestamp createTimeStamp)
	{
		this.timeStamp = createTimeStamp;
	}

	public User getCreator()
	{
		return creator;
	}

	public void setCreator(User creator)
	{
		this.creator = creator;
	}

	
	
	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public List<Assessment> getAssessments() {
		return assessments;
	}

	public void setAssessments(List<Assessment> assessments) {
		this.assessments = assessments;
	}

	public List<TemplateQuestion> getTemplateQuestions() {
		return templateQuestions;
	}

	public void setTemplateQuestions(List<TemplateQuestion> templateQuestions) {
		this.templateQuestions = templateQuestions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assessments == null) ? 0 : assessments.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + ((templateId == null) ? 0 : templateId.hashCode());
		result = prime * result + ((templateQuestions == null) ? 0 : templateQuestions.hashCode());
		result = prime * result + ((timeStamp == null) ? 0 : timeStamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Template other = (Template) obj;
		if (assessments == null) {
			if (other.assessments != null)
				return false;
		} else if (!assessments.equals(other.assessments))
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (templateId == null) {
			if (other.templateId != null)
				return false;
		} else if (!templateId.equals(other.templateId))
			return false;
		if (templateQuestions == null) {
			if (other.templateQuestions != null)
				return false;
		} else if (!templateQuestions.equals(other.templateQuestions))
			return false;
		if (timeStamp == null) {
			if (other.timeStamp != null)
				return false;
		} else if (!timeStamp.equals(other.timeStamp))
			return false;
		return true;
	}


	
}

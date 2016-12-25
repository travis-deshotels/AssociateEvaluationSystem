package com.revature.aes.beans;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class TemplateQuestionId {
	/**
	 * @question The question associated with the class.
	 */
	@ManyToOne
	private Question question;
	
	/**
	 * @template The template associated with the class.
	 */
	@ManyToOne
	private Template template;

	public TemplateQuestionId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TemplateQuestionId(Question question, Template template) {
		super();
		this.question = question;
		this.template = template;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((template == null) ? 0 : template.hashCode());
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
		TemplateQuestionId other = (TemplateQuestionId) obj;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (template == null) {
			if (other.template != null)
				return false;
		} else if (!template.equals(other.template))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TemplateQuestionId [question=" + question + ", template=" + template + "]";
	}
	
}

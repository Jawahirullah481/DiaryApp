package com.diaryApp;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DiaryAppInbox {

	private int inboxId;
	private String heading;
	private String content;
	private LocalDate dateEdited;

	public DiaryAppInbox() {

	}

	public DiaryAppInbox(int inboxId, String heading, String content, LocalDate dateEdited) {

		this.inboxId = inboxId;
		this.heading = heading;
		this.content = content;
		this.dateEdited = dateEdited;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getDateEdited() {
		return dateEdited;
	}

	public void setDateEdited(Date sqlDate) {
		this.dateEdited = sqlDate.toLocalDate();
	}
	
	public void setDateEdited(LocalDate date)
	{
		this.dateEdited = date;
	}

	public int getInboxId() {
		return inboxId;
	}

	public void setInboxId(int inboxId) {
		this.inboxId = inboxId;
	}
	
	public String toString()
	{
		return this.inboxId + "";
	}

	public String getformattedDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return this.getDateEdited().format(formatter);
	}

}

package com.diaryApp;

import java.awt.Window;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Deque;
import java.util.Iterator;

import com.oracle.wls.shaded.org.apache.xml.utils.DOMHelper;

import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.TagSupport;

public class AddInboxRecord extends TagSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() {
		JspWriter out = pageContext.getOut();

		DiaryAppUser user = (DiaryAppUser) pageContext.getAttribute("user", PageContext.SESSION_SCOPE);
		Deque<DiaryAppInbox> inbox = user.getInbox();

		Iterator<DiaryAppInbox> iterator = inbox.descendingIterator();
		iterator.forEachRemaining((i) -> {
			try {

				out.println("<form action=\"editdiary\" method=\"POST\">");
				out.println("<div class=\"note\" onClick=\"javascript:this.parentNode.submit();\">");
				out.println("<h2 class=\"note-heading\">" + i.getHeading() + "</h2>");
				out.println("<p class=\"note-content\">" + i.getContent() + "</p>");
				out.println("<p class=\"last-edited\">Last edited : " + i.getformattedDate() + "</p>");
				out.println("</div>");
				out.println("<input type=\"hidden\" name=\"noteheading\" value=\"" + i.getHeading() + "\">");
				out.println("<input type=\"hidden\" name=\"notecontent\" value=\"" + i.getContent() + "\">");
				out.println("<input type=\"hidden\" name=\"inboxid\" value=\"" + i.getInboxId() + "\">");
				out.println("</form>");

			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error while writing add diaries tag");
			}

		});

		return SKIP_BODY;
		

	}

}

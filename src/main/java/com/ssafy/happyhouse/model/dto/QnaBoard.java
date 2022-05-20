package com.ssafy.happyhouse.model.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QnaBoard {
	private int no;
	private String user_id;
	private String title;
	private String content;
	private Timestamp registDate;
	private Timestamp updateDate;


	@Override
	public String toString() {
		return "QnaBoard [no=" + no + ", user_id=" + user_id + ", title=" + title + ", content=" + content
				+ ", registDate=" + registDate + ", updateDate=" + updateDate + "]";
	}	
}

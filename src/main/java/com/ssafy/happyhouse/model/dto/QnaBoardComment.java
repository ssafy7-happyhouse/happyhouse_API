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
public class QnaBoardComment {
	private int no;
	private String user_id;
	private String title;
	private String content;
	private Timestamp registDate;
	private Timestamp updateDate;
	private int board_no;
	private String comment_content;
	private Timestamp comment_registDate;
	private Timestamp comment_updateDate;
	
}

package com.ssafy.happyhouse.model.dto;

import java.sql.Timestamp;

public class Comment {
	private int board_no;
	private String content;
	private Timestamp registDate;
	private Timestamp updateDate;
	
	public Comment() {}

	public Comment(int board_no, String content) {
		super();
		this.board_no = board_no;
		this.content = content;
	}
	
	public Comment(String content) {
		super();
		this.content = content;
	}


	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Timestamp registDate) {
		this.registDate = registDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Comment [board_no=");
		builder.append(board_no);
		builder.append(", content=");
		builder.append(content);
		builder.append(", registDate=");
		builder.append(registDate);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append("]");
		return builder.toString();
	};
	
	
	
	
}

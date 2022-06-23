package org.kosta.myproject.vo;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
@Data
public class ReportingBoardVO implements Serializable{
	private static final long serialVersionUID = 6440047762418162093L;
	private int reportBoardNo;
	private String reportBoardTitle;
	private Date reportBoardDate;
	private String reportBoardContent;
	private String reportedId;
	private MemberVO memberVO;
}

package org.kosta.myproject.vo;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
@Data
public class AdminBoardVO implements Serializable{
	private static final long serialVersionUID = 6440047762418162093L;
	private int adminBoardNo;
	private String adminBoardTitle;
	private Date adminBoardDate;
	private String adminBoardContent;
	private String adminBoardKind;
	private MemberVO memberVO;
}

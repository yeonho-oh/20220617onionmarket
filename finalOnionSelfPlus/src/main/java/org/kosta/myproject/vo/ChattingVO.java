package org.kosta.myproject.vo;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
@Data
public class ChattingVO implements Serializable{
	private static final long serialVersionUID = 6440047762418162093L;
	private int chattingNo;
	private ChattingRoomVO chattingRoomVO;
	private Date chattingDate;
	private int reception;
	private String chatting;
	private MemberVO memberVO;
}

package org.kosta.myproject.vo;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
@Data
public class TradingBoardVO implements Serializable{
	private static final long serialVersionUID = 6440047762418162093L;
	private int boardNo;
	private String boardTitle;
	private Date boardDate;
	private String boardContent;
	private String boardKind;
	private int tradeStatus;
	private int tradePrice;
	private String productPicture;
	private int hits;
	private MemberVO memberVO;
}

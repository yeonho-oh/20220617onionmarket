package org.kosta.myproject.vo;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
@Data
public class ChattingRoomVO implements Serializable{
	private static final long serialVersionUID = 6440047762418162093L;
	private int chattingRoomNo;
	private String chattingRoomTitle;
	private Date chattingRoomDate;
}

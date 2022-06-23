package org.kosta.myproject.vo;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
@Data
public class BoardTagVO implements Serializable{
	private static final long serialVersionUID = 6440047762418162093L;
	private TradingBoardVO tradingBoardVO;
	private TagVO tagVO;
	private Date tagDate;
}

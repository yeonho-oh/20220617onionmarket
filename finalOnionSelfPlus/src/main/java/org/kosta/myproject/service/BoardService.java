package org.kosta.myproject.service;

import java.util.ArrayList;

import org.kosta.myproject.vo.Pagination;
import org.kosta.myproject.vo.TradingBoardVO;

public interface BoardService {

	int getTotalPostCount();

	ArrayList<TradingBoardVO> orderByTemp(Pagination pagination);

	ArrayList<TradingBoardVO> orderByDate(Pagination pagination);

	ArrayList<TradingBoardVO> orderByPrice(Pagination pagination);

}

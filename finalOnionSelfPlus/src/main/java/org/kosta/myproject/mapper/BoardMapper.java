package org.kosta.myproject.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.myproject.vo.Pagination;
import org.kosta.myproject.vo.TradingBoardVO;

@Mapper
public interface BoardMapper {

	int getTotalPostCount();

	ArrayList<TradingBoardVO> orderByTemp(Pagination pagination);
	
	ArrayList<TradingBoardVO> orderByDate(Pagination pagination);

	ArrayList<TradingBoardVO> orderByPrice(Pagination pagination);

}

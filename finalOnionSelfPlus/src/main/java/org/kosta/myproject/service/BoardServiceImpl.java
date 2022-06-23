package org.kosta.myproject.service;

import java.util.ArrayList;

import org.kosta.myproject.mapper.BoardMapper;
import org.kosta.myproject.vo.Pagination;
import org.kosta.myproject.vo.TradingBoardVO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	private final BoardMapper boardmapper;

	@Override
	public int getTotalPostCount() {
		int result = boardmapper.getTotalPostCount();
		return result;
	}

	@Override
	public ArrayList<TradingBoardVO> orderByTemp(Pagination pagination) {
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		list = boardmapper.orderByTemp(pagination);
		return list;
	}

	@Override
	public ArrayList<TradingBoardVO> orderByDate(Pagination pagination) {
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		list = boardmapper.orderByDate(pagination);
		return list;
	}

	@Override
	public ArrayList<TradingBoardVO> orderByPrice(Pagination pagination) {
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		list = boardmapper.orderByPrice(pagination);
		return list;
	}
}

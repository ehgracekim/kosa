package com.coderby.myapp.board.service;

import java.util.List;

import com.coderby.myapp.board.model.BoardVO;

public interface IBoardService {
	List<BoardVO> selectList(int page);
	BoardVO selectBoardDetails(int seq);
	int insertNewBoard(BoardVO board);
	int updateBoard(BoardVO board);
	int deleteBoard(int seq);
}

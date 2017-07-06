package com.coderby.myapp.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coderby.myapp.board.dao.IBoardRepository;
import com.coderby.myapp.board.model.BoardVO;

@Service
public class BoardService implements IBoardService {

	@Inject
	IBoardRepository boardRepository;
	
	@Override
	public List<BoardVO> selectList(int page) {
		return boardRepository.selectList(page);
	}

	@Override
	public BoardVO selectBoardDetails(int seq) {
		return boardRepository.selectBoardDetails(seq);
	}

	@Transactional(value="txManager")//클래스단위로도 넣을 수 있음 
	public int insertNewBoard(BoardVO board) {
		System.out.println("biz()실행 전");
		int seq = boardRepository.getMaxSeq() + 1;
		board.setSeq(seq);
		int rowCount = boardRepository.insertNewBoard(board);
		System.out.println("biz() 실행 후");
	
		if(Math.random() <1) {
			throw new RuntimeException("강제예외 발생시킴");
		}
		return rowCount;
	}//트랜잭션은 여러개의 sql구문이 원자적으로 동작하게끔 하는것 
	//실행 되려면 다 되고 안되면 다 안되는

	@Override
	public int updateBoard(BoardVO board) {
		return boardRepository.updateBoard(board);
	}

	@Override
	public int deleteBoard(int seq) {
		return boardRepository.deleteBoard(seq);
	}

}
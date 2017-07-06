package com.coderby.myapp.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.coderby.myapp.board.model.BoardVO;
import com.coderby.myapp.board.service.IBoardService;

public class BoardMain {

	public static void main(String[] args) {
		AbstractApplicationContext context =
				new ClassPathXmlApplicationContext("application-config.xml");
		IBoardService service = context.getBean("boardService", IBoardService.class);
		try {
			List<BoardVO> boardList = service.selectList(1);
			for(BoardVO board : boardList) {
				System.out.println(board.toString());
			}
			BoardVO board = service.selectBoardDetails(3);
			System.out.println(board);
			
			board.setTitle("22");
			BoardVO b = new BoardVO();
			b.setTitle("새 글");
			b.setContent("새 내용");
			service.insertNewBoard(b);
			
			board = service.selectBoardDetails(5);
			board.setTitle("new Title");
			service.updateBoard(board);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

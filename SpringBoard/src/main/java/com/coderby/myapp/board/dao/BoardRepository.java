package com.coderby.myapp.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.coderby.myapp.board.model.BoardVO;

@Repository
public class BoardRepository implements IBoardRepository {

	@Inject
	JdbcTemplate jdbcTemplate;
	
	private class BoardMapper implements RowMapper<BoardVO> {
		@Override
		public BoardVO mapRow(ResultSet rs, int rowCount) throws SQLException {
			BoardVO board = new BoardVO();
			board.setSeq(rs.getInt("seq"));
			board.setTitle(rs.getString("title"));
			board.setWriter(rs.getString("writer"));
			board.setContent(rs.getString("content"));
			board.setRegdate(rs.getDate("regdate"));
			board.setCnt(rs.getInt("cnt"));
			return board;
		}
	}
	
	@Override
	public List<BoardVO> selectList(int page) {
		String sql =  "SELECT SEQ, TITLE, WRITER, REGDATE, CNT "
					+ "FROM ( "
					+ "		SELECT SEQ, TITLE, WRITER, REGDATE, CNT, ROWNUM AS RNUM " 
					+ "		FROM ( "
					+ "    		SELECT SEQ, TITLE, WRITER, REGDATE, CNT FROM BOARD "
					+ "    		ORDER BY SEQ DESC"
					+ "		) "
					+ ") "
					+ "WHERE RNUM BETWEEN ? AND ?";
		int start = (page-1) * 10 + 1;
		return jdbcTemplate.query(sql, new RowMapper<BoardVO>() {
			@Override
			public BoardVO mapRow(ResultSet rs, int rowCount) throws SQLException {
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setRegdate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("cnt"));
				return board;
			}
		}, start, start+9);
	}

	@Override
	public BoardVO selectBoardDetails(int seq) {
		String sql = "SELECT SEQ, TITLE, WRITER, REGDATE, CONTENT, CNT FROM BOARD WHERE SEQ=?";
		return jdbcTemplate.queryForObject(sql, new BoardMapper(), seq);
	}

	@Override
	public int insertNewBoard(BoardVO board) {
		String sql = "INSERT INTO BOARD (SEQ, TITLE, WRITER, CONTENT) "
					+"VALUES (?, ?, ?, ?)";
		return jdbcTemplate.update(sql, board.getSeq(), board.getTitle(), board.getWriter(), board.getContent());
	}

	@Override
	public int updateBoard(BoardVO board) {
		String sql = "UPDATE BOARD SET TITLE=?, WRITER=?, CONTENT=? WHERE SEQ=?";
		return jdbcTemplate.update(sql, board.getTitle(), board.getWriter(), board.getContent(), board.getSeq());
	}

	@Override
	public int deleteBoard(int seq) {
		String sql = "DELETE BOARD WHERE SEQ=?";
		return jdbcTemplate.update(sql, seq);
	}

	@Override
	public int getMaxSeq() {
		String sql = "SELECT MAX(SEQ) FROM BOARD";
		return jdbcTemplate.queryForInt(sql);
	}


}

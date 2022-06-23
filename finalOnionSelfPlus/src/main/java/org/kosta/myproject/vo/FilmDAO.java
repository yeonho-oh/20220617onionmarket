/*
package org.kosta.myproject.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class FilmDAO {
	private static FilmDAO instance = new FilmDAO();
	private DataSource dataSource;
	private FilmDAO() {
		this.dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static FilmDAO getInstance() {
		return instance;
	}
	public void closeAll(PreparedStatement pstmt,Connection con) throws SQLException{
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close(); // 컨넥션을 컨넥션풀에 반납한다 
	}
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection con) throws SQLException{
		if(rs!=null)
			rs.close();
		closeAll(pstmt, con);
	}
	public ArrayList<ReviewVO> findPostList(Pagination pagination) throws SQLException {
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select avgStar, filmNo, filmName, rnum, openDate ");
			sql.append("from( ");
			sql.append("select ROW_NUMBER() OVER(ORDER BY avgStar DESC) as rnum, avgStar, filmNo, filmName, openDate ");
			sql.append("	from ( ");
			sql.append("select nvl(ROUND(AVG(star),1),0) as avgStar, filmNo, filmName, openDate ");
			sql.append("from( ");
			sql.append("select r.star, f.filmNo, f.filmName, f.openDate ");
			sql.append("from review r right outer join film f on r.movieNo=f.filmNo) ");
			sql.append("group by filmNo, filmName, openDate)) ");
			sql.append("where rnum between ? and ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, pagination.getStartRowNumber());
			pstmt.setInt(2, pagination.getEndRowNumber());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReviewVO vo = new ReviewVO(rs.getFloat(1),null,new FilmVO(rs.getInt(2),rs.getString(3),null),null);
				//vo = new PostVO(rs.getInt(1),rs.getString(2),null,rs.getInt(5),rs.getString(4),new MemberVO(null,null,rs.getString(3)));
				list.add(vo);
			}
		}finally{
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	
	public FilmVO findPostByNo(String no) throws SQLException {
		FilmVO vo = new FilmVO();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT filmNO,filmName,content,openDate,ageLimit,director,produceYear FROM film WHERE filmNO =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {				
				vo.setFilmNO(rs.getInt(1));
				vo.setFilmName(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setOpenDate(rs.getDate(4));
				vo.setAgeLimit(rs.getInt(5));
				vo.setDirector(rs.getString(6));
				vo.setProduceYear(rs.getDate(7));
			}
		} finally{
			closeAll(rs, pstmt, con);
		}
		return vo;
	}
	public int getTotalPostCount() throws SQLException {
		int totalPostCount = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String sql = "select count(*) from film";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalPostCount=rs.getInt(1);
			}
		} finally{
			closeAll(rs, pstmt, con);
		}
		return totalPostCount;
	}
	public ArrayList<ReviewVO> orderByFilmName(Pagination pagination) throws SQLException {
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select avgStar, filmNo, filmName, rnum, openDate ");
			sql.append("from( ");
			sql.append("select ROW_NUMBER() OVER(ORDER BY filmName) as rnum, avgStar, filmNo, filmName, openDate ");
			sql.append("	from ( ");
			sql.append("select nvl(ROUND(AVG(star),1),0) as avgStar, filmNo, filmName, openDate ");
			sql.append("from( ");
			sql.append("select r.star, f.filmNo, f.filmName, f.openDate ");
			sql.append("from review r right outer join film f on r.movieNo=f.filmNo) ");
			sql.append("group by filmNo, filmName, openDate)) ");
			sql.append("where rnum between ? and ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, pagination.getStartRowNumber());
			pstmt.setInt(2, pagination.getEndRowNumber());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FilmVO fvo = new FilmVO();
				fvo.setFilmNO(rs.getInt("filmNo"));
				fvo.setFilmName(rs.getString("filmName"));
				ReviewVO vo = new ReviewVO(rs.getFloat(1),null, fvo,null);
				//vo = new PostVO(rs.getInt(1),rs.getString(2),null,rs.getInt(5),rs.getString(4),new MemberVO(null,null,rs.getString(3)));
				list.add(vo);
			}
		} finally{
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	public ArrayList<ReviewVO> orderByOpenDate(Pagination pagination) throws SQLException, ParseException {
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select avgStar, filmNo, filmName, rnum, openDate ");
			sql.append("from( ");
			sql.append("select ROW_NUMBER() OVER(ORDER BY openDate DESC) as rnum, avgStar, filmNo, filmName, openDate ");
			sql.append("	from ( ");
			sql.append("select nvl(ROUND(AVG(star),1),0) as avgStar, filmNo, filmName, openDate ");
			sql.append("from( ");
			sql.append("select r.star, f.filmNo, f.filmName, f.openDate ");
			sql.append("from review r right outer join film f on r.movieNo=f.filmNo) ");
			sql.append("group by filmNo, filmName, openDate)) ");
			sql.append("where rnum between ? and ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, pagination.getStartRowNumber());
			pstmt.setInt(2, pagination.getEndRowNumber());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FilmVO fvo = new FilmVO();
				fvo.setFilmNO(rs.getInt("filmNo"));
				fvo.setFilmName(rs.getString("filmName"));
				ReviewVO vo = new ReviewVO(rs.getFloat(1),null, fvo,null);
				//vo = new PostVO(rs.getInt(1),rs.getString(2),null,rs.getInt(5),rs.getString(4),new MemberVO(null,null,rs.getString(3)));
				list.add(vo);
			}
		} finally{
			closeAll(rs, pstmt, con);
		}
		return list;
	}
}
*/
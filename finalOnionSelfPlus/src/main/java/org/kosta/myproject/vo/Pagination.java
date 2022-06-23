package org.kosta.myproject.vo;

import lombok.Data;

/**
 * 게시판 페이징 처리 로직을 정의한 객체
 */
@Data
public class Pagination {
	private int nowPage=1; //현재 페이지
	private int postCountPerPage = 3;//페이지 당 게시물 수
	private int pageCountPerPageGroup=1;//페이지 그룹 당 페이지수
	private int totalPostCount;//총 게시물 수 (DB에 저장되어있는)
	private int startRowNumber;
	private int endRowNumber;
	
	public Pagination(int totalPostCount) {
		this.totalPostCount=totalPostCount;
	}
	public Pagination(int totalPostCount,int nowPage) {
		this.totalPostCount=totalPostCount;
		this.nowPage=nowPage;
	}
	public int getNowPage() {
		return nowPage;
	}
	/**
	 * 현페이지에 해당하는 게시물 리스트의 시작 row number를 반환.
	 *	현 페이지 -1에 각 페이지의 게시글 수 곱한 뒤 +1
	 * 참고 : 사용자가 페이지 번호를 클릭하면 ListController에서 페이지번호를 전달받고 BoardDAO 로부터 총 게시물을 반환받은 뒤
	 * Pagination 객체를 생성해서 findPostList(Pagination)에 전달하여 현 페이지에 맞는 게시물 리스트를 반환받을 떄 사용하기 위한 메서드		
	 */
	public int getStartRowNumber() {
		return this.postCountPerPage*(this.nowPage-1)+1;
	}
	/**
	 * 현재 페이지 번호에 해당하는 게시물리스트 게시물 row의 마지막 번호를 반환.
	 * nowpage * totalPostCount(총게시글수)보다 클 경우엔 totalPostCount가 현 페이지의 마지막
	 */
	public int getEndRowNumber() {
		int endpage = this.nowPage*this.postCountPerPage;
		if(endpage<this.totalPostCount&&endpage>this.getStartRowNumber()) {
			return endpage;
		}else {
			return this.totalPostCount;
		}
	}
	/**
	 * 총 페이지수를 반환. totalPostCount / postCountPerPage 값의 나머지가 0이면 나눈값이 총 페이지수
	 * 총 페이지수를 반환. totalPostCount / postCountPerPage 값의 나머지가 0이 아니면 나눈값+1 이 총 페이지수	  
	 * ex) 게시물수가 	1,2,3,4,5,	6,7,8,9,10,11.12
	 * 							1				2				3page
	 */
	public int getTotalPage() {
		int totalPage=totalPostCount / postCountPerPage;
		if(totalPostCount % postCountPerPage!=0) {
			totalPage+=1;
		}
		return totalPage;
	}
	/** ex) 총 페이지 수 48개 -> 총 페이지 수 getTotalPage()/pageCountPerPageGroup 페이지그룹수 -> 나머지0이면 나눈값, 아니라면 나눈값+1이 페이지 수
	 * 페이지 			1,2,3,4,	5,6,7,8,9,10
	 * 페이지 그룹	1			2			3
	 */
	public int getTotalPageGroup() {
		int totalPageGroup=this.getTotalPage()/pageCountPerPageGroup;
		if(this.getTotalPage() % pageCountPerPageGroup!=0) {
			totalPageGroup+=1;
		}
		return totalPageGroup;
	}
	/**
	 * 현재 페이지가 속한 페이지 그룹이 몇번째 그룹인지를 리턴 nowPage/pageCountPerPageGroup 값의 나머지따라 결정
	 */
	public int getNowPageGroup() {
		int nowPageGroup = nowPage/pageCountPerPageGroup;
		if(nowPage % pageCountPerPageGroup != 0){
			nowPageGroup+=1;
		}
		return nowPageGroup;
	}
	/** 현재 페이지가 속한 그룹의 시작페이지 번호를 반환
	 *  (getNowPageGroup()-1)*pageCountPerPageGroup+1
	 */
	public int getStartPageOfPageGroup() {
		return (getNowPageGroup()-1)*pageCountPerPageGroup+1;
	}
	public int getEndPageOfPageGroup() {
		int endPage = getNowPageGroup()*pageCountPerPageGroup;
		if(endPage>getTotalPage()) {
			endPage=getTotalPage();
		}
		return endPage;
	}
	/**
	 * 이전 페이지 그룹이 존재하는지 여부를 리턴
	 * getNowPageGroup() 이 1보다 크면 이전 페이지 그룹이 존재
	 */
	public boolean isPreviousPageGroup() {
		boolean flag=false;
		if(getNowPageGroup()>1) {
			flag=true;
		}
		return flag;
	}
	public boolean isNextPageGroup() {
		boolean flag=false;
		if(getNowPageGroup()<getTotalPageGroup()) {
			flag=true;
		}
		return flag;
	}
}















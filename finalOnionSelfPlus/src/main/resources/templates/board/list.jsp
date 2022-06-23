<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.table,th,tr{
  margin-left:auto; 
  margin-right:auto;
  width: auto;
  height: auto;
}
.broad{
         background-color:	#FF8C00;
               width: 400px;
               padding: 20px 50px;
               margin: 40px;
          border-radius: 60px 20px 60px 20px;
          font-size: xx-large;
          font-style:italic;
          font-weight:bold;
          color:white;
          border: 7px solid red;
  }
.ribbon {
	position:relative;
	width: 400px;
	height: 60px;
	margin: 100px auto 0;
	color: #fff;
	font: 28px/60px sans-serif;
	font-style:italic;
    font-weight:bold;
	text-align: center;
	cursor: pointer;
	text-transform: uppercase;
	background: #f0b537;
	-webkit-animation: main .25s;
	-moz-animation: main .25s;
	animation: main .25s;
}
.ribbon i {
	position: absolute;
}
.ribbon i:first-child, .ribbon i:nth-child(2) {
	left: -20px;
	bottom: -20px;
	z-index: -1;
	border: 20px solid transparent;
	border-right-color: #403804;
	-webkit-animation: edge .5s;
	-moz-animation: edge .5s;
	animation: edge .5s;
}
.ribbon i:nth-child(2) {
	left:auto;
	right:-20px;
	border-left-color: #403804;
	border-right-color:transparent;
}
.ribbon i:nth-child(3), .ribbon i:last-child {
	z-index: -2;
	width: 50px;
	bottom: -20px;
	left: -40px;
	border: 30px solid #c0971d;
	border-left-color: transparent;
	
	-webkit-animation: backRibbon .6ms;
	-moz-animation: backRibbon .6s;
	animation: backRibbon .6s;
	/* 좌측 리본 : 좌측에서 우측으로 scale 이 커지도록 한다. */
	-webkit-transform-origin: 100% 0;
	-moz-transform-origin: 100% 0;
	-ms-transform-origin: 100% 0;
	transform-origin: 100% 0;
}
.ribbon i:last-child {
	left: auto;
	right: -40px;
	border-right-color: transparent;
	border-left-color: #c0971d;
	/* 우측 리본 : 우측에서 좌측으로 scale 이 커지도록 한다. */
	-webkit-transform-origin: 0 0;
	-moz-transform-origin: 0 0;
	-ms-transform-origin: 0 0;
	transform-origin: 0 0;
}

/* animations */
/* webkit */
@-webkit-keyframes main {
	0% { -webkit-transform: scaleX(0); }
	100% { -webkit-transform: scaleX(1); }
}

@-webkit-keyframes edge {
	0%, 50% { -webkit-transform: scaleY(0); }
	100% { -webkit-transform: scaleY(1); }
}

@-webkit-keyframes backRibbon {
	0%, 75% { -webkit-transform: scaleX(0); }
	100% { -webkit-transform: scaleX(1); }
}

/* moz */
@-moz-keyframes main {
	0% { -moz-transform: scaleX(0); }
	100% { -moz-transform: scaleX(1); }
}

@-moz-keyframes edge {
	0%, 50% { -moz-transform: scaleY(0); }
	100% { -moz-transform: scaleY(1); }
}

@-moz-keyframes backRibbon {
	0%, 75% { -moz-transform: scaleX(0); }
	100% { -moz-transform: scaleX(1); }
}

/* standard */
@keyframes main {
	0% { transform: scaleX(0); }
	100% { transform: scaleX(1); }
}

@keyframes edge {
	0%, 50% { transform: scaleY(0); }
	100% { transform: scaleY(1); }
}

@keyframes backRibbon {
	0%, 75% { transform: scaleX(0); }
	100% { transform: scaleX(1); }
}
}
</style>
<br>
<br>
<br>
<br>
<center>
	<div class="ribbon">
	현재상영작
	<i></i>
	<i></i>
	<i></i>
	<i></i>
	</div>
</center>
<br>
<br>


<div align="right">
<select name="sort" id="sort"> 
<option value="no">--정렬 방식을 선택해 주세요.--</option> 
<option value="avgStar">평점순</option> 
<option value="filmName">제목순</option> 
<option value="openDate">신작순</option> 
</select>
<button type="submit" onclick="return sort()" class="btn btn-primary btn-outline-dark" >정렬하기</button>
</div>
<form id="sorting" action="OrderByListController.do" method="post">
	<input type="hidden" name="sort1" value="">
	<input type="hidden" name="sorton" value="on">
</form>
<script>
	function sort(){
		var sort = $("#sort").val();
		if(sort=="no"){
			alert("정렬 방식을 선택해 주세요.");
			return false;
		}
		$('input[name=sort1]').attr('value',sort);
    document.getElementById("sorting").submit();
}
</script>
<table class="table table-bordered table-hover boardlist">
	<tbody>
	
	<tr style="background-color: #333333">
		<c:forEach items="${list}" var="fvo">
			
				<td class="card-body">
				<div class="card shadow-sm">
				 <img src="images/${fvo.filmVO.filmNO}.gif" width=300px height=auto>
				 
					<div class="d-flex justify-content-between align-items-center">

						<c:choose>
							<c:when test="${sessionScope.mvo==null}">
							</c:when>
							<c:otherwise>
								<a type="button" class="btn btn-sm btn-outline-dark"
									href="PostDetailController.do?filmNO=${fvo.filmVO.filmNO}"
									class="card-text">상세페이지로</a>
							</c:otherwise>
						</c:choose>
					</div> <small class="text-muted">☆:${fvo.star}</small>
					</div>

				</td>
		
		</c:forEach>
		</tr>
	</tbody>
</table>
<%--
pagination 처리 
${pagination.startPageOfPageGroup}
${pagination.endPageOfPageGroup}
--%>
<ul class="pagination justify-content-center" style="margin: 20px 0">
	<c:if test="${pagination.previousPageGroup }">
		<li class="page-item"><a class="page-link"
			href="OrderByListController.do?pageNo=${pagination.startPageOfPageGroup-1}">Previous</a></li>
	</c:if>
	<c:forEach begin="${pagination.startPageOfPageGroup}"
		end="${pagination.endPageOfPageGroup}" var="page">
		<c:choose>
			<c:when test="${page==pagination.nowPage }">
				<li class="page-item active"><a class="page-link" href="OrderByListController.do?pageNo=${page}">${page}</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link"	href="OrderByListController.do?pageNo=${page}">${page}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${pagination.nextPageGroup }">
		<li class="page-item"><a class="page-link"
			href="OrderByListController.do?pageNo=${pagination.endPageOfPageGroup+1}">Next</a></li>
	</c:if>
</ul>
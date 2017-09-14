<%@page import="constant.Defines"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<div class="clearfix slider">
	<ul class="pgwSlider">
		<c:forEach items="${slidefirst}" var="objslidef">
			<li><img
				src="${pageContext.request.contextPath}/files/${objslidef.picture}"
				alt="${objslidef.lname}" data-description="${objslidef.lname}"
				data-large-src="${pageContext.request.contextPath}/files/${objslidef.picture}" /></li>
		</c:forEach>
		<c:forEach items="${slidesecond}" var="objslide">
			<li><img
				src="${pageContext.request.contextPath}/files/${objslide.picture}"
				alt="${objslide.lname}"
				data-large-src="${pageContext.request.contextPath}/files/${objslide.picture}"
				data-description="${stringUtil.nameClandSmall(objslide.lname)}" /></li>
		</c:forEach>
	</ul>
</div>
<div class="clearfix content">
	<div class="content_title">
		<h2>Bài viết xem nhiều nhất</h2>
	</div>
	<c:forEach items="${listLands}" var="objLands">
		<fmt:formatDate value="${objLands.date_create}" var="date"
			pattern="dd" />
		<fmt:formatDate value="${objLands.date_create}" var="month"
			pattern="MM" />
		<fmt:formatDate value="${objLands.date_create}" var="year"
			pattern="dd-MM-yyyy" />
		<div class="clearfix single_content">
			<div class="clearfix post_date floatleft">
				<div class="date">
					<h3>${date}</h3>
					<p>Tháng ${month}</p>
				</div>
			</div>
			<div class="clearfix post_detail">
				<h2 style="font-size: 24px; font-weight: 500;">
					<a href="${pageContext.request.contextPath}/chi-tiet/${slugUtil.makeSlug(objLands.lname)}-${objLands.lid}.html">${objLands.lname}</a>
				</h2>
				<div style="    margin-bottom: 13px;">
					<img alt="sao.gif" src="${pageContext.request.contextPath}/templates/public/images/thumbs/1sao.gif">
					<img alt="sao.gif" src="${pageContext.request.contextPath}/templates/public/images/thumbs/1sao.gif">
					<img alt="sao.gif" src="${pageContext.request.contextPath}/templates/public/images/thumbs/1sao.gif">
				</div>
				<div class="clearfix post-meta">
					<p style="margin-bottom: -5px;">
						<span><i class="fa fa-location-arrow" aria-hidden="true"></i>
							Địa chỉ: ${objLands.address}</span>
					</p>
					<br />
					<div class="row">
						<p>
							<span><i class="fa fa-folder"></i> Diện tích:
								${objLands.area} m<sup>2</sup></span> <span><i class="fa fa-folder"></i>
								Giá: ${objLands.gia}</span> <span><i class="fa fa-folder"></i>
								Lượt xem: ${objLands.count_views}</span> <span><i
								class="fa fa-heart" style="color: red;"></i> ${objLands.care}</span>
						</p>
					</div>
				</div>
				<div class="clearfix post_excerpt">
					<img id="myImg"
						src="${pageContext.request.contextPath}/files/${objLands.picture}"
						alt="Trolltunga, Norway" width="300" height="200">
					<p>${stringUtil.stringDrop(objLands.description)}</p>
				</div>
				<div style="padding-top: 25px;">
					<div style="float: left; color: #b4b4bd;">Đăng ngày: ${year}</div>
					<div style="float: right;" id="more">
						<a
							href="${pageContext.request.contextPath}/chi-tiet/${slugUtil.makeSlug(objLands.lname)}-${objLands.lid}.html">Đọc
							thêm</a>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
<div class="pagination">
	<nav>
		<ul style="margin-left: 235px;">
			<c:if test="${currentPage > 1}">
				<li><a
					href="${pageContext.request.contextPath}/${currentPage - 1}"
					aria-label="Previous"><span aria-hidden="true">«</span></a></li>
			</c:if>
			<c:forEach var="i" begin="1" end="${sumPage}">
				<li><a
					<c:if test="${currentPage == i}">style="color:red"</c:if>
					href="${pageContext.request.contextPath}/${i}">${i} <!-- <span class="sr-only">(current)</span> --></a></li>
			</c:forEach>
			<c:if test="${currentPage < sumPage}">
				<li><a
					href="${pageContext.request.contextPath}/${currentPage + 1}"
					aria-label="Previous"><span aria-hidden="true">«</span></a></li>
			</c:if>
		</ul>
	</nav>
</div>

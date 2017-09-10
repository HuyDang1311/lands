<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
<div class="clearfix sidebar">
						<div class="clearfix single_sidebar category_items">
							<h2>Danh mục bất động sản</h2>
							<ul>
								<c:forEach items="${listCat}" var="objCat">
									<li><a href="cat.php">${objCat.cname}</a></li>
								</c:forEach>
							</ul>
						</div>

						<div class="clearfix single_sidebar">
							<div class="popular_post">
								<div class="sidebar_title"><h2>Xem nhiều</h2></div>
								<ul>
									<li><a href="">Duis sed odio sit amet nibh vulputate cursus a sit amet mauris. </a></li>
									<li><a href="">Duis sed odio sit amet nibh vulputate cursus a sit amet mauris. </a></li>
									<li><a href="">Duis sed odio sit amet nibh vulputate cursus a sit amet mauris. </a></li>
									<li><a href="">Duis sed odio sit amet nibh vulputate cursus a sit amet mauris. </a></li>
									<li><a href="">Duis sed odio sit amet nibh vulputate cursus a sit amet mauris. </a></li>
								</ul>
							</div>
							</div>
						
						<div class="clearfix single_sidebar">
							<h2>Danh mục hot</h2>
							<ul>
								<li><a href="">Category Name <span>(12)</span></a></li>
								<li><a href="">Category Name <span>(12)</span></a></li>
								<li><a href="">Category Name <span>(12)</span></a></li>
								<li><a href="">Category Name <span>(12)</span></a></li>
							</ul>
						</div>
					</div>
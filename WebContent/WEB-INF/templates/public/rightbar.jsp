<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
<div class="clearfix sidebar">
						<div class="clearfix single_sidebar category_items">
							<div class="sidebar_title"><h2>Danh mục bất động sản</h2></div>
							<ul>
								<c:forEach items="${listCat}" var="objCat">
									<li><a href="${pageContext.request.contextPath}/danh-muc/${slugUtil.makeSlug(objCat.cname)}-${objCat.cid}">${objCat.cname}</a><span> (${catDao.countObjCat(objCat.cid)})</span></li>
								</c:forEach>
							</ul>
						</div>

						<div class="clearfix single_sidebar">
							<div class="popular_post">
								<div class="sidebar_title"><h2>Bài viết mới nhất</h2></div>
								<ul>
									<c:forEach items="${listView}" var="objView">
										<li>
											<a href="${pageContext.request.contextPath}/chi-tiet/${slugUtil.makeSlug(objView.lname)}-${objView.lid}.html">${objView.lname}<img alt="sao.gif" src="${pageContext.request.contextPath}/templates/public/images/thumbs/new2_e0.gif"></a>
											
										</li>
									</c:forEach>
								</ul>
							</div>
							</div>
						
						<div class="clearfix single_sidebar">
							<div class="sidebar_title"><h2>Bài viết quan tâm nhiều nhất</h2></div>
							<ul>
								<c:forEach items="${listCare}" var="objCare">
								<li>
									<div class="row">
										<div class="col-sm-6">
											<img alt="${objCare.lname}" src="${pageContext.request.contextPath}/files/${objCare.picture}">
										</div>
										<div class="col-sm-6">
											<a href="${pageContext.request.contextPath}/chi-tiet/${slugUtil.makeSlug(objCare.lname)}-${objCare.lid}.html">${stringUtil.nameCland(objCare.lname)}</a>
										</div>
									</div>
								</li>
								</c:forEach>
							</ul>
						</div>
					</div>
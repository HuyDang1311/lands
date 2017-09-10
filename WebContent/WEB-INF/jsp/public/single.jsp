<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
<div class="clearfix content">
						<h1>${objLands.lname}</h1>
						<fmt:formatDate value="${objLands.date_create}" var="year" pattern="yyyy-MM-dd"/>
						<div class="clearfix post-meta">
							<p><span><i class="fa fa-clock-o"></i> Địa chỉ: ${objLands.address}</span> <span><i class="fa fa-folder"></i> Diện tích: 100m2</span></p><br \>
							<p><span><i class="fa fa-folder"></i> Diện tích: ${objLands.area} m2</span>  <span><i class="fa fa-folder"></i> Giá: ${objLands.gia} đ</span> <span><i class="fa fa-folder"></i> Lượt xem: ${objLands.count_views}</span> <span><i class="fa fa-folder"></i> Đăng ngày: ${year}</span></p>
						</div>
						
						<div class="vnecontent">
							<c:choose>
	                            	 	<c:when test="${not empty objLands.picture}">
	                               		 <img src="${pageContext.request.contextPath}/files/${objLands.picture}" style="width: 565px;height: 500px;margin-left: 54px;margin-bottom: 31px;" alt="${objLands.lname}" />
	                               		 </c:when>
	                               		 <c:otherwise>
	                               		  <img src="${pageContext.request.contextPath}/files/noimages.jpg" alt="khong co hinh anh" />
	                               		 </c:otherwise>
	                        </c:choose>
							<p>${objLands.description}</p>
						</div>
						<div style="border: 1px solid #ececec;margin: 27px 0px;"></div>
						<div>
							<h2 style="font-size: 30px;">Thông tin liên hệ</h4>
							<p style="font-size: 19px;height: 25px;">Liên hệ: ${objLands.tenlh}</p>
							<p style="font-size: 19px;height: 25px;">Địa chỉ: ${objLands.dclienhe}</p>
							<p style="font-size: 19px;height: 25px;">Số điện thoại: ${objLands.sdt}</p>
						</div>
					
					</div>
					
						<div class="more_themes">
							<h2>Bất động sản liên quan <i class="fa fa-thumbs-o-up"></i></h2>
							<div class="more_themes_container">
							<c:forEach items="${listLQ}" var="objLQ">
								<div class="single_more_themes floatleft">
									<c:choose>
	                            	 	<c:when test="${not empty objLQ.picture}">
	                               		 <img src="${pageContext.request.contextPath}/files/${objLQ.picture}" alt="${objLQ.lname}" />
	                               		 </c:when>
	                               		 <c:otherwise>
	                               		  <img style="height: 144px;width: 220px;" src="${pageContext.request.contextPath}/files/noimages.jpg" alt="khong co hinh anh" />
	                               		 </c:otherwise>
	                      			  </c:choose>
									<a href="${pageContext.request.contextPath}/detail/${objLQ.lid}"><h2>${objLQ.lname}</h2></a>
								</div>
							</c:forEach>
							</div>
						</div>
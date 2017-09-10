<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
<div class="clearfix slider">
                    <ul class="pgwSlider">
                        <li><img src="images/thumbs/megamind_07.jpg" alt="Paris, France" data-description="Eiffel Tower and Champ de Mars" data-large-src="images/slides/megamind_07.jpg" /></li>
                        <li><img src="images/thumbs/wall-e.jpg" alt="Montréal, QC, Canada" data-large-src="images/slides/wall-e.jpg" data-description="Eiffel Tower and Champ de Mars" /></li>
                        <li>
                            <img src="images/thumbs/up-official-trailer-fake.jpg" alt="Shanghai, China" data-large-src="images/slides/up-official-trailer-fake.jpg" data-description="Shanghai ,chaina">
                        </li>


                    </ul>
                </div>

                <div class="clearfix content">
                    <div class="content_title">
                        <h2>Bài viết mới</h2>
                    </div>
					<c:forEach items="${listLands}" var="objLands">
						<fmt:formatDate value="${objLands.date_create}" var="date" pattern="dd"/>
						<fmt:formatDate value="${objLands.date_create}" var="month" pattern="MM"/>
						<fmt:formatDate value="${objLands.date_create}" var="year" pattern="yyyy-MM-dd"/>
	                    <div class="clearfix single_content">
	                        <div class="clearfix post_date floatleft">
	                            <div class="date">
	                                <h3>${date}</h3>
	                                <p>Tháng ${month}</p>
	                            </div>
	                        </div>
	                        <div class="clearfix post_detail">
	                            <h2><a href="${pageContext.request.contextPath}/detail/${objLands.lid}">${objLands.lname}</a></h2>
	                            <div class="clearfix post-meta">
	                                <p><span><i class="fa fa-clock-o"></i> Địa chỉ: ${objLands.address}</span></p><br />
	                                <div class="row">
	                                	 <div class="col-sm-6">
	                               			 <p><span><i class="fa fa-folder"></i> Diện tích: ${objLands.area} m2</span>  <span><i class="fa fa-folder"></i> Giá: ${objLands.gia} đ</span> <span><i class="fa fa-folder"></i> Lượt xem: ${objLands.count_views}</span> <span><i class="fa fa-folder"></i> Đăng ngày: ${year}</span></p>
	                               	 	</div>
	                               	 	 <div class="col-sm-6">
	                               			 <p></p>
	                               	 	</div>
	                                </div>
	                            </div>
	                            <div class="clearfix post_excerpt">
	                            	 <c:choose>
	                            	 	<c:when test="${not empty objLands.picture}">
	                               		 <img src="${pageContext.request.contextPath}/files/${objLands.picture}" alt="${objLands.lname}" />
	                               		 </c:when>
	                               		 <c:otherwise>
	                               		  <img src="${pageContext.request.contextPath}/files/noimages.jpg" alt="khong co hinh anh" />
	                               		 </c:otherwise>
	                                </c:choose>
	                                <p>${objLands.description}</p>
	                            </div>
	                            <a href="${pageContext.request.contextPath}/detail/${objLands.lid}">Đọc thêm</a>
	                        </div>
	                    </div>
                    </c:forEach>
                </div>
                <div class="pagination">
                    <nav>
                        <ul>
                            <li>
                                <a href="">
                                    << </a>
                            </li>
                            <c:forEach var = "i" begin="1" end="${sumPage}">
                           	 	<li><a <c:if test="${currentPage == i}">style="color:red"</c:if> href="${pageContext.request.contextPath}/${i}">${i} <!-- <span class="sr-only">(current)</span> --></a></li>
                           	 </c:forEach>
                            <li><a href=""> >> </a></li>
                        </ul>
                    </nav>
                </div>
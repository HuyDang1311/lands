<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
                <div class="content-box-large">
                    <div class="row">
                        <div class="panel-heading">
                            <div class="panel-title ">Quản lý tin</div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-md-8">
                            <a href="${pageContext.request.contextPath}/admin/lands/add" class="btn btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Thêm</a>

                        </div>
                        <div class="col-md-4">
                            <div class="input-group form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                         <button class="btn btn-primary" type="button">Search</button>
                       </span>
                            </div>
                        </div>
                    </div>
					<hr>
					<c:if test="${msg != null}">
                    	<div class="alert alert-success">
	                	${msg}
	                	</div>
	                </c:if>
	                <c:if test="${err != null}">
                    	<div class="alert alert-warning">
	                	${err}
	                	</div>
	                </c:if>
                    <div class="row">
                        <div class="panel-body">
                            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th style="width: 130px;">Tên tin</th>
<!--                                         <th>Miêu tả</th> -->
                                        <th>Ngày đăng</th>
                                        <th>Danh mục</th>
                                        <th style="width: 100px;">Hình ảnh</th>
                                        <th>Diện tích(m2)</th>
                                        <th>Giá(Đồng)</th>
                                        <th style="width: 140px;">Địa chỉ</th>
                                        <th>Tên liên hệ</th>
                                        <th>Đ/c liên hệ</th>
                                        <th>SDT liên hệ</th>
                                        <th>Lượt đọc</th>
                                        <th>Quan tâm</th>
                                        <th>Active</th>
                                        <th>Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${listLands}" var="objLands">
                                    <tr class="odd gradeX">
                                        <td>${objLands.lid}</td>
                                        <td>${objLands.lname}</td>
<%--                                         <td>${objLands.description}</td> --%>
                                        <td>${objLands.date_create}</td>
                                        <td>${objLands.cname}</td>
                                        <td class="center text-center">
                                        	<c:choose>
			                            	 	<c:when test="${not empty objLands.picture}">
			                               		 <img src="${pageContext.request.contextPath}/files/${objLands.picture}" style="width: 100px;" alt="${objLands.picture}" />
			                               		 </c:when>
			                               		 <c:otherwise>
			                               		  <img src="${pageContext.request.contextPath}/files/noimage.jpg" style="width: 100px;" alt="noimages" />
			                               		 </c:otherwise>
			                                </c:choose>
                                        </td>
                                        <td class="center">${objLands.area}</td>
                                        <td>${objLands.gia}</td>
                                        <td>${objLands.address}</td>
                                        <td>${objLands.tenlh}</td>
                                        <td>${objLands.dclienhe}</td>
                                        <td>${objLands.sdt}</td>
                                        <td class="center">${objLands.count_views}</td>
                                        <td>85</td>
                                        <td>0</td>
                                        <td class="center text-center">
                                            <a href="${pageContext.request.contextPath}/admin/lands/edit/${objLands.lid}" title="" class="btn btn-primary"><span class="glyphicon glyphicon-pencil "></span> Sửa</a>
                                            <a href="${pageContext.request.contextPath}/admin/lands/del/${objLands.lid}" title="" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xóa</a>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>

                            <nav class="text-center" aria-label="...">
                                <ul class="pagination">
                                    <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
                                    <c:forEach var = "i" begin="1" end="${sumPage}">
                                   	 	<li <c:if test="${currentPage == i}">class="active"</c:if>><a href="${pageContext.request.contextPath}/admin/lands/${i}">${i} <span class="sr-only">(current)</span></a></li>
                                   	 </c:forEach>
                                    <li><a href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>

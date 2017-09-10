<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>    
                <div class="content-box-large">
                    <div class="row">
                        <div class="panel-heading">
                            <div class="panel-title ">Quản lý danh mục</div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-md-8">
                            <a href="${pageContext.request.contextPath}/admin/cat/add" class="btn btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Thêm</a>

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
                                        <th>Tên danh mục</th>
                                        <th style="text-align: center">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${listCat}" var="objCat" >
                                	<c:set var="stt" value="${(currentPage - 1) * 5 + 1}"></c:set>
	                                    <tr class="odd gradeX">
	                                        <td>${objCat.cid}</td>
	                                        <td>${objCat.cname}</td>
	                                        <td class="center text-center">
	                                            <a href="${pageContext.request.contextPath}/admin/cat/edit/${objCat.cid}" title="" class="btn btn-primary"><span class="glyphicon glyphicon-pencil "></span> Sửa</a>
	                                            <a href="${pageContext.request.contextPath}/admin/cat/del/${objCat.cid}" title="" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xóa</a>
	                                        </td>
	                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>

                            <!-- Pagination -->
                            <nav class="text-center" aria-label="...">
                                <ul class="pagination">
                                    <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
                                    <c:forEach var="i" begin="1" end="${sumPage}">
                                    	<li <c:if test="${currentPage == i}">class="active"</c:if>><a href="${pageContext.request.contextPath}/admin/cat/${i}">${i}<!-- <span class="sr-only">(current)</span> --></a></li>
                                    </c:forEach>
                                    <li><a href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
                                </ul>
                            </nav>
                            <!-- /.pagination -->

                        </div>
                    </div>
                    <!-- /.row -->
                </div>
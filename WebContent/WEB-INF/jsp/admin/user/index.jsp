<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<div class="content-box-large">
	<div class="row">
		<div class="panel-heading">
			<div class="panel-title ">Quản lý người dùng</div>
		</div>
	</div>
	<hr>
	<div class="row">
		<div class="col-md-8">
			<c:if test="${user.rank_id == 1 }">
			<a href="${pageContext.request.contextPath}/admin/user/add"
				class="btn btn-success"><span class="glyphicon glyphicon-plus"
				aria-hidden="true"></span>&nbsp;Thêm</a>
			</c:if>

		</div>
		<div class="col-md-4">
			<div class="input-group form">
				<form action="${pageContext.request.contextPath}/admin/user/search"
					method="post">
					<div class="col-md-8">
						<input type="text" class="form-control" value="${param.search}"
							name="search" required="required" placeholder="Search...">
					</div>
					<div class="col-md-4" style="margin-left: -28px;">
						<input class="btn btn-primary" type="submit" value="search">
					</div>
				</form>
			</div>
		</div>
	</div>
	<hr>
	<c:if test="${msg != null}">
		<div class="alert alert-success">${msg}</div>
	</c:if>
	<c:if test="${err != null}">
		<div class="alert alert-warning">${err}</div>
	</c:if>
	<div class="row">
		<div class="panel-body">
			<table cellpadding="0" cellspacing="0" border="0"
				class="table table-striped table-bordered" id="example">
				<c:choose>
					<c:when test="${listUser.size()>0}">
						<thead>
							<tr>
								<th>ID</th>
								<th>Tên đăng nhập</th>
								<th>Họ tên</th>
								<th>Cấp bậc</th>
								<th>Chức năng</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listUser}" var="objUser">
								<tr class="odd gradeX">
									<td>${objUser.id}</td>
									<td>${objUser.username}</td>
									<td>${objUser.fullname}</td>
									<td>${objUser.rankname}</td>
									<td class="center text-center">
										<c:if test="${user.rank_id == 1 || user.username == objUser.username}">
										<a
										href="${pageContext.request.contextPath}/admin/user/edit/${objUser.id}"
										title="" class="btn btn-primary"><span
											class="glyphicon glyphicon-pencil "></span> Sửa</a> 
										</c:if>
										<c:if test="${user.rank_id == 1}">
										<a
										href="${pageContext.request.contextPath}/admin/user/del/${objUser.id}"
										title="" class="btn btn-danger"><span
											class="glyphicon glyphicon-trash"></span> Xóa</a></td>
											</c:if>
									
								</tr>
							</c:forEach>
						</tbody>
					</c:when>
					<c:otherwise>
						<h1>Không có dữ liệu</h1>
					</c:otherwise>
				</c:choose>
			</table>

			<!-- Pagination -->
			<nav class="text-center" aria-label="...">
				<ul class="pagination">
					<c:if test="${currentPage > 1}">
						<li><a
							href="${pageContext.request.contextPath}/admin/user/${currentPage	-1}"
							aria-label="Previous"><span aria-hidden="true">«</span></a></li>
					</c:if>
					<c:forEach var="i" begin="1" end="${sumPage}">
						<li <c:if test="${currentPage == i}">class="active"</c:if>><a
							href="${pageContext.request.contextPath}/admin/user/${i}">${i}<!--  <span class="sr-only">(current)</span> --></a></li>
					</c:forEach>
					<c:if test="${currentPage < sumPage}">
						<li><a
							href="${pageContext.request.contextPath}/admin/user/${currentPage	+1}"
							aria-label="Next"><span aria-hidden="true">»</span></a></li>
					</c:if>
				</ul>
			</nav>
			<!-- /.pagination -->

		</div>
	</div>
	<!-- /.row -->
</div>
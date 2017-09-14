<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<div class="content-box-large">
	<div class="row">
		<div class="panel-heading">
			<div class="panel-title ">Danh sách liên hệ</div>
		</div>
	</div>
	<hr>
	<div class="row">
		<div class="col-md-8"></div>
		<div class="col-md-4">
			<div class="input-group form">
				<form action="${pageContext.request.contextPath}/admin/contact/search" method="post">
					<div class="col-md-8">
						<input type="text" class="form-control" value="${param.search}" name="search" required="required" placeholder="Search...">
					</div>
					<div class="col-md-4" style="margin-left: -28px;" >
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
					<c:when test="${listContact.size()>0}">
				<thead>
					<tr>
						<th>ID</th>
						<th>Tên người gửi</th>
						<th>Email</th>
						<th>Chủ đề</th>
						<c:if test="${user.rank_id == 1 || user.rank_id == 2}">
						<th>Chức năng</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listContact}" var="objContact">
						<tr class="odd gradeX">
							<td>${objContact.cid}</td>
							<td>${objContact.fullname}</td>
							<td>${objContact.email}</td>
							<td>${objContact.content}</td>
							<c:if test="${user.rank_id == 1 || user.rank_id == 2}">
							<td class="center text-center"><a
								href="${pageContext.request.contextPath}/admin/contact/del/${objContact.cid}"
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
						<li><a href="${pageContext.request.contextPath}/admin/contact/${currentPage	-1}" aria-label="Previous"><span
							aria-hidden="true">«</span></a></li>
					</c:if>
					<c:forEach var="i" begin="1" end="${sumPage}">
						<li <c:if test="${currentPage == i}">class="active"</c:if>><a
							href="${pageContext.request.contextPath}/admin/contact/${i}">${i}<!--  <span class="sr-only">(current)</span> --></a></li>
					</c:forEach>
					<c:if test="${currentPage < sumPage}">
						<li><a href="${pageContext.request.contextPath}/admin/contact/${currentPage	+1}" aria-label="Next"><span aria-hidden="true">»</span></a></li>
					</c:if>
				</ul>
			</nav>
			<!-- /.pagination -->

		</div>
	</div>
	<!-- /.row -->
</div>
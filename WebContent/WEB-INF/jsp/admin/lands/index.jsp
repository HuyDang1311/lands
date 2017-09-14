<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<div class="content-box-large">
	<div class="row">
		<div class="panel-heading">
			<div class="panel-title ">Quản lý tin</div>
		</div>
	</div>
	<hr>
	<div class="row">
		<div class="col-md-8">
			<a href="${pageContext.request.contextPath}/admin/lands/add"
				class="btn btn-success"><span class="glyphicon glyphicon-plus"
				aria-hidden="true"></span>&nbsp;Thêm</a>

		</div>
		<div class="col-md-4">
			<form action="${pageContext.request.contextPath}/admin/lands/search" method="post">
					<div class="col-md-8">
						<input type="text" class="form-control" value="${param.search}" name="search" placeholder="Search...">
					</div>
					<div class="col-md-4" style="margin-left: -28px;" >
						<input class="btn btn-primary" type="submit" value="search">
					</div>
			</form>
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
					<c:when test="${listLands.size()>0}">
				<thead>
					<tr>
						<th>ID</th>
						<th style="width: 130px;">Tên tin</th>
						<!--                                         <th>Miêu tả</th> -->
						<th>Ngày đăng</th>
						<th>Danh mục</th>
						<th style="width: 75px;">Hình ảnh</th>
						<th>Diện tích(m2)</th>
						<th>Giá</th>
						<th style="width: 140px;">Địa chỉ</th>
						<th>Tên liên hệ</th>
						<th>Đ/c liên hệ</th>
						<th>SDT liên hệ</th>
						<th>Lượt đọc</th>
						<th>Quan tâm</th>
						<th>Tọa độ</th>
						<c:if test="${user.rank_id == 1 || user.rank_id == 2}">
						<th>Active</th>
						<th>Chức năng</th>
						</c:if>
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
							<td class="center text-center"><c:choose>
									<c:when test="${not empty objLands.picture}">
										<img
											src="${pageContext.request.contextPath}/files/${objLands.picture}"
											style="width: 75px;" alt="${objLands.picture}" />
									</c:when>
									<c:otherwise>
										<img
											src="${pageContext.request.contextPath}/files/noimage.jpg"
											style="width: 75px;" alt="noimages" />
									</c:otherwise>
								</c:choose></td>
							<td class="center">${objLands.area}</td>
							<td>${objLands.gia}</td>
							<td>${objLands.address}</td>
							<td>${objLands.tenlh}</td>
							<td>${objLands.dclienhe}</td>
							<td>${objLands.sdt}</td>
							<td class="center">${objLands.count_views}</td>
							<td>${objLands.care}</td>
							<td>
								<p>Vĩ độ: ${objLands.lat}</p>
								<br />
								<p>Kinh độ: ${objLands.lng}</p>
							</td>
							<c:if test="${user.rank_id == 1 || user.rank_id == 2}">
							<td id="active_${objLands.lid}"><a href="javascript:void(0)"
								onclick="return active(${objLands.lid},${objLands.active})">
									<c:choose>
										<c:when test="${objLands.active==1}">
											<c:set var="active" value="acti.gif"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="active" value="deactive.gif"></c:set>
										</c:otherwise>
									</c:choose> <img
									src="${pageContext.request.contextPath}/templates/admin/images/<c:out value="${active}"></c:out>">
							</a></td>
							</c:if>
							<td class="center text-center">
								<c:if test="${user.rank_id == 1 || user.rank_id == 2}">
							<a
								href="${pageContext.request.contextPath}/admin/lands/edit/${objLands.lid}"
								title="" class="btn btn-primary"><span
									class="glyphicon glyphicon-pencil "></span> Sửa</a> <a
								href="${pageContext.request.contextPath}/admin/lands/del/${objLands.lid}"
								title="" class="btn btn-danger"><span
									class="glyphicon glyphicon-trash"></span> Xóa</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				</c:when>
					<c:otherwise>
						<h1>Không có dữ liệu</h1>
					</c:otherwise>
				</c:choose>
			</table>
			<script type="text/javascript">
								function active(id, active){
									$.ajax({
										url: '/duancland/admin/lands/active',
										type: 'POST',
										cache: false, 
										data: {
											//Dữ liệu gửi đi
											aid:id,
											aactive:active
										},
										success: function(data){
											$("#active_"+id).html(data);
								
										},
										error: function (){
											// Xử lý nếu có lỗi
											alert("Có lỗi trong quá trình xử lý");
										}
									});
								}
							</script>
			<nav class="text-center" aria-label="...">
				<ul class="pagination">
					<c:if test="${currentPage > 1}">
						<li><a href="${pageContext.request.contextPath}/admin/lands/${currentPage	-1}" aria-label="Previous"><span
							aria-hidden="true">«</span></a></li>
					</c:if>
					<c:forEach var="i" begin="1" end="${sumPage}">
						<li <c:if test="${currentPage == i}">class="active"</c:if>><a
							href="${pageContext.request.contextPath}/admin/lands/${i}">${i}
								<span class="sr-only">(current)</span>
						</a></li>
					</c:forEach>
					<c:if test="${currentPage < sumPage}">
						<li><a href="${pageContext.request.contextPath}/admin/lands/${currentPage	+1}" aria-label="Next"><span aria-hidden="true">»</span></a></li>
					</c:if>
				</ul>
			</nav>
		</div>
	</div>
</div>

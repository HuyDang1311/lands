<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<div class="row">
	<div class="col-md-12 panel-info">
		<div class="content-box-header panel-heading">
			<div class="panel-title ">Cập nhật tin</div>
		</div>
		<div class="content-box-large box-with-header">
			<div>
				<form action="" method="post" enctype="multipart/form-data">
					<div class="row mb-10"></div>
					<div class="row">
						<div class="col-sm-10">
							<c:if test="${err ne null}">
								<ul class="alert alert-warning">
									<c:forEach items="${err}" var="objErr">
										<li>${objErr}</li>
									</c:forEach>
								</ul>
							</c:if>
							<div class="form-group">
								<label for="name" class="require">Tên tin:</label> <input
									type="text" class="form-control" value="${objLand.lname}"
									placeholder="Nhập tên tin" name="lname" required="required"
									minlength="5">
							</div>

							<div class="form-group">
								<label class="require">Danh mục tin:</label> <select
									class="form-control" name="ciddd">
									<c:forEach items="${listCat}" var="objCat">
										<c:choose>
											<c:when test="${objLand.cid == objCat.cid}">
												<option value="${objCat.cid}" selected>${objCat.cname}</option>
											</c:when>
											<c:otherwise>
												<option value="${objCat.cid}">${objCat.cname}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>

							<div class="form-group">
								<label>Thêm hình ảnh</label> <input type="file" name="hinhanh"
									class="btn btn-default" id="exampleInputFile1">
								<p class="help-block">
									<em>Định dạng: jpg, png, jpeg,...</em>
								</p>
								<img
									src="${pageContext.request.contextPath}/files/${objLand.picture}"
									style="width: 100px;" alt="${objLands.picture}" />
							</div>

							<div class="form-group">
								<label for="name" class="require">Diện tích(m2):</label> <input
									type="number" step="any" value="${objLand.area}"
									class="form-control" name="area" placeholder="Nhập diện tích"
									required="required">
							</div>

							<div class="form-group">
								<label for="name" class="require">Giá:</label> <input
									type="text" value="${objLand.gia}" class="form-control"
									name="gia" placeholder="Nhập giá bán" required="required">
							</div>

							<div class="form-group">
								<label class="require">Địa chỉ:</label>
								<textarea class="form-control" rows="3" name="address"
									placeholder="Nhập địa chỉ" required="required">${objLand.address}</textarea>
							</div>

							<div class="form-group">
								<label for="name" class="require">Tên liên hệ:</label> <input
									type="text" class="form-control" value="${objLand.tenlh}"
									placeholder="Nhập tên liên hệ" name="tenlh" required="required"
									minlength="3" maxlength="32">
							</div>

							<div class="form-group">
								<label class="require">Địa chỉ liên hệ:</label>
								<textarea class="form-control" value="" rows="3" name="dclienhe"
									placeholder="Nhập địa chỉ liên hệ" required="required">${objLand.dclienhe}</textarea>
							</div>

							<div class="form-group">
								<label for="name" class="require">Số điện thoại liên hệ:</label>
								<input type="text" value="${objLand.sdt}" class="form-control"
									name="sdt" placeholder="Nhập số điện thoại" required="required"
									minlength="10" maxlength="13">
							</div>

							<div class="form-group">
								<label for="name" class="require">Vỹ độ:</label> <input
									type="text" class="form-control" value="${objLand.lat}"
									placeholder="Nhập vỹ độ" name="lat" required="required">
							</div>

							<div class="form-group">
								<label for="name" class="require">Kinh độ:</label> <input
									type="text" class="form-control" value="${objLand.lng}"
									placeholder="Nhập kinh độ" name="lng" required="required">
							</div>

							<div class="form-group">
								<label>Mô tả</label>
								<textarea class="form-control, ckeditor" name="description"
									rows="3" required="required">${objLand.description}</textarea>
							</div>

						</div>

					</div>

					<hr>

					<div class="row">
						<div class="col-sm-12">
							<input type="submit" value="Thêm" class="btn btn-success" /> <input
								type="reset" value="Nhập lại" class="btn btn-default" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
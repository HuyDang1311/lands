<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<div class="clearfix content">
	<h1>${objLands.lname}</h1>
	<fmt:formatDate value="${objLands.date_create}" var="year"
		pattern="yyyy-MM-dd" />
	<div class="clearfix post-meta">
		<p>
			<span><i class="fa fa-clock-o"></i> Địa chỉ:
				${objLands.address}</span>
		</p>
		<p>
			<span><i class="fa fa-folder"></i> Diện tích: ${objLands.area}
				m2</span> <span><i class="fa fa-folder"></i> Giá: ${objLands.gia}</span>
			<span><i class="fa fa-folder"></i> Lượt xem:
				${objLands.count_views}</span> <span><i class="fa fa-folder"></i>
				Đăng ngày: ${year}</span>
		</p>
	</div>

	<div class="vnecontent">
		<c:choose>
			<c:when test="${not empty objLands.picture}">
				<img
					src="${pageContext.request.contextPath}/files/${objLands.picture}"
					style="width: 565px; height: 500px; margin-left: 54px; margin-bottom: 31px;"
					alt="${objLands.lname}" />
			</c:when>
			<c:otherwise>
				<img src="${pageContext.request.contextPath}/files/noimages.jpg"
					alt="khong co hinh anh" />
			</c:otherwise>
		</c:choose>
	</div>
</div>
<div class="container">
	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#home">Thông
				tin mô tả</a></li>
		<li><a data-toggle="tab" href="#menu1">Bản đồ</a></li>
	</ul>

	<div class="tab-content">
		<div id="home" class="tab-pane fade in active">
			<p>${objLands.description}</p>
		</div>
		<div id="menu1" class="tab-pane fade">
			<div id="googleMap" style="width: 650px; height: 400px;">
			
			</div>

			<script>
						      function initMap() {
						        var uluru = {lat: ${objLands.lat}, lng: ${objLands.lng}};
						        var map = new google.maps.Map(document.getElementById('googleMap'), {
						          zoom: 15,
						          center: uluru
						        });
						        var marker = new google.maps.Marker({
						          position: uluru,
						          map: map
						        });
						      }
						    </script>
			<script
				src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBNPyFN3e6vOFcRO1mLIl7h93hky9iqK6I&callback=initMap">
						    </script>
		</div>
	</div>
</div>
<div class="row" id="quantam">
	<c:choose>
		<c:when test="${objLands.statuscare==1}">
			<c:set var="care" value="btn btn-warning"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="care" value="btn btn-primary"></c:set>
		</c:otherwise>
	</c:choose>
	<a href="javascript:void(0)" title=""
		class='<c:out value="${care}"></c:out>'
		style="color: white; margin-left: 18px;"
		onclick="return getQuantam(${objLands.lid},${objLands.statuscare})">Quan
		Tâm</a> <span id="valu" style="margin: 15px;">${objLands.care}</span>
</div>
<!-- 					<div><button type="button" class="btn btn-warning">Quan tâm  <span style="    padding-left: 8px;"> 8</span></button></div> -->
<div style="border: 1px solid #ececec; margin-bottom: 27px;"></div>
<div>
	<h2 style="font-size: 30px;">
		Thông tin liên hệ
		</h4>
		<ul>
			<li><p>Liên hệ: ${objLands.tenlh}</p></li>
			<li><p>Địa chỉ: ${objLands.dclienhe}</p></li>
			<li><p>Số điện thoại: ${objLands.sdt}</p></li>
		</ul>
</div>
<div class="more_themes">
	<h2>
		Bất động sản liên quan <i class="fa fa-thumbs-o-up"></i>
	</h2>
	<c:forEach items="${listLQ}" var="objLQ">
		<fmt:formatDate value="${objLQ.date_create}" var="date"
			pattern="dd" />
		<fmt:formatDate value="${objLQ.date_create}" var="month"
			pattern="MM" />
		<fmt:formatDate value="${objLQ.date_create}" var="year"
			pattern="dd-MM-yyyy" />
		<div class="clearfix single_content">
			<div class="clearfix post_date floatleft">
				<div class="date">
					<h3>${date}</h3>
					<p>Tháng ${month}</p>
				</div>
			</div>
			<div class="clearfix post_detail">
				<h2 style="    font-size: 24px; font-weight: 500;">
					<a href="${pageContext.request.contextPath}/chi-tiet/${slugUtil.makeSlug(objLQ.lname)}-${objLQ.lid}.html">${objLQ.lname}</a>
				</h2>
				<div style="    margin-bottom: 13px;">
					<img alt="sao.gif" src="${pageContext.request.contextPath}/templates/public/images/thumbs/1sao.gif">
					<img alt="sao.gif" src="${pageContext.request.contextPath}/templates/public/images/thumbs/1sao.gif">
					<img alt="sao.gif" src="${pageContext.request.contextPath}/templates/public/images/thumbs/1sao.gif">
				</div>
				<div class="clearfix post-meta">
					<p style="margin-bottom: -5px;">
						<span><i class="fa fa-location-arrow" aria-hidden="true"></i>
							Địa chỉ: ${objLQ.address}</span>
					</p>
					<br />
					<div class="row">
						<p>
							<span><i class="fa fa-folder"></i> Diện tích:
								${objLQ.area} m<sup>2</sup></span> <span><i class="fa fa-folder"></i>
								Giá: ${objLQ.gia}</span> <span><i class="fa fa-folder"></i>
								Lượt xem: ${objLQ.count_views}</span> <span><i
								class="fa fa-heart" style="color: red;"></i></i> ${objLQ.care}</span>
						</p>
					</div>
				</div>
				<div class="clearfix post_excerpt">
					<img id="myImg"
						src="${pageContext.request.contextPath}/files/${objLQ.picture}"
						alt="Trolltunga, Norway" width="300" height="200">
					<p>${stringUtil.stringDrop(objLQ.description)}</p>
				</div>
				<div style="padding-top: 25px;">
					<div style="float: left; color: #b4b4bd;">Đăng ngày: ${year}</div>
					<div style="float: right;" id="more">
						<a
							href="${pageContext.request.contextPath}/detail/${objLQ.lid}">Đọc
							thêm</a>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
<script>	
						function getQuantam(id,status){
							var qTam = parseInt($("#valu").text());
							$.ajax({
								url: '/duancland/quantam',
								type: 'POST',
								cache: false, 
								data: {
									//Dữ liệu gửi đi
									aid:id,
									astatus:status,
									aqTam : qTam,
								},
								success: function(data){
									$("#quantam").html(data);
						
								},
								error: function (){
									// Xử lý nếu có lỗi
									alert("Có lỗi trong quá trình xử lý");
								}
							});
						}
						</script>
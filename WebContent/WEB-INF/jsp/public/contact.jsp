<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
<div class="clearfix content">

                    <div class="contact_us">
                    	<h1 style="text-align: center;">Công ty Cổ phần Bất động sản CLand</h1>
                    	<p style="text-align: center;">Tên giao dịch: TOPERLAND.,JSC</p>
                    	<p style="text-align: center;">Địa chỉ: 13 Nguyễn Văn Linh, Q.Hải Châu, Tp Đà Nẵng</p>
                    	<p style="text-align: center;">Quy mô công ty: 100- 499 nhân viên</p>
                    	<p style="text-align: center;">Điện thoại: 01645315444</p>
						<c:if test="${msg != null}">
							<div class="alert alert-success">${msg}</div>
						</c:if>
						<c:if test="${err != null}">
							<div class="alert alert-warning">${err}</div>
						</c:if>
						<c:if test="${listErr ne null}">
							<ul class="alert alert-warning">
								<c:forEach items="${listErr}" var="objErr">
									<li>${objErr}</li>
								</c:forEach>
							</ul>
						</c:if>
                        <form action="" method="post">
                            <p><input type="text" class="wpcf7-text" name="fullname" value="${param.fullname}" required="required" minlength="3" maxlength="32" placeholder="Họ tên *" /></p>
                            <p><input type="email" class="wpcf7-email" name="email" value="${param.email}" required="required" placeholder="Email *" /></p>
                            <p><textarea class="wpcf7-textarea" name="content" required="required" placeholder="Nội dung *">${param.content}</textarea></p>
                            <p><input type="Submit" class="wpcf7-submit" value="Gửi liên hệ" /></p>
                        </form>

                    </div>

                </div>
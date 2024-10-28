<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<title>新闻资讯注册</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/xznstatic/css/bootstrap4.2.1.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/xznstatic/css/style.default.css" id="theme-stylesheet">

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets2/js/jquery.min.js"></script>

	<style type="text/css">
		.error{ color: red!important; }
	</style>
</head>

<body>
	<div class="page login-page">
        <div class="container d-flex align-items-center">
            <div class="form-holder has-shadow">
                <div class="row">
                    <!-- Logo & Information Panel-->
                    <div class="col-lg-6">
                        <div class="info d-flex align-items-center">
                            <div class="content">
                                <div class="logo">
                                    <h1>新闻资讯注册</h1>
                                </div>
                                <p></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 bg-white">
                        <div class="form d-flex align-items-center">
                            <div class="content">
                                <form id="registerForm" action="" method="post" class="form-validate">
                                    <button id="submitBtn" type="button" class="btn btn-primary" style="margin-top: 15px;">注 册</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

	<script src="${pageContext.request.contextPath}/resources/xznstatic/js/bootstrap4.2.1.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/js/validate/jquery.validate.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/js/validate/messages_zh.js"></script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/js/validate/card.js"></script>

	<script type="text/javascript">
		
		<%@ include file="../../utils/menu.jsp"%>
		
		<%@ include file="../../utils/baseUrl.jsp"%>

		// 表单校验
		function validform() {
			return $("#registerForm").validate({ 
				rules: {
							picture: {
					required: true,
				},
										content: {
					required: true,
				},
									},
				messages: {
					picture: {
						required: "图片不能为空",
					},
					content: {
						required: "内容不能为空",
					},
				}
			}).form();
		}
		// 添加表单校验方法
		function addValidation(){
			jQuery.validator.addMethod("isPhone", function(value, element) {
				var length = value.length;
				var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
				return this.optional(element) || (length == 11 && mobile.test(value));
			}, "请填写正确的手机号码");
			jQuery.validator.addMethod("isIdCardNo", function(value, element) {
				return this.optional(element) || idCardNoUtil.checkIdCardNo(value);
			}, "请正确输入您的身份证号码");
		}

		// 表单提交
		function submit() {
			if(validform()) {
				let data = {};
				let value = $('#registerForm').serializeArray();
				$.each(value, function (index, item) { 
					data[item.name] = item.value;
				});
				let json = JSON.stringify(data);
				$.ajax({ 
					type: "POST",
					url: baseUrl + "news/register",
					contentType: "application/json",
					data:json,
					beforeSend: function(xhr) {xhr.setRequestHeader("token", window.sessionStorage.getItem('token'));},
					success: function(res){    
						if(res.code == 0){
						alert("注册成功!");
						window.location.href="${pageContext.request.contextPath}/jsp/login.jsp";
						}else if(res.code == 401){
							<%@ include file="../../static/toLogin.jsp"%> 	
						}else{
							alert(res.msg)
						}
					},
				});      
			}else {
				alert("表单未填完整或有错误");
			}
		}    

		function ready() {
			addValidation();
			//注册表单验证
		    $(validform());
			$('#submitBtn').on('click', function(e) {
				e.preventDefault();
				submit();
			});
		}
		document.addEventListener("DOMContentLoaded", ready);
	</script>
</body>

</html>

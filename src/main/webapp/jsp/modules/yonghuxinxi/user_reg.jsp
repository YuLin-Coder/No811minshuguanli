<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-cn">

<head>
	<%@ include file="../../static/head.jsp" %>
	<link href="http://www.bootcss.com/p/bootstrap-datetimepicker/bootstrap-datetimepicker/css/datetimepicker.css"
		  rel="stylesheet">
	<script type="text/javascript" charset="utf-8">
        window.UEDITOR_HOME_URL = "${pageContext.request.contextPath}/resources/ueditor/"; //UEDITOR_HOME_URL、config、all这三个顺序不能改变
	</script>
	<script type="text/javascript" charset="utf-8"
			src="${pageContext.request.contextPath}/resources/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8"
			src="${pageContext.request.contextPath}/resources/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" charset="utf-8"
			src="${pageContext.request.contextPath}/resources/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<style>
	.error {
		color: red;
	}
</style>
<body>
<!-- Pre Loader -->
<div class="loading">
	<div class="spinner">
		<div class="double-bounce1"></div>
		<div class="double-bounce2"></div>
	</div>
</div>
<!--/Pre Loader -->
<div class="wrapper">
	<!-- Page Content -->
	<div id="content">
		<!-- Top Navigation -->
		<%@ include file="../../static/topNav.jsp" %>
		<!-- Menu -->
		<div class="container menu-nav">
			<nav class="navbar navbar-expand-lg lochana-bg text-white">
				<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					<span class="ti-menu text-white"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul id="navUl" class="navbar-nav mr-auto">

					</ul>
				</div>
			</nav>
		</div>
		<!-- /Menu -->
		<!-- Breadcrumb -->
		<!-- Page Title -->
		<div class="container mt-0">
			<div class="row breadcrumb-bar">
				<div class="col-md-6">
					<h3 class="block-title">编辑</h3>
				</div>
				<div class="col-md-6">
					<ol class="breadcrumb">
						<li class="breadcrumb-item">
							<a href="${pageContext.request.contextPath}/index.jsp">
								<span class="ti-home"></span>
							</a>
						</li>
						<li class="breadcrumb-item">管理</li>
						<li class="breadcrumb-item active">编辑</li>
					</ol>
				</div>
			</div>
		</div>
		<!-- /Page Title -->

		<!-- /Breadcrumb -->
		<!-- Main Content -->
		<div class="container" style="width: 500px">

			<div class="row" center>
				<!-- Widget Item -->
				<div class="col-md-12">
					<div class="widget-area-2 lochana-box-shadow">
						<h3 class="widget-title">注册</h3>
						<form id="addOrUpdateForm">
							<div class="form-row">
								<input id="updateId" name="id" type="hidden">
									<div class="form-group col-md-12">
										<label>用户名称</label>
										<input id="name" name="name" class="form-control"
											   placeholder="用户名称">
									</div>
									<div class="form-group col-md-12">
										<label>账号</label>
										<input id="account" name="account" class="form-control"
											   placeholder="账号">
									</div>
									<div class="form-group col-md-12">
										<label>密码</label>
										<input id="password" name="password" class="form-control"
											   placeholder="密码">
									</div>
									<div class="form-group col-md-12">
										<label>确认密码</label>
										<input id="password2" name="password2" class="form-control"
											   placeholder="确认密码">
									</div>
								<div class="form-group col-md-12 mb-3">
									<button id="exitBtn" type="button" class="btn btn-primary btn-lg">取消</button>
									<button id="submitBtn" type="button" class="btn btn-primary btn-lg">注册</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<!-- /Widget Item -->
			</div>
		</div>
		<!-- /Main Content -->
	</div>
	<!-- /Page Content -->
</div>
<!-- Back to Top -->
<a id="back-to-top" href="#" class="back-to-top">
	<span class="ti-angle-up"></span>
</a>
<!-- /Back to Top -->
<%@ include file="../../static/foot.jsp" %>
<script src="${pageContext.request.contextPath}/resources/js/vue.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.ui.widget.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.fileupload.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script type="text/javascript" charset="utf-8"
		src="${pageContext.request.contextPath}/resources/js/validate/jquery.validate.min.js"></script>
<script type="text/javascript" charset="utf-8"
		src="${pageContext.request.contextPath}/resources/js/validate/messages_zh.js"></script>
<script type="text/javascript" charset="utf-8"
		src="${pageContext.request.contextPath}/resources/js/validate/card.js"></script>
<script type="text/javascript" charset="utf-8"
		src="${pageContext.request.contextPath}/resources/js/datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script>
    <%@ include file="../../utils/menu.jsp"%>
    <%@ include file="../../static/setMenu.js"%>
    <%@ include file="../../utils/baseUrl.jsp"%>

    var tableName = "yonghuxinxi";
    var pageType = "add-or-update";
    var updateId = "";
    var crossTableId = -1;
    var crossTableName = '';
    var ruleForm = {};
    var crossRuleForm = {};

    var sbTypesOptions = [];

    var ruleForm = {};
    var vm = new Vue({
        el: '#addOrUpdateForm',
        data: {
            ruleForm: {},
        },
        beforeCreate: function () {
            var id = window.sessionStorage.getItem("updateId");
            if (id != null && id != "" && id != "null") {
                $.ajax({
                    type: "GET",
                    url: baseUrl + "yonghuxinxi/info/" + id,
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader("token", window.sessionStorage.getItem('token'));
                    },
                    success: function (res) {
                        if (res.code == 0) {
                            vm.ruleForm = res.data;
                        } else if (res.code == 401) {
                            <%@ include file="../../static/toLogin.jsp"%>
                        } else {
                            alert(res.msg)
                        }
                    },
                });
            }
        },
        methods: {}
    });

    // 表单提交
    function submit() {
        if (validform() == true) {
            let data = {};
            let value = $('#addOrUpdateForm').serializeArray();
			if($("#password2").val() != $("#password").val()){
                return alert("两次输入密码不一致")
			}
            $.each(value, function (index, item) {
                data[item.name] = item.value;
            });
            let json = JSON.stringify(data);
            httpJson("yonghuxinxi/save", "POST", data, (res) => {
                if(res.code == 0){
					alert("注册成功");
                    window.parent.location.href = "../../login.jsp";

				}
        })
            ;
        } else {
            alert("表单未填完整或有错误");
        }
    }

    //搜素输入检查
    function idChickValue(e){
        var this_val = e.value || 0;
        var reg=/^[0-9]*$/;
        if(!reg.test(this_val)){
            e.value = "";
            alert("输入不合法");
            return false;
        }
    }
    function balanceChickValue(e){
        var this_val = e.value || 0;
        var reg=/^[0-9]+.?[0-9]{1,2}$/;
        if(!reg.test(this_val)){
            e.value = "";
            alert("输入不合法");
            return false;
        }
    }
    function creditChickValue(e){
        var this_val = e.value || 0;
        var reg=/^[0-9]*$/;
        if(!reg.test(this_val)){
            e.value = "";
            alert("输入不合法");
            return false;
        }
    }

    function exit() {
        window.location.href = "../../login.jsp";
    }
    // 表单校验
    function validform() {
        return $("#addOrUpdateForm").validate({
            rules: {
                name: "required",
                account: "required",
                password: "required",
                password2: "required",
            },
            messages: {
                name: "用户名不能为空",
                account: "账号不能为空",
                password: "密码不能为空",
                password2: "确认密码不能为空",
            }
        }).form();
    }


    $(document).ready(function () {
        <%@ include file="../../static/myInfo.js"%>
        $('#submitBtn').on('click', function (e) {
            e.preventDefault();
            //console.log("点击了...提交按钮");
            submit();
        });
    });

</script>
</body>

</html>
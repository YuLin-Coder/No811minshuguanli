<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

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
                    <ul class="navbar-nav mr-auto" id="navUl">

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
                    <h3 class="block-title">编辑民宿信息评论表</h3>
                </div>
                <div class="col-md-6">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="${pageContext.request.contextPath}/index.jsp">
                                <span class="ti-home"></span>
                            </a>
                        </li>
                        <li class="breadcrumb-item">民宿信息评论表管理</li>
                        <li class="breadcrumb-item active">民宿信息评论表登记</li>
                    </ol>
                </div>
            </div>
        </div>
        <!-- /Page Title -->

        <!-- /Breadcrumb -->
        <!-- Main Content -->
        <div class="container">

            <div class="row">
                <!-- Widget Item -->
                <div class="col-md-12">
                    <div class="widget-area-2 lochana-box-shadow">
                        <h3 class="widget-title">民宿信息评论表信息</h3>
                        <form id="addOrUpdateForm">
                            <div class="form-row">
                                    <input id="updateId" name="id" type="hidden">
                                    <div class="form-group col-md-6">
                                        <label>创建时间</label>

                                        <input id="addtime" name="addtime" class="form-control"
                                               v-model="ruleForm.addtime" readonly>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label>关联表id</label>

                                        <input id="refid" name="refid" class="form-control"
                                               v-model="ruleForm.refid" readonly>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label>用户id</label>

                                        <input id="userid" name="userid" class="form-control"
                                               v-model="ruleForm.userid" readonly>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label>评论内容</label>

                                        <input id="replycontent" name="replycontent" class="form-control"
                                               v-model="ruleForm.replycontent" readonly>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label>回复内容</label>

                                        <input id="reply" name="reply" class="form-control"
                                               v-model="ruleForm.reply" readonly>
                                    </div>
                                <div class="form-group col-md-12 mb-3">
                                    <button id="exitBtn" type="button" class="btn btn-primary btn-lg">返回</button>
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
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>

<script>
    <%@ include file="../../utils/menu.jsp"%>
            <%@ include file="../../static/setMenu.js"%>
            <%@ include file="../../utils/baseUrl.jsp"%>

    var tableName = "discussfangwuxinxi";
    var pageType = "add-or-update";
    var updateId = "";


    var ruleForm = {};
    var vm = new Vue({
        el: '#addOrUpdateForm',
        data: {
            ruleForm: {},
        },
        beforeCreate: function () {
            var id = window.sessionStorage.getItem("id");
            if (id != null && id != "" && id != "null") {
                $.ajax({
                    type: "GET",
                    url: baseUrl + "discussfangwuxinxi/info/" + id,
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

    //查询当前页面下所有列表
    // 下拉框选项回显
    function setSelectOption() {
    }


    // 填充富文本框
    function setContent() {
    }

    // 获取当前详情
    function getDetails() {
        var id = window.sessionStorage.getItem("id");
        if (id != null && id != "" && id != "null") {
            $("#submitBtn").addClass("修改");
            updateId = id;
            window.sessionStorage.removeItem('id');
            $.ajax({
                type: "GET",
                url: baseUrl + "discussfangwuxinxi/info/" + id,
                async:false,
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("token", window.sessionStorage.getItem('token'));
                },
                success: function (res) {
                    if (res.code == 0) {
                        ruleForm = res.data
                        showImg();
                        setContent();
                        setMediaUrl();
                        setSelectOption();
                        // setDownloadBtn();
                    } else if (res.code == 401) {
                    <%@ include file="../../static/toLogin.jsp"%>
                    } else {
                        alert(res.msg);
                    }
                },
            });
        } else {
            $("#submitBtn").addClass("新增");
        }
    }

    //图片显示
    function showImg() {
    }

    function exit() {
        window.sessionStorage.removeItem("id");
        window.location.href = "list.jsp";
    }

    // 下载
    function download(fileName) {
        var url = baseUrl+'file/download?fileName='+fileName;
        window.open(url);
    }

    //设置下载
    function setDownloadBtn() {

    }


    //设置音视频播放链接
    function setMediaUrl() {
    }

    //打开新窗口播放媒体
    function mediaPlay(url) {
        //var url = baseUrl + "upload/" + fileName;
        window.open(url);
    }

    $(document).ready(function () {
        //设置右上角用户名
        $('.dropdown-menu h5').html(window.sessionStorage.getItem('username'))
        //设置项目名
        $('.sidebar-header h3 a').html(projectName)
        setMenu();
        $('#exitBtn').on('click', function (e) {
            e.preventDefault();
            exit();
        });

        //查询当前页面所有下拉框

        getDetails();

    <%@ include file="../../static/myInfo.js"%>
    });
    // 用户登出
    <%@ include file="../../static/logout.jsp"%>
</script>
</body>

</html>
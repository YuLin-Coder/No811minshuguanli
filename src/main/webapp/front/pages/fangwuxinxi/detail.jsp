<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="true" %>

<!-- 首页 -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>首页</title>
    <link rel="stylesheet" href="../../layui/css/layui.css">
    <!-- 样式 -->
    <link rel="stylesheet" href="../../css/style.css"/>
    <!-- 主题（主要颜色设置） -->
    <link rel="stylesheet" href="../../css/theme.css"/>
    <!-- 通用的css -->
    <link rel="stylesheet" href="../../css/common.css"/>
</head>
<body>
<div id="app">

    <div class="data-detail">
        <div class="data-detail-breadcrumb">
					<span class="layui-breadcrumb">
						<a href="../home/home.jsp">首页</a>
						<a><cite>{{title}}</cite></a>
					</span>


        </div>
        <div class="layui-row">
            <div class="layui-col-md5">
                <div class="layui-carousel" id="swiper">
                    <div carousel-item id="swiper-item">
                        <div v-for="(item,index) in swiperList" v-bind:key="index">
                            <img class="swiper-item" :src="item.img">
                        </div>
                    </div>
                </div>


            </div>
            <div class="layui-col-md7" style="padding-left: 20px;">
                <h1 class="title">{{title}}</h1>


                <div v-if="detail.fwlxTypes" class="detail-item">
                    <span>民宿类型：</span>
                    <span class="desc">
                        {{detail.fwlxTypes}}
                    </span>
                </div>
                <div v-if="detail.money" class="detail-item">
                    <span>价格（天）：</span>
                    <span class="desc">
                        {{detail.money}}
                    </span>
                </div>
                <div v-if="detail.address" class="detail-item">
                    <span>民宿地址：</span>
                    <span class="desc">
                        {{detail.address}}
                    </span>
                </div>
                <div v-if="detail.fwstateTypes" class="detail-item">
                    <span>民宿状态：</span>
                    <span class="desc">
                        {{detail.fwstateTypes}}
                    </span>
                </div>
                <div v-if="detail.fdTypes" class="detail-item">
                    <span>所属房东：</span>
                    <span class="desc">
                        {{detail.fdTypes}}
                    </span>
                </div>
                <div v-if="detail.phone" class="detail-item">
                    <span>房东联系电话：</span>
                    <span class="desc">
                        {{detail.phone}}
                    </span>
                </div>

                <div class="detail-item">
                    <button  type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" type="button" class="layui-btn btn-submit">
                        立即租房
                    </button>


                </div>

                <div class="detail-item" style="text-align: right;">


                </div>
            </div>
        </div>

        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">租房截至日期设置</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-4" style="margin-top: 8px">
                                输入租房截至日期：
                            </div>
                            <div class="col-sm-8">
                                <input type="date" id="finishTime" class="form-control form-control-sm">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                        <button type="button" id="receive" @click="buyTap" class="btn btn-primary">发布</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="layui-row">
            <div class="layui-tab layui-tab-card">

                <ul class="layui-tab-title">

                    <li class="layui-this">详情</li>
                    <li>评价</li>
                </ul>

                <div class="layui-tab-content">

                    <div class="layui-tab-item layui-show">
                        <div v-html="detail.noticeContent"></div>
                    </div>


                    <div class="layui-tab-item">
                        <div class="message-container">
                            <form class="layui-form message-form" style="padding-right: 20px;border-bottom: 0;">
                                <div class="layui-form-item layui-form-text">
                                    <label class="layui-form-label"></label>
                                    <div class="layui-input-block">
                                        <textarea name="replycontent" required lay-verify="required" placeholder="请输入内容"
                                                  class="layui-textarea"></textarea>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="lay ui-input-block">
                                        <button class="layui-btn btn-submit" lay-submit lay-filter="*">立即提交</button>
                                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                                    </div>
                                </div>
                            </form>
                            <div class="message-list">
                                <div class="message-item" v-for="(item,index) in dataList" v-bind:key="index">
                                    <div class="username-container">
                                        <img class="avator" :src="item.imgPhoto">
                                        <span class="username">用户：{{item.name}}</span>
                                    </div>
                                    <div class="content">
                                            <span class="message">
                                                {{item.replycontent}}
                                            </span>
                                    </div>
                                </div>
                            </div>
                            <div class="pager" id="pager"></div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
    </div>


</div>

<script src="../../layui/layui.js"></script>
<script src="../../js/vue.js"></script>
<!-- 组件配置信息 -->
<script src="../../js/config.js"></script>
<!-- 扩展插件配置信息 -->
<script src="../../modules/config.js"></script>
<!-- 工具方法 -->
<script src="../../js/utils.js"></script>
<!-- 校验格式工具类 -->
<script src="../../js/validate.js"></script>
<!-- 地图 -->
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=ca04cee7ac952691aa67a131e6f0cee0"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
<link href="../../../resources/css/bootstrap.min.css" rel="stylesheet">



<script>
    var vue = new Vue({
        el: '#app',
        data: {
            //外表数据
            fwlxTypesSelectSearch: [],
            fwstateTypesSelectSearch: [],
            fdTypesSelectSearch: [],
            // 轮播图
            swiperList: [],
            // 数据详情
            detail: {
                id: 0
            },
            // 商品标题
            title: '',
            // 倒计时
            count: 0,
            dialogVisible: false,
            // 加入购物车数量
            buynumber: 1,
            // 当前详情页表
            detailTable: 'fangwuxinxi',
            // 评价列表
            dataList: [],
            // 选座座位列表
            numberList: []
        },
        // 倒计时效果
        computed: {
            SecondToDate: function () {
                var time = this.count;
                if (null != time && "" != time) {
                    if (time > 60 && time < 60 * 60) {
                        time =
                            parseInt(time / 60.0) +
                            "分钟" +
                            parseInt((parseFloat(time / 60.0) - parseInt(time / 60.0)) * 60) +
                            "秒";
                    } else if (time >= 60 * 60 && time < 60 * 60 * 24) {
                        time =
                            parseInt(time / 3600.0) +
                            "小时" +
                            parseInt(
                                (parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60
                            ) +
                            "分钟" +
                            parseInt(
                                (parseFloat(
                                    (parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60
                                    ) -
                                    parseInt(
                                        (parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60
                                    )) *
                                60
                            ) +
                            "秒";
                    } else if (time >= 60 * 60 * 24) {
                        time =
                            parseInt(time / 3600.0 / 24) +
                            "天" +
                            parseInt(
                                (parseFloat(time / 3600.0 / 24) - parseInt(time / 3600.0 / 24)) *
                                24
                            ) +
                            "小时" +
                            parseInt(
                                (parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60
                            ) +
                            "分钟" +
                            parseInt(
                                (parseFloat(
                                    (parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60
                                    ) -
                                    parseInt(
                                        (parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60
                                    )) *
                                60
                            ) +
                            "秒";
                    } else {
                        time = parseInt(time) + "秒";
                    }
                }
                return time;
            }
        },
        //  清除定时器
        destroyed: function () {
            window.clearInterval(this.inter);
        },
        methods: {

            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                    done();
                })
                .catch(_ => {});
            },

            jump(url) {
                jump(url)
            },
            isAuth(tablename, button) {
                return isFrontAuth(tablename, button)
            },
            // 倒计时初始化
            countDown() {
                let reversetime = new Date(this.detail.reversetime).getTime()
                let now = new Date().getTime();
                let count = reversetime - now;
                if (count > 0) {
                    this.count = count / 1000
                    var _this = this;
                    this.inter = window.setInterval(function () {
                        _this.count = _this.count - 1;
                        if (_this.count < 0) {
                            window.clearInterval(_this.inter);
                            layer.msg("活动已结束", {
                                time: 2000,
                                icon: 5
                            })
                        }
                    }, 1000);
                }
            },

            // 立即租房
            buyTap() {
                // 商品信息
                console.log(vue.detail.id)
                debugger
                layui.http.requestJson(`${vue.detailTable}/renting?id=`+vue.detail.id +"&finishTime="+$("#finishTime").val(), 'post', {
                }, function (res) {
                    if (res && res.code === 0) {
                        alert("租房成功")
                        jump('../home/home.jsp');
                    } else {
                        this.$message.error(data.msg);
                    }
                    // 跳转到确认下单页面

                });


            },


        }
    })

    layui.use(['layer', 'form', 'element', 'carousel', 'http', 'jquery', 'laypage'], function () {
        var layer = layui.layer;
        var element = layui.element;
        var form = layui.form;
        var carousel = layui.carousel;
        var http = layui.http;
        var jquery = layui.jquery;
        var laypage = layui.laypage;

        var limit = 10;

        // 数据ID
        var id = http.getParam('id');
        vue.detail.id = id;

        http.request(
            `fangwuleixing/page`, 'get', {}, function (res) {
                if (res && res.code === 0) {
                    this.fwlxTypesSelectSearch = res.data.list;
                } else {
                    this.$message.error(data.msg);
                }
            });

        http.request(
            `dictionary/page`, 'get', {}, function (res) {
                if (res && res.code === 0) {
                    this.fwstateTypesSelectSearch = res.data.list;
                } else {
                    this.$message.error(data.msg);
                }
            });

        http.request(
            `fangdongxinxi/page`, 'get', {}, function (res) {
                if (res && res.code === 0) {
                    this.fdTypesSelectSearch = res.data.list;
                } else {
                    this.$message.error(data.msg);
                }
            });
        // 商品信息
        http.request(`${vue.detailTable}/detail/` + id, 'get', {}, function (res) {
            // 详情信息
            vue.detail = res.data
            vue.title = vue.detail.fwname;
            // 轮播图片
            vue.swiperList = vue.detail.imgPhoto ? vue.detail.imgPhoto.split(",") : [];
            var swiperItemHtml = '';
            for (let item of vue.swiperList) {
                swiperItemHtml +=
                    '<div>' +
                    '<img class="swiper-item" src="' + item + '">' +
                    '</div>';
            }

            var select = {};

            var fwlxTypesSelect = this.fwlxTypesSelectSearch;
            for (var i = 0; i < fwlxTypesSelect.length; i++) {
                if(fwlxTypesSelect[i].id == vue.detail.fwlxTypes){
                    vue.detail.fwlxTypes = fwlxTypesSelect[i].fwname;
                }
            }

            var fwstateTypesSelect = this.fwstateTypesSelectSearch;
            for (var i = 0; i < fwstateTypesSelect.length; i++) {
                if(fwstateTypesSelect[i].codeIndex == vue.detail.fwstateTypes){
                    vue.detail.fwstateTypes = fwstateTypesSelect[i].indexName;
                }
            }

            var fdTypesSelect = this.fdTypesSelectSearch;
            for (var i = 0; i < fdTypesSelect.length; i++) {
                if(fdTypesSelect[i].id == vue.detail.fdTypes){
                    vue.detail.fdTypes = fdTypesSelect[i].name;
                }
            }

            jquery('#swiper-item').html(swiperItemHtml);
            // 轮播图
            carousel.render({
                elem: '#swiper',
                width: swiper.width, height: swiper.height,
                arrow: swiper.arrow,
                anim: swiper.anim,
                interval: swiper.interval,
                indicator: swiper.indicator
            });


        });

        // 获取评论
        http.request(`discuss${vue.detailTable}/page`, 'get', {
            page: 1,
            limit: limit,
            refid: vue.detail.id,
            userid: localStorage.getItem('userid')
        }, function (res) {
            vue.dataList = res.data.list
            // 分页
            laypage.render({
                elem: 'pager',
                count: res.data.total,
                limit: limit,
                jump: function (obj, first) {
                    //首次不执行
                    if (!first) {
                        http.request(`discuss${vue.detailTable}/page`, 'get', {
                            page: obj.curr,
                            limit: obj.limit,
                            userid: localStorage.getItem('userid')
                        }, function (res) {
                            vue.dataList = res.data.list
                        })
                    }
                }
            });
        })

        // 提交评论
        form.on('submit(*)', function (data) {
            debugger
            data = data.field;
            data.userid = localStorage.getItem('userid')
            data.refid = vue.detail.id
            http.requestJson(`discuss${vue.detailTable}/save`, 'post', data, function (res) {
                layer.msg('评价成功', {
                    time: 2000,
                    icon: 6
                }, function () {
                    window.location.reload();
                });
                return false
            });
            return false
        }); // 提交评论
        form.on('submit(*)', function (data) {
            data = data.field;
            data.userid = localStorage.getItem('userid')
            data.refid = vue.detail.id
            http.requestJson(`discuss${vue.detailTable}/save`, 'post', data, function (res) {
                layer.msg('评价成功', {
                    time: 2000,
                    icon: 6
                }, function () {
                    window.location.reload();
                });
                return false
            });
            return false
        });

    });
</script>
</body>
</html>

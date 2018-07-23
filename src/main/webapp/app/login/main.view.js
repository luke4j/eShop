define(function(require, exports, module) {
    require("md5") ;
    require("J") ;
    require("backbone") ;
    require("bootstrap") ;
    var MenuView = require("app/login/menu.view") ;
    var MainView = Backbone.View.extend({
        el: $("body"),
        events: {
            /**导航条，查看用户信息*/
            "click #a_mt_userInfo":"a_mt_userInfo_handler",
            /**导航条，修改密码*/
            "click #a_mt_updatePwd":"a_mt_updatePwd_handler",
            /**导航条，登出*/
            "click #a_mt_logout":"a_mt_logout_handler",
            /**导航条，用户信息查看*/
            "click #main_nav_user_info a":"main_nav_user_info__a_handler",
            /**返回主页*/
            "click #main_home_btn":'main_home_btn_handler',
            /**返回主页logo*/
            //"click #main_logo_btn":'main_logo_btn_handler',
        },
        initialize: function () {
            J.getJBody().html("<div id='_main' class='container' style='height:100%;'></div>") ;
            this.render() ;
        },
        render:function(){
            /**请求数据，数据放在全局变量LukeApp.info中*/
            this.page_login_getInfo_3() ;
//            /**添加导航*/
            this.render_add_nav() ;
            /**添加主要工作区*/
            $("#_main").append("<div class='container' id='wm_workspace' style='margin-top:60px;'>main</div>") ;
            /**添加时间*/
            this.render_add_time() ;
            $("#nav_current_user_name").text("欢迎："+LukeApp.tuken.name) ;
            if(LukeApp.info.role)
                $("#nav_current_user_role").text("角色："+LukeApp.info.role.name) ;
            if(LukeApp.tuken.comName)
                $("#nav_current_user_com").text("公司："+LukeApp.tuken.comName) ;
            if(LukeApp.tuken.storeName)
                $("#nav_current_user_store").text("站点："+LukeApp.tuken.storeName) ;
            /**导航条显示用户消息,这是*/
            this.page_login_user_msgs() ;

        },
        /**同步获取用户权限，用户消息，系统时间*/
        page_login_getInfo_3:function(){
            J.ajax({
                url:'login/getInfo.act',
                async:false,
                success:function(d){
                    LukeApp.info = d ;
                    console.log("=============LukeApp.info start==========================") ;
                    console.dir( LukeApp.info) ;
                    console.log("=============LukeApp.info end============================") ;
                }
            }) ;
        },

        /**添加主导航条*/
        render_add_nav:function(){
            var main_nav = J.htmlTemp('app/login/main.nav.html') ;
            $("#_main").append($(main_nav)) ;
        },
        /**添加时间*/
        render_add_time:function(){
            var _now = J.JavaTimeToJsTime(LukeApp.info.sysTime) ;
            $("#lmt_time").text(J.jsDateToStr(_now)) ;
            function outtime(){
                _now = new Date(_now.getTime()+1000) ;
                $("#lmt_time").text(J.jsDateToStr(_now)) ;
            } ;
            this.timeInterval = setInterval(outtime,1000) ;
        },

        /**返回主页logo*/
        main_logo_btn_handler:function(e){
            var workspace = $("#wm_workspace").html("") ;
            $("#wm_workspace").addClass("bg") ;
        },
        /**返回主页*/
        main_home_btn_handler:function(e){
            J.changeView(J._CurrentWorkSpaceView,"app/login/menu.view") ;
        },
        /**导航条显示用户消息*/
        page_login_user_msgs:function(){
            /**消息数理*/
            var jqMsgNum = $("#main_nav_user_info_num") ;
            jqMsgNum.text(LukeApp.info.msgs.length) ;
            var jqMsgUl = $("#main_nav_user_info") ;
            jqMsgUl.html("") ;
            for(var i in LukeApp.info.msgs){
                var msg = LukeApp.info.msgs[i] ;
                jqMsgUl.append("<li><a msgId='"+msg.id+"' msg='"+msg.msg+"'><span class='margin_right_5 glyphicon glyphicon-star-empty'>"+msg.title+"</span></a></li>") ;
            }
        },
        /**导航条，用户信息查看*/
        main_nav_user_info__a_handler:function(e){
            var jq_a = $(e.currentTarget) ;
            var msgId = $("<input />").attr('type','hidden').val(jq_a.attr('msgId')) ;
            var jform = J.jForm({fid:'_alert_hideForm',fname:'_alert_hideForm',els:[msgId]}) ;
            var alertCount = jq_a.attr('msg')+"<hr>"
                                //+ J.jsDateToStr(J.JavaTimeToJsTime(jq_a.attr('wt')) )
                                +jform.html();
            J.alert({
                title:jq_a.text(),
                //msg:jq_a.attr('msg')+"<hr>"+  J.jsDateToStr(J.JavaTimeToJsTime(jq_a.attr('wt')) )+"<input type='hidden' value='"+jq_a.attr('msgId')+"'>",
                msg:alertCount,
                btnsGroup:{
                    text:'已读',
                    handler:function(e,alt){
                        var msgId = $("input",alt).val() ;
                        J.ajax({
                            url:'login/update_readInfo',
                            data:{id:msgId},
                            success:function(data){
                                J.alert(data) ;
                            }
                        })
                    }
                }
            }) ;

        },
        /**头部导航条用户信息点击事件*/
        a_mt_userInfo_handler:function(e){
            var userinfo = J.htmlTemp('app/login/userInfo.temp.html') ;
            J.alert({
                title:"用户信息",
                msg:$(userinfo)
            }) ;
            J.ajax({
                url:'login/getUserInfo.act',
                success:function(d){
                    for(var n in d){
                        /**定义的页面元素名与返回数据后台类的属性名有规则对应关系*/
                        $("#user_"+n).html(d[n]) ;
                    }
                }
            }) ;
        },

        /**头部导航条修改密码点击事件*/
        a_mt_updatePwd_handler:function(e){
            var fpwd = J.htmlTemp('app/login/userPassword.temp.html') ;
            J.alert({
                title:'修改密码',
                btns:'YN',
                msg:$(fpwd),
                okFunction:function(e,alt){
                    var formData = {
                        id: $("#user_id",alt).val(),
                        password:$("#user_password",alt).val(),
                        password2:$("#user_password2",alt).val()
                    } ;
                    var isValid = J.validate(formData,{
                        id:[{null_able:false,msg:'用户异常'}],
                        password:[{null_able:false,msg:'密码不能为空'}],
                        password2:[{null_able:false,msg:'重复密码不能为空'},
                            {equals:function(){
                                if($("#user_password",alt).val()!=$("#user_password2",alt).val()){
                                    return false ;
                                }else{
                                    return true ;
                                }
                            },msg:'两次密码不相同'}]
                    }) ;
                    if(isValid){
                        formData.password = J.md5(formData.password) ;
                        formData.password2 = J.md5(formData.password2) ;
                        J.ajax({url:'login/editPassword',isMsg:true,data:formData}) ;
                    }
                }
            }) ;

            $("#user_name").text(LukeApp.tuken.name) ;
            $("#user_loginName").text(LukeApp.tuken.loginName) ;
        },
        /**头部导航退出登录事件*/
        a_mt_logout_handler:function(e){
            var me = this ;
            J.ajax({
                url:'login/logout',
                success:function(data){
                    J.alert(data) ;
                    J.changeView(me,'app/login/login.view') ;
                    if(me.msgInterval){
                        clearInterval(me.msgInterval) ;
                    }
                    if(me.timeInterval){
                        clearInterval(me.timeInterval) ;
                    }
                }
            }) ;
        }
    });
    return MainView ;
}) ;
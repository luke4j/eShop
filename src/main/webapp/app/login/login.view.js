define(function(require, exports, module) {
    require("J") ;
    require("md5") ;
    require("backbone") ;
    require("bootstrap") ;
    var main_view = require("app/login/main.view") ;
    var LoginView = Backbone.View.extend({
        el: $("body"),
        initialize: function () {
            this.render() ;
        },
        render:function(){
            var login_html = J.htmlTemp("app/login/login.tmp.html") ;
            $("body").html(login_html) ;
            $("#_app_name").text(_app_name) ;
            $("#com").focus() ;
            /**添加公司选项*/
            this.render_com() ;
        },
        render_com:function(){
            /**
             * div id is div_com ;
             * 公司id is com
             */
            J.ajax({
                url:'login/findCom.act',
                success:function(result){
                    var $com = $("#com") ;
                    $com.html("") ;
                    $com.append($("<option>").text('').val('')) ;
                    for(var i in result){
                        $com.append($("<option>").text(result[i].name).val(result[i].id)) ;
                    }
                }
            }) ;
        },
        events: {
            /**登录事件*/
            "click #btn_login":"btn_login_handler",
            "keypress #password":"keypress_handler"
        },
        keypress_handler:function(e){
            if(e.keyCode==13){
                $("#btn_login").click() ;
            }
        },
        /**登录事件*/
        btn_login_handler:function(e){
            var me = this ;
            var fv = {} ;
            fv.loginName = $("#loginName").val() ;
            fv.password =$("#password").val() ;
            fv.comId = $("#com").val() ;
            var valid = J.validate(fv,{
                loginName:[{null_able:false,msg:'登录名不能为空'}],
                password:[{null_able:false,msg:'密码不能为空'}]
            }) ;
            if(valid){
                fv.password =  J.md5(fv.password) ;
                J.ajax({
                    url:'login/login',
                    data:fv,
                    success:function(data){
                        window.LukeApp = {} ;
                        LukeApp.tuken = data ;
                        console.log("=============LukeApp.tuken start==========================") ;
                        console.dir( LukeApp.tuken) ;
                        console.log("=============LukeApp.tuken end============================") ;
                        J.changeView(me,"app/login/main.view") ;
                    }
                }) ;
            }
        }
    });
    return LoginView ;
}) ;
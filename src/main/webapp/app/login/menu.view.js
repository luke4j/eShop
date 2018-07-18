define(function(require, exports, module) {
    require("J") ;
    require("backbone") ;
    require("bootstrap") ;
    var View = Backbone.View.extend({
        el: $("#wm_workspace"),
        events: {
            //"click #a_mt_userInfo":"a_mt_userInfo_handler"
        },
        initialize: function () {
        debugger ;
            this.render() ;
        },
        render:function(){
            var $me = this ;
            LukeApp.jqWorkSpace = $("#wm_workspace") ;
            var menus = $("<div />").addClass('container-fluid').attr("id","main_menu");
            var $groupRow ;

            for(var i in LukeApp.Role){

                var fun = LukeApp.Role[i] ;
                if(fun.fid == 0){
                    $groupRow = $("<div >").addClass("row") ;
                    menus.append($groupRow) ;
                    continue ;
                }
                if(!fun.viewPath) continue ;
                var item = menu_item = J.htmlTemp("app/login/menu_item.tmp.html") ;
                item = $(item) ;

                item.attr("viewPath",fun.viewPath).attr("viewName",fun.name) ;
                item.on('click',function(e){
                    requirejs([$(e.currentTarget).attr('viewPath')],function(VClass){
                        $me.undelegateEvents() ;
                        $("#main_menu").remove() ;
                        var viewPath = $(e.currentTarget).attr('viewPath') ;
                        J.changeView($me,viewPath) ;
                        $("#lmt_name").html("").append($("<span/>").addClass("glyphicon glyphicon-cloud")).append($(e.currentTarget).attr('viewName')) ;
                    }) ;
                }) ;
                if(fun.iconPath){
                    $("h3",item).html("") ;
                    $("h3",item).append($("<p>").addClass("glyphicon").addClass(fun.iconPath)).append(fun.name) ;
                }else{
                    $("h3",item).text(fun.name) ;
                }

                $groupRow.append(item) ;

            }
            LukeApp.jqWorkSpace.html("") ;
            LukeApp.jqWorkSpace.append(menus) ;
        }

    });
    return View ;
}) ;
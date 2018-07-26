var lens_help = {
    tabelOpt:{
        edit:false, //是否可以编辑
        clickCheck:true, //是否点击选择
        mouseSelect:true,// 是否鼠标划动选择
        _0_color:false //0轴颜色
    },
    /**生成度数表*/
    _id_btn_dsfw_setup_handler:function(){
        if($("#id_div_lensParantDiv").length>0){
            $("#id_div_lensParantDiv").remove() ;
        }
        var jme = this ;
        var sphMin,sphMax,sphPool,cylMin,cylMax,cylPool,xx,$main=$("#id_div_lensMain");
        sphMin = parseFloat($("#sphMin").val()) ;
        sphMax = parseFloat($("#sphMax").val()) ;
        sphPool = parseFloat($("#sphPool").val()) ;
        cylMin = parseFloat($("#cylMin").val()) ;
        cylPool = parseFloat($("#cylPool").val()) ;
        cylMax = parseFloat($("#cylMax").val()) ;
        if(isNaN(sphMin)||isNaN(sphMax)||isNaN(cylMin)||isNaN(cylMax)||isNaN(sphPool)||isNaN(cylPool)){
            J.alert("请正确配置参数") ;
            return false ;
        }
        xx = $("#id_xx").val() ;
        var $table = $("<table>").attr("id",'id_lensTable').attr('border',1);

        var $divLens = $("<div  id='id_div_lensParantDiv'>").addClass("col-xs-12 col-sm-12 col-md-12 col-lg-12").append(
            $("<div>").addClass("lensParantDiv").append($table).width($(window).width()-100).height(500)
        ) ;
        $main.append($divLens) ;

        switch (xx){
            case '--':
                lens_help.__id_btn_dsfw_setup_handler_ff(sphMin,sphMax,sphPool,cylMin,cylMax,sphPool) ;
                break ;
            case '+-':
                lens_help.__id_btn_dsfw_setup_handler_zf(sphMin,sphMax,sphPool,cylMin,cylMax,sphPool) ;
                break ;
            default :
                lens_help.__id_btn_dsfw_setup_handler_def(sphMin,sphMax,sphPool,cylMin,cylMax,sphPool) ;
                break ;
        }
        $table.FrozenTable(1,0,1);
        if(lens_help.tabelOpt._0_color){
            $("td[sph='0.00']").addClass("td_yellow") ;
            $("td[cyl='0.00']").addClass("td_yellow") ;
        }
        if(lens_help.tabelOpt){
            if(lens_help.tabelOpt.edit){
                lens_help.__tabelOpt_edit($table) ;
            }
            if(lens_help.tabelOpt.clickCheck){
                lens_help.__tabelOpt_clickCheck($table) ;
            }
            if(lens_help.tabelOpt.mouseSelect){
                lens_help.__tabelOpt_mouseSelect($table) ;
            }
        }
    },

    /**生成度数表  正负 */
    __id_btn_dsfw_setup_handler_zf:function(sphMin,sphMax,sphPool,cylMin,cylMax,cylPool){
        sphMin = sphMin - sphPool ;
        cylMax = cylMax+sphPool ;
        if(sphMin>sphMax||cylMin>cylMax){
            J.alert("请认真填写能数");
            return false ;
        }
        var $table = $("#id_lensTable") ;
        for(var sph = sphMin ;sph<=sphMax;sph+=cylPool){
            var $tr = $("<tr>").attr('align','center') ;
            for(var cyl = cylMax ;cyl>=cylMin;cyl-=cylPool){
                var $td = $("<td>").css({width:'100px',height:'35px'}).attr("noWrap","nowrap") ;
                $tr.append($td) ;
                if(sph==sphMin&&cyl==cylMax){
                    $td.css({'background-color':'yellow'}) ;
                    $td.text("球/柱")
                }else if(sph==sphMin||cyl==cylMax){
                    $td.css({'background-color':'blue'}) ;
                    if(sph==sphMin){
                        $td.text(J.showLens(cyl)) ;
                    }else if(cyl==cylMax){
                        $td.text(J.showLens(sph)) ;
                    }
                }else{
                    $td.attr('sph',J.showLens(sph)).attr('cyl',J.showLens(cyl)) ;
                }
            }
            $table.append($tr) ;
        }
    },
    /**生成度数表 负负*/
    __id_btn_dsfw_setup_handler_ff:function(sphMin,sphMax,sphPool,cylMin,cylMax,cylPool){
        if(sphMin>sphMax||cylMin>cylMax){
            J.alert("请认真填写能数");
            return false ;
        }
        sphMax = sphMax+sphPool ;
        cylMax = cylMax+sphPool ;
        var $table = $("#id_lensTable") ;
        for(var sph = sphMax ;sph>=sphMin;sph-=cylPool){
            var $tr = $("<tr>").attr('align','center') ;
            for(var cyl = sphMax ;cyl>=cylMin;cyl-=sphPool){
                var $td = $("<td>").css({width:'100px',height:'35px'}).attr("noWrap","nowrap") ;
                $tr.append($td) ;
                if(sph==sphMax&&cyl==sphMax){
                    $td.css({'background-color':'yellow'}) ;
                    $td.text("球/柱")
                }else if(sph==sphMax||cyl==sphMax){
                    $td.css({'background-color':'blue'}) ;
                    if(sph==sphMax){
                        $td.text(J.showLens(cyl)) ;
                    }else if(cyl==sphMax){
                        $td.text(J.showLens(sph)) ;
                    }
                }else{
                    $td.attr('sph',J.showLens(sph)).attr('cyl',J.showLens(cyl)) ;
                }
            }
            $table.append($tr) ;
        }
    },
    /**生成度数表 默认*/
    __id_btn_dsfw_setup_handler_def:function(sphMin,sphMax,sphPool,cylMin,cylMax,cylPool){
        sphMin = sphMin - sphPool ;
        cylMin = cylMin - cylPool ;
        if(sphMin>sphMax||cylMin>cylMax){
            J.alert("请认真填写能数");
            return false ;
        }
        var $table = $("#id_lensTable") ;
        for(var sph = sphMin ;sph<=sphMax;sph+=cylPool){
            var $tr = $("<tr>").attr('align','center') ;
            for(var cyl = cylMin ;cyl<=cylMax;cyl+=sphPool){
                var $td = $("<td>").css({width:'100px',height:'35px'}).attr("noWrap","nowrap") ;
                $tr.append($td) ;
                if(sph==sphMin&&cyl==cylMin){
                    $td.css({'background-color':'yellow'}) ;
                    $td.text("球/柱")
                }else if(sph==sphMin||cyl==cylMin){
                    $td.css({'background-color':'blue'}) ;
                    if(sph==sphMin){
                        $td.text(J.showLens(cyl)) ;
                    }else if(cyl==cylMin){
                        $td.text(J.showLens(sph)) ;
                    }
                }else{
                    $td.attr('sph',J.showLens(sph)).attr('cyl',J.showLens(cyl)) ;
                }
            }
            $table.append($tr) ;
        }
    },

    __tabelOpt_mouseSelect:function($table){
        var start = {},end={} ;
        $("td[sph][cyl]",$table).mousedown(function(e){
            start.sph = $(e.currentTarget).attr('sph') ;
            start.cyl = $(e.currentTarget).attr('cyl') ;
        }).mouseup(function(e){
            end.sph = $(e.currentTarget).attr('sph') ;
            end.cyl = $(e.currentTarget).attr('cyl') ;

            var startSph,startCyl,endSph,endCyl,sphPool = parseFloat($("#sphPool").val()),cylPool= parseFloat($("#cylPool").val());
            startSph = parseFloat(start.sph) ;
            startCyl = parseFloat(start.cyl) ;
            endSph = parseFloat(end.sph) ;
            endCyl = parseFloat(end.cyl) ;
            //右下到左上滑
            if(startSph>=endSph&&startCyl>=endCyl){
                for(var i = endSph ;i<=startSph;i+=sphPool){
                    for(var j = endCyl ;j<=startCyl;j+=cylPool){
                        $("td[sph='"+ J.showLens(i)+"'][cyl='"+ J.showLens(j)+"']").each(function(i,td){
                            var $td = $(td) ;
                            var sph = parseFloat($td.attr('sph')) ;
                            var cyl = parseFloat($td.attr('cyl')) ;
                            if(sph<0&&cyl>0) return true ;
                            if($td.hasClass('td_red')){
                                $td.removeClass('td_red') ;
                            }else{
                                $td.addClass('td_red') ;
                            }
                        });
                    }
                }
            }
            //左上到右下
            if(startSph<=endSph&&startCyl<=endCyl){
                for(var i = startSph ;i<=endSph;i+=sphPool){
                    for(var j = startCyl ;j<=endCyl;j+=cylPool){
                        $("td[sph='"+ J.showLens(i)+"'][cyl='"+ J.showLens(j)+"']").each(function(i,td){
                            var $td = $(td) ;
                            var sph = parseFloat($td.attr('sph')) ;
                            var cyl = parseFloat($td.attr('cyl')) ;
                            if(sph<0&&cyl>0) return true ;
                            if($td.hasClass('td_red')){
                                $td.removeClass('td_red') ;
                            }else{
                                $td.addClass('td_red') ;
                            }
                        });
                    }
                }
            }
            //左下到右上
            if(startSph>=endSph&&startCyl<=endCyl){
                for(var i = endSph ;i<=startSph;i+=sphPool){
                    for(var j = startCyl ;j<=endCyl;j+=cylPool){
                        $("td[sph='"+ J.showLens(i)+"'][cyl='"+ J.showLens(j)+"']").each(function(i,td){
                            var $td = $(td) ;
                            var sph = parseFloat($td.attr('sph')) ;
                            var cyl = parseFloat($td.attr('cyl')) ;
                            if(sph<0&&cyl>0) return true ;
                            if($td.hasClass('td_red')){
                                $td.removeClass('td_red') ;
                            }else{
                                $td.addClass('td_red') ;
                            }
                        });
                    }
                }
            }
            //右上到左下
            if(startSph<=endSph&&startCyl>=endCyl){
                for(var i = startSph ;i<=endSph;i+=sphPool){
                    for(var j = endCyl ;j<=startCyl;j+=cylPool){
                        $("td[sph='"+ J.showLens(i)+"'][cyl='"+ J.showLens(j)+"']").each(function(i,td){
                            var $td = $(td) ;
                            var sph = parseFloat($td.attr('sph')) ;
                            var cyl = parseFloat($td.attr('cyl')) ;
                            if(sph<0&&cyl>0) return true ;
                            if($td.hasClass('td_red')){
                                $td.removeClass('td_red') ;
                            }else{
                                $td.addClass('td_red') ;
                            }
                        });
                    }
                }
            }
            start = {} ;
            end = {} ;
        }) ;
    },
    __tabelOpt_clickCheck:function($table){
        $("td[sph][cyl]",$table).on('click',function(e){
            $td = $(e.currentTarget) ;
            var sph = $td.attr('sph'),cyl = $td.attr('cyl') ;
            if(parseFloat(sph)<0&&parseFloat(cyl)>0){
                return false ;
            }
            if($td.hasClass('td_red')){
                $td.removeClass('td_red') ;
            }else{
                $td.addClass('td_red') ;
            }
        }) ;
    },
    __tabelOpt_edit:function($table){
        $("td[sph][cyl]",$table).on('click',function(e){
            $td = $(e.currentTarget) ;
            $input = $("<input>").width($td.width()-5) ;
            $input.on('blur',function(ie){
                $td.attr("val",$(ie.currentTarget).val()) ;
                $td.empty() ;
                $td.text($td.attr('val')) ;
                return false ;
            }) ;
            $td.append($input) ;
            $input.focus() ;
            return false ;
        }) ;
    },
    /**球镜选择*/
    _id_btn_sph_slt_handler:function(e){
        var sph =  $("#slt_sph").val(),cylStart = $("#slt_cylMin").val(),cylEnd = $("#slt_cylMax").val(),cylPool = $("#sphPool").val();
        cylStart = parseFloat(cylStart) ;
        cylEnd = parseFloat(cylEnd) ;
        cylPool = parseFloat(cylPool) ;
        if(cylStart>cylEnd){
            J.alert("柱1应小于柱2") ;
            return false ;
        }
        for(var i = cylStart ;i<=cylEnd ;i+=cylPool){
            var $td =  $("td[sph='"+ J.showLens(sph)+"'][cyl='"+ J.showLens(i)+"']") ;
            var sph = parseFloat($td.attr('sph')) ;
            var cyl = parseFloat($td.attr('cyl')) ;
            if(sph<0&&cyl>0) continue ;
            if($td.hasClass('td_red')){
                $td.removeClass("td_red") ;
            }else{
                $td.addClass("td_red") ;
            }
        }
    },
    /**柱镜选择*/
    _id_btn_cyl_slt_handler:function(e){
        var cyl =  $("#slt_cyl").val(),sphStart = $("#slt_sphMin").val(),sphEnd = $("#slt_sphMax").val(),sphPool = $("#sphPool").val();
        sphStart = parseFloat(sphStart) ;
        sphEnd = parseFloat(sphEnd) ;
        sphPool = parseFloat(sphPool) ;
        if(sphStart>sphEnd){
            J.alert("球1应小于球2") ;
            return false ;
        }
        for(var i = sphStart ;i<=sphEnd ;i+=sphPool){
            var $td =  $("td[cyl='"+ J.showLens(cyl)+"'][sph='"+ J.showLens(i)+"']") ;
            var sph = parseFloat($td.attr('sph')) ;
            var cyl = parseFloat($td.attr('cyl')) ;
            if(sph<0&&cyl>0) continue ;
            if($td.hasClass('td_red')){
                $td.removeClass("td_red") ;
            }else{
                $td.addClass("td_red") ;
            }
        }
    },
    _id_btn_fan_xuan_handler:function(e){
        $("td[sph][cyl]").each(function(i,ele){
            var sph = parseFloat($(ele).attr('sph')) ;
            var cyl = parseFloat($(ele).attr('cyl')) ;
            if(sph<0&&cyl>0) return true ;
            if($(ele).hasClass("td_red")){
                $(ele).removeClass("td_red") ;
            }else{
                $(ele).addClass("td_red") ;
            }
        }) ;
    },
    _id_btn_qing_chu_handler:function(e){
        $("td[sph][cyl]").each(function(i,ele){
            if($(ele).hasClass("td_red")){
                $(ele).removeClass("td_red") ;
            }
        }) ;
    },
    _id_btn_save_handler:function(e){
        var values = J.formValues($("#id_lensBaseSetupGroupFrom")) ;
        var validate = J.validate(values,{
            goodsId:{null_able:false,msg:'商品没有保存成功'},
            sphMax:{null_able:false,msg:'最大球镜不能为空'},
            sphMin:{null_able:false,msg:'最小球镜不能为空'},
            sphPool:{null_able:false,msg:'球镜级差不能为空'},
            cylMax:{null_able:false,msg:'最大柱镜不能为空'},
            cylMin:{null_able:false,msg:'最小柱镜不能为空'},
            cylPool:{null_able:false,msg:'柱镜级差不能为空'}
        }) ;
        if(!validate){
            return false ;
        }
        var arrayLens = [] ;
        $("td[sph][cyl]").each(function(i,ele){
            if($(ele).hasClass("td_red")){
                arrayLens.push({sph:$(ele).attr("sph"),cyl:$(ele).attr("cyl")}) ;
            }
        }) ;

        var param = $.extend({},values,{lens:JSON.stringify(arrayLens)})
        J.ajax({
            url:'goods/save_goods_lens',
            data:param,
            timeout:150000,
            success:function(d){
                J.setFormValue($("#id_lensBaseSetupGroupFrom"), d.goodsLensSetup) ;
                $("#goodsId").val(d.goodsLensSetup.goodsId) ;
                $("td[sph][cyl]").each(function(i,ele){
                    $(ele).removeClass("td_red") ;
                }) ;
                $.each(d.goodsLens,function(i,t){
                    $("td[sph='"+ J.showLens(t.sph)+"'][cyl='"+ J.showLens(t.cyl)+"']").addClass("td_red")  ;
                }) ;
                J.alert("度数配置成功") ;
            }
        }) ;
    },
    _up_down_event_handler:function(e){
        var $ele = $(e.currentTarget) ;
        $ele.attr('autocomplete','off') ;
        var val = $ele.val() ;
        val = parseFloat(val)||0 ;
        if(e.keyCode==38){//上
            val+= 0.25 ;
            $ele.val(J.showLens(val)) ;
            $ele.focus() ;
        }else if(e.keyCode==40){//下
            val-= 0.25 ;
            $ele.val(J.showLens(val)) ;
            $ele.focus() ;
        }

    },
    _id_btn_show_setup_handler:function(e){
        $("#lens_menu_row1").toggle("fast") ;
    },
    _id_btn_show_select_handler:function(e){
        $("#lens_menu_row2").toggle("fast") ;
        $("#lens_menu_row3").toggle("fast") ;
    },
    /**度数配置功能栏*/
    _tbar :function($main,goodsId){
        var row0 = $("<div class='row' id='lens_menu_row0'>").css({'margin-right':'15px','margin-top':'3px'}) ;
        var row1 = $("<div class='row' id='lens_menu_row1'>").css({'margin-right':'15px','margin-top':'3px'}) ;
        var row2 = $("<div class='row' id='lens_menu_row2'>").css({'margin-right':'15px','margin-top':'3px'}) ;
        var row3 = $("<div class='row' id='lens_menu_row3'>").css({'margin-right':'15px','margin-top':'3px'}) ;
        var row4 = $("<div class='row' id='lens_menu_row4'>").css({'margin-right':'15px','margin-top':'3px'}) ;
        var row5 = $("<div class='row' id='lens_menu_row5'>").css({'margin-right':'15px','margin-top':'3px'}) ;

        row0.append($("<div class='col-xs-12 col-sm-6 col-md-2 col-lg-2'>").append(J.formElement({id:'id_btn_show_setup',name:'id_btn_show_setup',text:'设置',type:'btn'})))
            .append($("<div class='col-xs-12 col-sm-6 col-md-2 col-lg-2'>").append(J.formElement({id:'id_btn_show_select',name:'id_btn_show_select',text:'选择',type:'btn'})))
            .append($("<div class='col-xs-12 col-sm-6 col-md-2 col-lg-2'>").append(J.formElement({id:'id_btn_save',name:'id_btn_save',text:'保存',type:'btn'})))
        ;

        var jFrom = J.createForm('id_lensBaseSetupGroupFrom','form-inline') ;
        jFrom.fieldset.append(J.formElement({id:'sphMax',name:'sphMax',text:'最大球镜'}))
            .append(J.formElement({id:'sphMin',name:'sphMin',text:'最小球镜'}))
            .append(J.formElement({id:'sphPool',name:'sphPool',text:'球镜级差',type:'select',options: J.SelectOptions('级差')}))
            .append(J.formElement({id:'cylMax',name:'cylMax',text:'最大柱镜'}))
            .append(J.formElement({id:'cylMin',name:'cylMin',text:'最小柱镜'}))
            .append(J.formElement({id:'cylPool',name:'cylPool',text:'柱镜级差',type:'select',options: J.SelectOptions('级差')}))
            .append(J.formElement({id:'id_xx',name:'id_xx',text:'象限',type:'select',options: J.SelectOptions('象限')}))
            .append(J.formElement({id:'goodsId',name:'goodsId',text:'商品ID',type:'hidden'}))
            .append($("<div class='col-xs-12 col-sm-6 col-md-2 col-lg-2'>").append(J.formElement({id:'id_btn_dsfw_setup',name:'id_btn_dsfw_setup',text:'配置',type:'btn'})))
        ;
        $(".form-group",jFrom.form).each(function(i,j){
            $(j).addClass("col-xs-12 col-sm-6 col-md-4 col-lg-4 margin-top_2") ;
        }) ;

        row1.append(jFrom.form) ;

        var jFrom2 = J.createForm('id_lensSelectGroupFrom','form-inline') ;
        jFrom2.fieldset.append(J.formElement({id:'slt_sph',name:'slt_sph',text:'球'}))
            .append(J.formElement({id:'slt_cylMin',name:'slt_cylMin',text:'柱1'}))
            .append(J.formElement({id:'slt_cylMax',name:'slt_cylMax',text:'柱2'}))
            .append($("<div class='col-xs-12 col-sm-6 col-md-2 col-lg-3'>").append(J.formElement({id:'id_btn_sph_slt',name:'id_btn_sph_slt',text:'选择',type:'btn'})))
            .append(J.formElement({id:'slt_cyl',name:'slt_cyl',text:'柱'}))
            .append(J.formElement({id:'slt_sphMin',name:'slt_sphMin',text:'球1'}))
            .append(J.formElement({id:'slt_sphMax',name:'slt_sphMax',text:'球2'}))
            .append($("<div class='col-xs-12 col-sm-6 col-md-2 col-lg-3'>").append(J.formElement({id:'id_btn_cyl_slt',name:'id_btn_cyl_slt',text:'选择',type:'btn'})))
        ;
        $(".form-group",jFrom2.form).each(function(i,j){
            $(j).addClass("col-xs-12 col-sm-6 col-md-3 col-lg-3") ;
        }) ;
        row2.append(jFrom2.form) ;

        row3.append($("<div class='col-xs-12 col-sm-6 col-md-2 col-lg-2'>").append(J.formElement({id:'id_btn_fan_xuan',name:'id_btn_fan_xuan',text:'反选',type:'btn'})))
            .append($("<div class='col-xs-12 col-sm-6 col-md-2 col-lg-2'>").append(J.formElement({id:'id_btn_qing_chu',name:'id_btn_qing_chu',text:'清除',type:'btn'}))) ;
            //.append($("<div class='col-xs-12 col-sm-6 col-md-2 col-lg-2'>").append(J.formElement({id:'id_btn_save',name:'id_btn_save',text:'保存',type:'btn'}))) ;

        $main.append(row0).append(row1).append(row2).append(row3).append(row4).append(row5) ;
    },
    /**显示度数配置弹出窗*/
    showLensWindow:function(goodsId,option){
        if(option){
            $.extend(lens_help.tabelOpt,option)
        }
        var $main = $("<div id='id_div_lensMain'>").addClass("container-fluid row") ;
        lens_help._tbar($main,goodsId) ;
        requirejs(['lensTable'],function(){
            var $alert = J.alert({
                title:'度数配置',
                msg:$main
            }) ;
            $(".modal-dialog",$alert).css({'text-align':'center','width':'auto'}) ;
            $("#lens_menu_row1").hide() ;
            $("#lens_menu_row2").hide() ;
            $("#lens_menu_row3").hide() ;
            $("#lens_menu_row4").hide() ;
            $("#lens_menu_row5").hide() ;

            /**显示配置*/
            $("#id_btn_show_setup").on("click",lens_help._id_btn_show_setup_handler) ;
            /**显示选择*/
            $("#id_btn_show_select").on("click",lens_help._id_btn_show_select_handler) ;

            /**度数范围生成*/
            $("#id_btn_dsfw_setup").on("click",lens_help._id_btn_dsfw_setup_handler) ;
            /**球镜选择*/
            $("#id_btn_sph_slt").on("click",lens_help._id_btn_sph_slt_handler) ;
            /**柱镜选择*/
            $("#id_btn_cyl_slt").on("click",lens_help._id_btn_cyl_slt_handler) ;
            /**全选*/
            $("#id_btn_fan_xuan").on("click",lens_help._id_btn_fan_xuan_handler) ;
            /**清除*/
            $("#id_btn_qing_chu").on("click",lens_help._id_btn_qing_chu_handler) ;
            /**保存*/
            $("#id_btn_save").on("click",lens_help._id_btn_save_handler) ;

            $("#sphMax").keydown(lens_help._up_down_event_handler) ;
            $("#sphMin").keydown(lens_help._up_down_event_handler) ;
            $("#cylMax").keydown(lens_help._up_down_event_handler) ;
            $("#cylMin").keydown(lens_help._up_down_event_handler) ;
            $("#slt_sph").keydown(lens_help._up_down_event_handler) ;
            $("#slt_cylMin").keydown(lens_help._up_down_event_handler) ;
            $("#slt_cylMax").keydown(lens_help._up_down_event_handler) ;
            $("#slt_cyl").keydown(lens_help._up_down_event_handler) ;
            $("#slt_sphMin").keydown(lens_help._up_down_event_handler) ;
            $("#slt_sphMax").keydown(lens_help._up_down_event_handler) ;


            var setupValue = {} ;
            J.ajax({
                url:'goods/get_goods_lens',
                async:false,
                data:{id:goodsId},
                success:function(d){
                    setupValue = d ;
                }
            }) ;
            $("#goodsId").val(goodsId) ;
            if(setupValue.goodsLensSetup){
                setupValue.goodsLensSetup.sphPool = J.showLens( setupValue.goodsLensSetup.sphPool) ;
                setupValue.goodsLensSetup.cylPool = J.showLens( setupValue.goodsLensSetup.cylPool) ;
                for(var i in setupValue.goodsLens){
                    setupValue.goodsLens[i].sph = J.showLens(setupValue.goodsLens[i].sph) ;
                    setupValue.goodsLens[i].cyl = J.showLens(setupValue.goodsLens[i].cyl) ;
                }
                J.setFormValue($("#id_lensBaseSetupGroupFrom"),setupValue.goodsLensSetup) ;
                $("#id_btn_dsfw_setup").click() ;
                lens_help._lensGoodsSelect(setupValue.goodsLens) ;
            }
        }) ;
    },
    _lensGoodsSelect:function(goodslens){
        $.each(goodslens,function(i,d){
            $("td[sph='"+ J.showLens(d.sph)+"'][cyl='"+ J.showLens(d.cyl)+"']").addClass("td_red").attr('lensId', d.id) ;
        }) ;
    }
} ;
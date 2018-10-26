
var J = J||{} ;
/**是否正式运行*/
J._run = false ;
J.contextPath = contextPath|| J.error("没有contextPath设置") ;
J._CurrentWorkSpaceView = null ;
J.$WorkSpace = function(){
    return $("#wm_workspace") ;
} ;

J.emptyFun = function(){console.dir(arguments)} ;
/**
 * 统一的提示{
 *msg:'提示内容',title:'提示标题',btns:'显示按钮Y/YN（默认只有Y）',closeFunction:'关闭弹出窗后执行方法（可选）',okFunction:'确定按钮执行方法（可选）'
 * }
 * @param msg
 */
J.Debug = false ;
J.debugPrint = function(obj){
    console.dir(obj) ;
} ;
J.objIsNull = function(obj){
    return (obj)?false:true ;
};
/**
 * cfg string or object
 * object cfg{
 *btns: 'Y' or 'YN'
 *okFunction:function(e,alert){}
 * }
 *例：
 * J.alert({
                    title:'修改'+d.text+'品类',
                    msg:$Element,
                    btns:'YN',
                    okFunction:function(e,alert){
                       //code...
                    }
                }) ;
 * @param cfg
 */

J.alert = function(cfg){
    var alt ;
    /**没有参数时，提示异常*/
    if(J.objIsNull(cfg)) J.error({msg:"请为J.alert函数添加json对象参数<br>" +
    "<br>{" +
    "\t<br>msg:'提示内容'," +
    "\t<br>title:'提示标题'," +
    "\t<br>btns:'显示按钮Y/YN（默认只有Y）'," +
    "\t<br>closeFunction:'关闭弹出窗后执行方法（可选）'" +
    "\t<br>okFunction:'确定按钮执行方法（可选）'" +
    "<br>}",title:'Code Exception !!!'}) ;
    /**参数如果是string类型，写为默认参数*/
    if(typeof(cfg)=='string'){
        var msg = cfg ;
        cfg = {} ;
        cfg.msg = msg ;
        cfg.title = "提示" ;
        cfg.btns = "Y" ;
    }
    /**参数是json对象类型，给没有的属性设置默认值 */
    if(typeof (cfg)=='object'){
        cfg.btns = cfg.btns?cfg.btns:"Y" ;
        cfg.title = cfg.title?cfg.title:"注意" ;
        cfg.msg = cfg.msg?cfg.msg:'你在玩我嘛？！！' ;
        cfg.closeFunction = cfg.closeFunction?cfg.closeFunction:function(){} ;
        cfg.okFunction = cfg.okFunction?cfg.okFunction:function(){} ;
        //cfg.btnsGroup =  cfg.btnsGroup? cfg.btnsGroup:{} ;
    }
    if(J.alterTemp==null){
        alt = J.alterTemp = J.htmlTemp('app/common/alert.temp.html') ;
    }else{
        alt = J.alterTemp ;
    }
    //var alt = J.htmlTemp('app/common/alert.temp.html') ;
    alt = $(alt) ;
    $(".modal-title",alt).text(cfg.title) ;
    $(".modal-body",alt).html(cfg.msg) ;

    $("body").append($(alt)) ;
    alt.modal({backdrop: 'static'}) ;

    /**只有确定时*/
    if(cfg.btns=="Y"){
        $("#_alertOkBtn",alt).remove() ;
    }else if(cfg.btns=="YN"){
        if(typeof (cfg.okFunction)=='function'){
            $("#_alertOkBtn",alt).on("click",function(e){
                cfg.okFunction(e,alt) ;
            }) ;
        }
    }
    if(cfg.btnsGroup&&typeof (cfg.btnsGroup) == 'object'){
        if(J.objIsNull(cfg.btnsGroup.text)
            || J.objIsNull(cfg.btnsGroup.handler)
            ||typeof (cfg.btnsGroup.handler)!='function'){
            J.error("J.alert param btnsGroup type is {text:'string',handler(e,jq_alert)}") ;
        }
        var btn = $("<button>").text(cfg.btnsGroup.text).addClass("btn btn-primary").on("click",function(e){
            cfg.btnsGroup.handler(e,alt) ;
        }) ;
        $("#_alertBtnsDiv",alt).append(btn) ;
    }

    alt.on('hidden.bs.modal',function(e){
        if(typeof(cfg.closeFunction)=='function'){
            cfg.closeFunction(e,alt) ;
        }
        $(e.target).remove() ;
    }) ;
    alt.on('click',function(){return false ;}) ;
    return alt ;
} ;
J.error = function(msg){
    throw new Error(msg) ;
} ;
J.alertOk = function(){
    J.alert("操作成功") ;
} ;


/**
 * @param src       数据来源json
 * @param target    目标json
 * @param isAll     是否覆盖相同属性 ，默认为不覆盖
 * @returns {*}
 */
J.copyJson= function(src,target,isAll){
    isAll = isAll||false ;
    for(var p in src){
        /**当target中不存在src中的p属性，把src中的p属性复制到target中*/
        if(!target[p]){
            target[p] = src[p] ;
        }else {
            /**如果 target中存在src中的p属性，并且是全部复制时，把src中的p属性复制到target中*/
            if(isAll){
                target[p] = src[p] ;
            }
        }
    }
    return target ;
} ;
/**
 * 统一ajax方法
 * J.ajax({
                    url:'login/login',
                    ajaxDebugger:true,   请求成功打印console.dir(res) ;
                    ajaxOk:true,显示操作成功
                    data:fv,
                    success:function(a){
                        window.LukeApp = {} ;
                        LukeApp.User = a ;
                        J.changeView(me,"app/login/main.view") ;
                    }
                }) ;
 * @param settings
 */
J.ajax = function(settings){
    var param = {}, defaults = {
        cache:false,
        url: '',
        type: 'POST',
        dataType:'json',
        contentType: 'application/json', //很重要
        traditional: true,
        data: {j:Math.random()},
        error:function(XMLHttpRequest, textStatus,errorThrown){
            if(XMLHttpRequest.status===0&&XMLHttpRequest.readyState===0&&textStatus==='error'){
                J.alert("请求没有发出，请检查网络或服务器是否开启！！！") ;
            }else{
                J.alert("url is "+param.url+"\t"+XMLHttpRequest.status+":"+errorThrown) ;
            }
        },
        success: function(res, status, xhr) {
            if(!res.errorMsg){
                if(param.ajaxOk){
                    if(param.ajaxDebugger){
                        console.dir(res) ;
                    }
                    J.alertOk() ;
                }
            }else{
                console.dir(res) ;
                J.alert(res.errorMsg) ;
            }

        }
    } ;
    if(typeof settings  == "string"){
        param = $.extend(param,defaults) ;
        param.url = settings ;
    }
    if(typeof settings == "object"){
        param = $.extend(param,defaults,settings) ;
        /**处理Url*/
        if(!param.url){
            J.error("ajax url is null") ;
        }
        param.url= J.contextPath+param.url ;
        if(param.url.indexOf(".act")<0){
            param.url = param.url+".act" ;
        }
        /**处理成功返回方法*/
        if(settings.success&&typeof settings.success == "function"){
            //param.success = settings.success
            param.success = function(res, status, xhr){
                if(res.success){
                    if(param.ajaxDebugger){
                        console.dir(res) ;
                    }
                    settings.success(res.data,res) ;
                }else{
                    if(res.errorMsg.indexOf('请登录')>=0){
                        J.alert({
                            title:'登录提示',
                            msg:res.errorMsg,
                            btns:'YN',
                            okFunction:function(e,alert){
                                window.location.href = J.contextPath ;
                            }
                        }) ;
                    }else{
                        J.alert(res.doing+"</br>"+res.errorType+":</br>"+res.errorMsg) ; J.alert(res.doing+"</br>"+res.errorType+":</br>"+res.errorMsg) ;
                    }
                }
            }
        }
        /**处理参数*/
        param.data =  JSON.stringify($.extend({_jsession:Math.random()},param.data)) ;
    }
    $.ajax(param) ;
} ;

/**
 * 加载html模板
 * @param url
 */
J.htmlTemp = function(url){
    var param = {
        cache:false,
        url : J.contextPath+url ,
        data:{jsession:Math.random()},
        dataType : 'html' ,
        async : false,
        error:function(XMLHttpRequest, textStatus, errorThrown){
            if(XMLHttpRequest.status==404){
                J.alert("url is "+J.contextPath+url+"\t404找不到请求地址:"+errorThrown) ;
            }
            if(XMLHttpRequest.status==500){
                J.alert("url is "+J.contextPath+url+"\t服务器处理异常:") ;
            }
        },
        success : function(data, textStatus, jqXHR){
            return data ;
        }
    } ;
    var rt = $.ajax(param) ;
    return $(rt.responseText).html() ;
} ;

/**对象不为空*/
J.isNotNull = function(obj){
    return obj?true:false ;
} ;
/**字符串不为空*/
J.strIsNotEmpty = function(str){
    return (str||str!='')?true:false ;
} ;
/**返回度数格式字符串*/
J.toLensFormat = function(obj){
    var number ;
    if(typeof(obj)=='number'){
        number = obj ;
    }else if(typeof (obj)=='string'){
        number = parseFloat(obj) ;
    }else{
        J.error("toLensFormat方法只能转换字符串和数字类型")
    }
    if(number>0){
        return "+"+number.toFixed(2) ;
    }else if(number<0){
        return number.toFixed(2) ;
    }else{
        return 0 ;
    }
} ;
J.md5 = function(str){
    return hex_md5(str) ;
} ;
/**
 * js时间转字符串
 * @param data
 * @param format
 * @returns {*}
 */
J.jsDateToStr = function(data,format){
    format = format||"yyyy-MM-dd hh:mm:ss" ;
    var fm = {
        "M": data.getMonth() + 1,
        "d": data.getDate(),
        "h": data.getHours(),
        "m": data.getMinutes(),
        "s": data.getSeconds(),
        "y":data.getFullYear(),
        "S": data.getMilliseconds()
    },rt;
    if(format == "yyyy-MM-dd") {
        rt = fm.y + "-" + fm.M + "-" + fm.d;
    }else if(format == "yyyy-MM-dd hh:mm:ss"){
        rt = fm.y + "-" + fm.M + "-" + fm.d+" "+fm.h+":"+fm.m+":"+fm.s ;
    }else{
        J.error("还没有写此时间格式，请在jstool.js的 J.jsDateToStr方法中添加实现")
    }
    return rt ;
} ;
/**后台时间返回长整形，所以这里再换成时间就要新new 一个*/
J.JavaTimeToJsTime = function(obj){
    obj = parseInt(obj) ;
    return new Date(obj) ;
} ;

/**json数据验证
 *
 var fv = {} ;
 fv.loginName = $("#loginName").val() ;
 fv.password =$("#password").val() ;

 var valid = J.validate(fv,{
                loginName:[{null_able:false,msg:'登录名不能为空'}],
                password:[{null_able:false,msg:'密码不能为空'}]
            }) ;
 * */
J.validate = function(data,validate){
    for(var dataAttr in validate){
        var dataValue = data[dataAttr] ;
        var valid = validate[dataAttr] ;
        for(var v in valid){
            if(valid.null_able==false){
                if(!J.strIsNotEmpty(dataValue)) {
                    J.alert(valid.msg) ;
                    return false ;
                }
            }
            if(valid.equals&&!valid.equals()){
                J.alert(valid.msg) ;
                return false ;
            }
            // 还可以再补充其它的验证
        }
    }
    return true ;
} ;
/**所有视图都是以body为el,所以有时会删除body,可以用这个方法来得到body元素*/
J.getJBody = function(){
    if($("body").length==0){
        $("html").append($("<body>")) ;
    }
    return $("body") ;
} ;
/**切换视图，解决事件多次注册问题*/
J.changeView = function(view,url){
    var args = arguments ;
    if(view){
        view.undelegateEvents() ;//处理事件重复加载问题
    }
    try{
        requirejs([url],function(Class){
            //$("#wm_workspace").fadeOut("fast") ;
            $("#wm_workspace").empty() ;
            new Class(args) ;
            //$("#wm_workspace").fadeIn("fast") ;

        });
    }catch(e){
        console.dir(e) ;
       J.alert("请检查文件："+url) ;
    }
} ;
/**
 *
 * @param cfg{
 *      fname:'form.name'
 *      fid:'form.id'
 *      els:[jHtmlElement]
 * }
 */
J.jForm = function(cfg){
     var jform = $("<form>").attr('name',cfg.fname).attr("id",cfg.fid) ;
    for(el in cfg.els){
        jform.append(cfg.els[el]) ;
    }
    jform.hide() ;
    return jform ;
} ;
/**
 * @param bootstrapTableSetup{url:'',columns:[{field:'',title:'',visible:''...}]}
 * http://bootstrap-table.wenzhixin.net.cn/documentation/
 * @param id
 * @param bootstrapTableSetup
 */
J.bpTable = function(tableId,bootstrapTableSetup){

    var defaults = {
        onLoadError:function(status, res){
            if(status=='404')
                J.alert("bootstrap-table 未找到请求地址->"+bootstrapTableSetup.url) ;
        },
        showSelectTitle:true,       //show the title of column with 'radio' or 'singleSelect' 'checkbox' option.
            striped:true,               //设置为条文行
            maintainSelected:true,      //保存当前页面页码
            search:true,                //显示页面查询
            searchOnEnterKey:true,      //回车执行查询
            showColumns:true,           //显示所有已加载的数据
            showPaginationSwitch:true,
            showRefresh:true,           //显示刷新，此功能重新访问后台
            showToggle:true,            //显示为行
            pagination:true,            //显示分页
            locale:'zh-CN',
            paginationLoop:true,       //enable pagination continuous loop mode.
            totalField:'total',
            dataField:'data',
            method:'post',
            ajaxOptions:{
                contentType: 'application/json', //很重要
                traditional: true
            },
            rowStyle:function(row, index){
                if(index%2==0){
                    return {classes:"info"} ;
                }else{
                    return {} ;
                }
            },
            responseHandler:function(res){
                if(res.success){
                    return res ;
                }else{
                    J.alert({
                        title:'异常问题',
                        msg: res.errorMsg,
                        btns:'YN',
                        okFunction:function(e,$alert1){
                            if(res.errorMsg.indexOf('请登录')>=0){
                                window.location.href = J.contextPath ;
                            }
                        }
                    }) ;
                    return res ;
                }
            }
    } ;

    var setup = $.extend({},defaults,bootstrapTableSetup) ;
    if(!setup.url){
        J.alert("请填写URL参数") ;
        return false ;
    }
    setup.url = J.contextPath+setup.url ;
    require(['bootstrap-table','js/bootstrap/plugins/bootstrap-table/locale/bootstrap-table-zh-CN'],function(){
        $("#"+tableId).bootstrapTable(setup) ;
    }) ;
} ;
/**
 *
 * @param id
 * @param tableCfg
 * @param columnCfg
 */
J.tableCfg = function(id,tableCfg,columns,columnCfg){
    tableCfg.url = J.contextPath+tableCfg.url ;
    if(!id){
        id = tableCfg ;
        tableCfg = columnCfg ;
        columns = columnCfg ;
    }
    var defOption = {} ;
    var defColumn = {} ;
    $.each($.fn.bootstrapTable.columnDefaults,function(key,value){
        if(typeof value === 'boolean'){
            defColumn[key] = value ;
        }
    }) ;
    var arrayColumn = [] ;
    $.each(columns,function(i,column){
        arrayColumn.push($.extend({},defColumn,column))
    }) ;
    $.each($.fn.bootstrapTable.defaults,function(key,value){
        if(typeof value === 'boolean'){
            defOption[key] = value ;
        }
    }) ;
    var myTableCfg = {
        showSelectTitle:true,       //show the title of column with 'radio' or 'singleSelect' 'checkbox' option.
        striped:true,               //设置为条文行
        search:true,                //显示页面查询
        searchOnEnterKey:true,      //回车执行查询
        showColumns:true,           //显示所有已加载的数据
        showPaginationSwitch:true,
        showRefresh:true,           //显示刷新，此功能重新访问后台
        showToggle:true,            //显示为行
        pagination:true,            //显示分页
        locale:'zh-CN',
        paginationLoop:true,       //enable pagination continuous loop mode.
        totalField:'total',
        dataField:'data',
        method:'post',
        ajaxOptions:{
            contentType: 'application/json', //很重要
            traditional: true
        },
        rowStyle:function(row, index){
            if(index%2==0){
                return {classes:"info"} ;
            }else{
                return {} ;
            }
        },
        responseHandler:function(res){
            if(res.success){
                return res ;
            }else{
                J.alert({
                    title:'异常问题',
                    msg: res.errorMsg,
                    btns:'YN',
                    okFunction:function(e,$alert1){
                        if(res.errorMsg.indexOf('请登录')>=0){
                            window.location.href = J.contextPath ;
                        }
                    }
                }) ;
                return res ;
            }
        }
    } ;
    tableCfg = $.extend({},defOption,myTableCfg,tableCfg)  ;
    tableCfg.columns = arrayColumn ;
    $("#"+id).bootstrapTable(tableCfg) ;
    return $("#"+id) ;
} ;
/**
 * select Option 固定类型
 * @param type str  {'是否','性别','库存级别'}
 * @returns {*[]}
 * @constructor
 */
J.SelectOptions = function(type){
    if(type=='空'){
        return [{val:'',text:''}] ;
    }
    if(type=='商品属性分组'){
        return [{val:'',text:''},{val:'品类',text:'品类'},{val:"品牌",text:'品牌'},{val:"型号",text:'型号'},{val:"颜色",text:'颜色'}] ;
    }
    if(type=='是否'){
        return [{val:'',text:''},{val:true,text:'是'},{val:false,text:'否'}] ;
    }
    if(type=='性别'){
        return [{val:'',text:''},{val:'男',text:'男'},{val:'女',text:'男'}] ;
    }
    if(type=='库存级别'){
        return [{val:'',text:''},{val:'0',text:'现库'},{val:'1',text:'零订'},{val:'2',text:'车房'}] ;
    }
    if(type=='象限'){
        return [{val:'',text:''},{val:'++',text:'++'},{val:'--',text:'--'},{val:'+-',text:'+-'}] ;
    }
    if(type=='级差'){
        return [{val:'',text:''},{val:'+0.25',text:'+0.25'},{val:'+0.50',text:'+0.50'},{val:'+0.75',text:'+0.75'}] ;
    }
    J.error(type+":J.SelectOptions 没有配置类型")
} ;
/**
 * ele{
 * id:elementId
 * name:elementName
 * text:label显示文字
 * type:'password,select，time,hidden,btn'
 *options:[type是select时起效{val:'',text:''}]
 * format:[type是time时起效 YYYY-MM-DD]
 * }
 * 生成Form表单元素
 * @param ele
 */
J.formElement = function(ele){
    var labelRowCss = "col-xs-5 col-sm-5 col-md-5 col-lg-5 row" ;
    var elementRowCss = "col-xs-7 col-sm-7 col-md-7 col-lg-7 row" ;
    var inputCss = "col-xs-11 col-sm-11 col-md-11 col-lg-11" ;
    var $formGroup  = $("<div class='form-group col-xs-12 col-sm-6 col-md-4 col-lg-4 row '>") ;
    var $lable = $("<label  control-label'>").addClass(labelRowCss).attr("for",ele.id).text(ele.text) ;
    var $divElement = $("<div >").addClass(elementRowCss) ;
    $formGroup.append($lable).append($divElement) ;

    if(!ele.type){
        $divElement.append($("<input>").attr("id",ele.id).attr("name",ele.name).css({width:'100%'}).addClass(inputCss).addClass(S.keypress).attr('autocomplete','off').attr("placeholder",ele.text).addClass("form-control")) ;
    }else if(ele.type=='select'){
        var $select  = $("<select>").addClass("form-control").attr('id',ele.id).attr("name",ele.name).attr("placeholder",ele.text).attr("eleType","select").css({width:'100%'}).addClass(inputCss).addClass(S.keypress) ;
        if(ele.options){
            for(var i in ele.options){
                var opt = $("<option>") ;
                opt.val(ele.options[i].val) ;
                opt.text(ele.options[i].text) ;
                $select.append(opt) ;
            }
        }
        $divElement.append($select) ;
    }else if(ele.type=='btn'){
        $formGroup = $("<button >").addClass(inputCss).addClass("btn btn-default").addClass(ele.cls).attr('id',ele.id).text(ele.text) ;
    }else if(ele.type=='hidden'){
        $formGroup = $("<input type='hidden'>").attr('autocomplete','off').attr("name",ele.name).attr("id",ele.id).attr("value",ele.value).addClass(S.keypress) ;
    }else if(ele.type=='time'){
        var $element = $("<input>").addClass("form-control").attr('autocomplete','off').css({width:'100%'}).addClass(inputCss).attr("name",ele.name).attr('id',ele.id).attr("placeholder",ele.text).attr("eleType","time") ;
        $divElement.append($element) ;
        $element.datetimepicker({
            format:ele.format?ele.format: 'YYYY-MM-DD',//显示格式
        }) ;
    }else if(ele.type=='div'){
        return $formGroup ;
    }else{
        var $element = $("<input>").css({width:'100%'}).addClass("form-control").attr('autocomplete','off').addClass(S.keypress).attr("name",ele.name).attr('id',ele.id).attr("placeholder",ele.text).attr('type',ele.type) ;
        $divElement.append($element) ;
    }
    $formGroup.addClass("margin-top_2") ;
    return $formGroup ;
} ;

/**
 * ele{
 * id:elementId
 * name:elementName
 * text:label显示文字
 * type:'password,select，time,hidden,btn'
 *options:[type是select时起效]
 * format:[type是time时起效]
 * }
 * 生成Form表单元素
 * @param ele
 */
J.formElementReadOnly = function(ele){
    var labelRowCss = "col-sm-4" ;
    var elementRowCss = "col-sm-8" ;
    var $formGroup  = $("<div class='form-group'>") ;
    var $lable = $("<label  control-label'>").addClass(labelRowCss).attr("for",ele.id).text(ele.text) ;
    var $divElement = $("<div >").addClass(elementRowCss) ;
    $formGroup.append($lable).append($divElement) ;

    if(!ele.type){
        $divElement.append($("<input>").attr("id",ele.id).attr("name",ele.name).attr("placeholder",ele.text).addClass("form-control").attr("readonly",true).val(ele.value)) ;
    }else if(ele.type=='select'){
        var $select  = $("<select>").addClass("form-control").attr('id',ele.id).attr("name",ele.name).attr("placeholder",ele.text).attr("eleType","select").attr("readonly",true) ;
        if(ele.options){
            for(var i in ele.options){
                var opt = $("<option>") ;
                opt.val(ele.options[i].val) ;
                opt.text(ele.options[i].text) ;
                $select.append(opt) ;
            }
        }
        $divElement.append($select) ;
    }else if(ele.type=='btn'){
        $formGroup = $("<button >").addClass("btn btn-default").attr('id',ele.id).text(ele.text) ;
    }else if(ele.type=='hidden'){
        $formGroup = $("<input type='hidden'>").attr("name",ele.name).attr("id",ele.id).attr("readonly",true).attr('disabled',true);
    }else if(ele.type=='time'){
        var $element = $("<input>").addClass("form-control").attr("name",ele.name).attr('id',ele.id).attr("placeholder",ele.text).attr("eleType","time") .attr("readonly",true);
        $divElement.append($element) ;
        $element.datetimepicker({
            format:ele.format?ele.format: 'YYYY-MM-DD',//显示格式
        }) ;
    }else if(ele.type=='div'){
        return $formGroup ;
    }else{
        var $element = $("<input>").addClass("form-control").attr("name",ele.name).attr('id',ele.id).attr("placeholder",ele.text).attr('type',ele.type).attr("readonly",true) ;
        $divElement.append($element) ;
    }
    return $formGroup ;
} ;
/**
 * cfg:{
 * title:
 * content:
 * }
 * @param cfg
 * @returns {*|jQuery|HTMLElement}
 */
J.$panel = function(cfg){
    var htmlPanel = J.htmlTemp("app/common/panel.temp.html") ;
    var $panel = $(htmlPanel) ;
    $(".panel-heading",$panel).text(cfg.title) ;
    $(".panel-body",$panel).append(cfg.content) ;
    return $panel ;
} ;
/**
 *
 * @param $form
 */
J.formValues = function($form){
    var values = {} ;
    $('input',$form).each(function(i,input){
        var $input = $(input) ;
        values[$input.attr('name')] = $input.val() ;
    }) ;
    $('select',$form).each(function(i,input){
        var $input = $(input) ;
        values[$input.attr('name')] = $input.val() ;
    }) ;
    return values ;
} ;

J.addTag = function(css){
    return $("<span>").addClass(css).addClass("margin-left_2 margin-right_2") ;
} ;

/**列表中的功能按钮*/
J.a_btn = function(val,tag,text){
    return "<a class='a_btn' tag="+tag+" value="+val+">"+text+"</a>" ;
} ;

/**
 * 为form表单填数据
 * @param $RootForm
 * @param data
 */
J.setFormValue = function($Form,data,exception){
    for(var i in data){
        if(exception&&exception==i)continue ;

        if($("#"+i,$Form).length>0){
            var $ele =  $("#"+i,$Form) ;
            if($ele.attr("eleType")=='time'){
                $("#"+i,$Form).val(J.jsDateToStr(J.JavaTimeToJsTime(data[i]),"yyyy-MM-dd")) ;
            }else if($ele.is('select')){
                //$("option",$ele).attr("selected",false) ;
                $ele.val(data[i]) ;
                $("option",$ele).each(function(n,element){
                    if(($(element).val()+'')===(data[i]+'')){
                        $(element).attr("selected",true) ;
                    }else{
                        $(element).attr("selected",false) ;
                    }
                });
            }else {
                $("#"+i,$Form).val(data[i]) ;
            }

        }
    }
} ;
/**
 *
 * @param id    form 的 id
 * @param clazz form 的 样式   form-inline||form-horizontal
 * @param use boolean 元素是否只读
 * @returns {{form: (*|jQuery), fieldset: (*|jQuery|HTMLElement)}}
 */
J.createForm = function(id,clazz,use){
    clazz = clazz||"form-horizontal" ;
    use = (use==null||use)?"":"disabled" ;
    var $Form = $("<form>").attr("id",id).addClass(clazz) ;
    var $fieldset = $("<fieldset "+use+">") ;
    $Form.append($fieldset) ;
    $Form.on('keydown',function(e){ if (event.keyCode == "13")return false}) ;
    return {form:$Form,fieldset:$fieldset} ;
} ;
/**
 * 动态加载LODOP插件
 * @returns {*}
 */
J.getPrint = function(){
    if($("#LODOP_OB").length==0){
        var $obj_LODOP_OB = $("<object>").attr("id","LODOP_OB").attr("classid","clsid:2105C259-1E0C-4534-8141-A753534CB4CA").width(0).height(0) ;
        var $embed_LODOP_EM = $("<embed>").attr("id","LODOP_EM").attr('type',"application/x-print-lodop").width(0).height(0) ;
        $obj_LODOP_OB.append($embed_LODOP_EM).appendTo($("head"))  ;
    }
    var lodop ;
    $.ajax({
        type: 'POST',
        url: J.contextPath+'print/LodopFuncs.js' ,
        dataType: 'script ',
        data: {js:Math.random()} ,
        async:false,
        success: function(a,b,c){
            if($("#LODOP_OB",$('head')).length!=0)
                lodop = getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
        }
    });
    if(lodop===null){
        J.error("打印组件未加载成功")
    }

    lodop.PRINT_INIT("ehop打印");
    return lodop ;
} ;
/**
 * 打印方式
 * @param lodop
 * @param printType   0是预览打印 1是直接打印 2是打印设计
 */
J.print = function(lodop,printType){
    printType = printType||0 ;
    if(printType==0){
        lodop.PREVIEW();
    }else if(printType==1){
        lodop.PRINT();
    }else if(printType==2){
        lodop.PRINT_DESIGN();
    }
} ;

J.filterRecord = function(record){
    var d = {} ;
    /**treegrid 行会多出四个属性*/
    var filds = ["_last","_level","_nodes","_parent"] ;
    var is ;
    for(var name in record){
        is = false ;
        for(var i in filds){
            if(name==filds[i]){
                is = true ;
            }
        }
        if(!is){
            d[name] = record[name];
        }
    }
    return d ;
} ;
/**
 *
 * @param render
 * @param view
 * *.view.js 的render统一调用方法
 */
J.render = function(functionRender,view){
    /**当前工作视图*/
    J._CurrentWorkSpaceView = view ;
    var $me =$("#wm_workspace") ;
    $me.empty()
    var $div_Row = $("<div class='col-xs-12 col-sm-12 col-md-12 col-lg-12'>");
    view.$div_Row = $div_Row ;
    $me.append(
        $("<div>").addClass('container-fluid').append($div_Row)
    ) ;
    /**
     * view  当前的功能视图
     * $div_row  页面的内容显示div
     * */
    functionRender(view,$div_Row) ;
} ;

J.studyView = function(fun){
    var $video = $("<video>").attr("src",fun.studyPath).attr("controls","controls").html("不好意思，您的浏览器不支持") ;
    J.alert({
        title:fun.name,
        msg:$video
    }) ;
};
/**以度数显示数字*/
J.showLens = function (v){
    var lens = "" ;
    v = parseFloat(v) ;
    if(isNaN(v)){
        throw new Error(v + "不是数字") ;
    }
    if(v>0){
        lens='+'+v.toFixed(2) ;
    }else{
        lens = v.toFixed(2) ;
    }
    return lens+"" ;
}

/**
 * 以数据中对象的 arr  属性为key ,对象为值 ，arr默认为id
 * @param list
 * @param arr
 * @constructor
 */
J.ArrayToMap = function(list,arr){
    var key = arr||'id' ;
    var map = {} ;
    for(var i in list){
        map[list[i][key]]=list[i] ;
    }
    return map ;
}

/**
 * 配合J.ArrayToMap使用，map的value集合数组
 * @constructor
 */
J.MapToArray = function(map){
    var list = [] ;
    for(var i in map){
        list.push(map[i]) ;
    }
    return list ;
}



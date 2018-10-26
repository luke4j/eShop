/**
 * 定义文件路径
 contextPath 是由common.jsp中定义
 */
var _cp = contextPath.substring(0,contextPath.length-1) ;
requirejs.config({
    urlArgs:"jsession="+Math.random(),
    "baseUrl": _cp,
    map: {
        '*': {
            'css': 'js/css.min'
        }
    },
    "paths": {
        "J":"app/J",
        "S":"app/S",
        "G":"app/G",
        "md5":"js/md5",
        "require": "js/require",
        "jquery": "js/jquery/jquery-1.12.4",
        "underscore": "js/underscore",
        "backbone": "js/backbone",
        "bootstrap": "js/bootstrap/js/bootstrap",
        "bootstrap-table":"js/bootstrap/plugins/bootstrap-table/bootstrap-table",
        "table-zh":"js/bootstrap/plugins/bootstrap-table/locale/bootstrap-table-zh-CN",
        "bootstrap-editable":"js/bootstrap/plugins/bootstrap-table/extensions/editable/bootstrap-editable",
        "bootstrap-table-editable":"js/bootstrap/plugins/bootstrap-table/extensions/editable/bootstrap-table-editable",

        "bootstrap-table-treegrid":"js/bootstrap/plugins/bootstrap-table/extensions/treegrid/bootstrap-table-treegrid",
        "jquery-treegrid":"js/bootstrap/plugins/bootstrap-table/extensions/treegrid/jquery-treegrid",
        "lensTable":"js/jquery/plugins/TableFreeze",

        "treeview":'js/bootstrap/plugins/treeview/bootstrap-treeview',
        "picker":"js/bootstrap/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min",
        "picker-zh":"js/bootstrap/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN",
        "moment":"js/bootstrap/plugins/bootstrap-datetimepicker/moment.min",
        "print":"plugin/print/LodopFuncs",
        "ztree":"js/jquery/plugins/ztree/jquery.ztree.all"
    },

    /**
     * 配置js文件依赖关系
     * */
    shim:{
        ztree:{
            deps:['jquery','css!js/jquery/plugins/ztree/metroStyle/metroStyle.css'],
            exports:'ztree'
        },
        treeview:{
            deps:['jquery','css!js/bootstrap/plugins/treeview/bootstrap-treeview.min.css'],
            exports:'treeview'
        },
        lensTable:{
            deps:['jquery'],
            exports:'lensTable'
        },
        moment:{
            deps:['jquery'],
            exports:'moment'
        },
        picker:{
            deps:['jquery','bootstrap','moment','picker-zh',
                'css!js/bootstrap/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker',
                'css!js/bootstrap/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker-standalone.css'],
            exports:'picker'
        },
        md5:{
            deps:['jquery'],
            exports:'md5'
        },
        'bootstrap-table':{
            deps:['jquery','bootstrap',
                'css!js/bootstrap/plugins/bootstrap-table/bootstrap-table.min'],
            exports:'bootstrap-table'
        },
        'bootstrap-table-zh':{
            deps:['bootstrap-table'],
            exportss:'bootstrap-table-zh'
        },
        'bootstrap-table-editable':{
            deps:['jquery','bootstrap','bootstrap-table','bootstrap-editable',
                "css!js/bootstrap/plugins/bootstrap-table/extensions/editable/bootstrap-editable"],
            exports:'bootstrap-table-editable'
        },
        "bootstrap-table-treegrid":{
            //"bootstrap-table-editable",
            deps:['jquery','bootstrap','bootstrap-table','jquery-treegrid','css!js/bootstrap/plugins/bootstrap-table/extensions/treegrid/jquery-treegrid'],
            exports:'bootstrap-table-treegrid'
        },
        bootstrap:{
            deps:['jquery',
                'css!js/bootstrap/css/bootstrap',
                'css!js/bootstrap/css/bootstrap-theme'],
            exports:'bootstrap'
        },
        J:{
            deps : ['jquery'],
            exports:'J'
        },
        underscore:{
            exports : 'underscore'
        },
        backbone:{
            /** 设置backbone 需要underscore支持 */
            deps : ['underscore'],
            exports : 'backbone'
        }
    }
});
requirejs(['app/app']);



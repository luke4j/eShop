/**使用require.js的标准方法定义 js脚本*/
define(function(require, exports, module){
    /**加载定义好的jquery*/
    require("jquery") ;
    /**加载登录js*/
    var LoginView = require('app/login/login.view') ;

    $(function(){
        /**加载完成后运行*/
        new LoginView() ;
    }) ;
}) ;
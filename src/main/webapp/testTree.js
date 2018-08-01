var treeView = {
    beforeExpand:function(treeId, treeNode){
        if(!treeNode.isAjaxing){
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            zTree.reAsyncChildNodes(treeNode,"refresh") ;
            return true ;
        }else{
            return false ;
        }

    }
} ;

var setting = {
    async: {
        enable: true,
        type: 'POST',
        dataType:'json',
        contentType: 'application/json', //很重要
        traditional: true,
        url:'http://127.0.0.1/eshop/tree/getZtreeData.act',
        otherParam:function(treeId,treeNode){
            console.dir(treeNode) ;
        },
        dataFilter:function(treeId, parentNode, responseData){
            //console.dir(arguments) ;
            return responseData.data ;
        }
    },
    check: {
        enable: true
    },
    data: {
        simpleData: {
            enable: false
        }
    },
      view: {
        expandSpeed: "",
          addDiyDom: function(treeId, treeNode){
              var aObj = $("#" + treeNode.tId + "_a");
              aObj.addClass("treeNode_50") ;

              aObj.append("<span class='badge'>42</span>") ;

              aObj.append("<a class='float_right a_btn'>删除</a>") ;

              aObj.append("<a class='float_right a_btn'>修改</a>") ;

              var $add = $("<a class='float_right a_btn'>添加</a>") ;
              aObj.append($add) ;

              $add.on('click',function(){
                  console.dir(treeNode) ;
              }) ;



          }
      },
      callback: {
        beforeExpand: treeView.beforeExpand,
        //onAsyncSuccess: onAsyncSuccess,
        //onAsyncError: onAsyncError
      }
};

var zNodes =[
    {name:"500个节点", id:"-1", count:500, times:1, isParent:true},
    {name:"1000个节点", id:"-2", count:1000, times:1, isParent:true},
    {name:"2000个节点", id:"-3", count:2000, times:1, isParent:true}
];


$(document).ready(function(){
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    console.dir("==================") ;
});
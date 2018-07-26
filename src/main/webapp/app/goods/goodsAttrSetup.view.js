define(function(require, exports, module) {
    require("md5");
    require("S");
    require("J");
    require("backbone");
    require("bootstrap-table-editable") ;
    var View = Backbone.View.extend({
        el: $("body"),
        events: {
            "click .btn_return":"btn_return_click_handler"
        },
        initialize: function () {
            this._args = arguments[0] ;
            this.render();
        },
        render: function () {
            var me = this ;
            J.render(function(view,$div_Row){
                var data = me._args[2] ;
                var $toolbar = $("<div id='return_'>") ;
                $("<button>").addClass("btn").addClass("btn_return").addClass(S.btn_r4_tag_css).text("返回").appendTo($toolbar) ;
                $("<div>").text(data.c_group+":"+data.text+"->属性配置").appendTo($toolbar) ;
                $toolbar.appendTo( $div_Row) ;
               $div_Row.append('<table id="tbl_kindAttrSetup"></table>') ;
                var tblCfg = {
                    height:650,
                    toolbar:$toolbar,
                    pagination:false,
                    url:'goodsTree/find_goods_attr_setup.act',
                    queryParams:function(){
                        return {id:data.id} ;
                    },
                    /**编辑完后保存事件*/
                    onEditableSave:function(columnFild,record,columnText,edit){
                        console.dir(arguments) ;
                        var param = record ;
                        var kindId = record.kind.id ;
                        J.ajax({
                            url:'goodsTree/edit_goods_attr_setup.act',
                            data:param,
                            success:function(data){
                                $("#tbl_kindAttrSetup").bootstrapTable('refresh',{url:'goodsTree/find_goods_attr_setup.act',queryParams:{id:kindId}}) ;
                            }
                        }) ;
                    },
                    columns : [
                        {
                            field:'id',
                            title:'id',
                            visible:false
                        },{
                            field:'columnName',
                            title:'序号'
                        },
                        {
                            field:'columnValue',
                            title:'代表意义',
                            editable:true
                        },{
                            field:'c_type',
                            title:'显示类型',
                            editable: {
                                type: 'select',
                                pk: 1,
                                source: [
                                    {value: 'text', text: '文本'},
                                    {value: 'select', text: '下拉列表'}
                                ]
                            }
                        },{
                            field:'defaults',
                            title:'默认值',
                            editable:true
                        }
                    ]
                }
                J.bpTable("tbl_kindAttrSetup",tblCfg) ;

            },this) ;
        },
        // ------------------------------------事件代码区-----------------------------------------
        btn_return_click_handler:function(e){
            var me = this ;
            J.changeView(me,me._args[3]) ;
        }
    }) ;



    return View ;
}) ;

function toGlassLens(v){
			var lens = "" ;
			if(v>0){
				lens='+'+v.toFixed(2) ;
			}else{
				lens = v.toFixed(2) ;
			}
			return lens ;
		}
function tableStyleFun (param){
	var tableStyle = $("<style>").attr("type","text/css") ;
	$("head").append(tableStyle) ;
	tableStyle.text(

			"#div_"+param.id+" table{font-family:宋体; font-size:10pt;border:1px green solid;background-color:white;}"+
			"#div_"+param.id+" table{empty-cells:show;border-collapse:collapse;border-spacing:0}"+
			"#div_"+param.id+" table td{height:30px;text-align:center;border:1px green solid;width:50px;overflow:hidden;}"+
			"#div_"+param.id+" table .tabTh{color:green;font-weight:bold;background-color:#f1faee;}"+
			"#div_"+param.id+" table .trGreen{color:green;font-weight:bold;background-color:#f1faee;}"+
			"#div_"+param.id+" table .trWidth{color:green;font-weight:bold;background-color:#ffffff;}"+
			"#div_"+param.id+" table td .hColumn{color:green;font-weight:bold;background-color:#f1faee;}"+
			
			"#oDivH_"+param.id+" table{font-family:宋体; font-size:10pt;border:1px green solid;background-color:white;}"+
			"#oDivH_"+param.id+" table{empty-cells:show;border-collapse:collapse;border-spacing:0}"+
			"#oDivH_"+param.id+" table td{height:30px;text-align:center;border:1px green solid;width:50px;overflow:hidden;}"+
			"#oDivH_"+param.id+" table .tabTh{color:green;font-weight:bold;background-color:#f1faee;}"+
			"#oDivH_"+param.id+" table .trGreen{color:green;font-weight:bold;background-color:#f1faee;}"+
			"#oDivH_"+param.id+" table .trWidth{color:green;font-weight:bold;background-color:#ffffff;}"+
			"#oDivH_"+param.id+" table td .hColumn{color:green;font-weight:bold;background-color:#f1faee;}"+
			
			"#oTableLH_"+param.id+" {font-family:宋体; font-size:10pt;border:1px green solid;background-color:white;}"+
			"#oTableLH_"+param.id+" {empty-cells:show;border-collapse:collapse;border-spacing:0}"+
			"#oTableLH_"+param.id+"  td{height:30px;text-align:center;border:1px green solid;width:50px;overflow:hidden;}"+
			"#oTableLH_"+param.id+"  .tabTh{color:green;font-weight:bold;background-color:#f1faee;}"+
			"#oTableLH_"+param.id+"  .trGreen{color:green;font-weight:bold;background-color:#f1faee;}"+
			"#oTableLH_"+param.id+"  .trWidth{color:green;font-weight:bold;background-color:#ffffff;}"+
			"#oTableLH_"+param.id+"  td .hColumn{color:green;font-weight:bold;background-color:#f1faee;}"+
			
			"#oDivL_"+param.id+" table{font-family:宋体; font-size:10pt;border:1px green solid;background-color:white;}"+
			"#oDivL_"+param.id+" table{empty-cells:show;border-collapse:collapse;border-spacing:0}"+
			"#oDivL_"+param.id+" table td{height:30px;text-align:center;border:1px green solid;width:50px;overflow:hidden;}"+
			"#oDivL_"+param.id+" table .tabTh{color:green;font-weight:bold;background-color:#f1faee;}"+
			"#oDivL_"+param.id+" table .trGreen{color:green;font-weight:bold;background-color:#f1faee;}"+
			"#oDivL_"+param.id+" table .trWidth{color:green;font-weight:bold;background-color:#ffffff;}"+
			"#oDivL_"+param.id+" table td .hColumn{color:green;font-weight:bold;background-color:#f1faee;}"
	) ;
} ;

/**
 * 页面中对度数商品的设置元素
 * @param minSph
 * @param maxSph
 * @param sphPool
 * @param minCyl
 * @param maxCyl
 * @param cylPool
 * @param param    {id:'设置table元素的id',tdEvent:'设置table中的td的事件',appendTo:'添加到页面哪个元素中'}
 */
/**建table*/
function createtable_zz(minSph,maxSph,sphPool,minCyl,maxCyl,cylPool,param){
	tableStyleFun (param) ;
		/*内部table宽高*/
		var panel = $("<div>").attr('id','div_'+param.id).attr("style","overflow:auto;width:990px;height:588px;padding:0px;") ;
		if(param.appendTo)
			$('#'+param.appendTo).append(panel) ;
		
		var tableWidth = (maxCyl - minCyl)/cylPool+2 ;
		var table = $("<table>").attr("border",1).attr('width',tableWidth*50) ;
		if(param.id)
			table.attr('id',param.id) ;
		panel.append(table) ;
		var tr,td ;
		
		/*sph球镜要多一行做标题*/
		for(var i = minSph-sphPool,m=0 ;i<=maxSph;i+=sphPool,m++){
			/**第一行，（头）*/
			if(i == (minSph-sphPool)){
				tr =  $("<tr>").attr('class','tabTh') ;
			}else{
				/**从第二行起，每一行*/
				if((m)%2==0)
					tr = $("<tr>").attr('class','trGreen') ;
				else
					tr = $("<tr>").attr('class','trWidth') ;
			}
			/*cyl柱镜要多一列做行首*/
			for(var j =minCyl-cylPool  ;j<=maxCyl ;j+=cylPool){
				/*左上角球柱显示*/
				if(i==(minSph-sphPool)&&j==(minCyl-cylPool)){  
					/**左上角每一格*/
					td = $("<td width=100px />").html("<font color='red'>球\\柱</font>") ;
					tr.append(td);
				}else if(i==(minSph-sphPool)){
					/**第一行*/
					td = $("<td width=100px />").html("<font color='black'>"+toGlassLens(j)+"</font>") ;
					tr.append(td)
				}else if(j==(minCyl-cylPool)){
					/**第一列*/
					td = $("<td width=100px class='tabLie' />").html("<font color='black'>"+toGlassLens(i)+"</font>") ;
					tr.append(td)
				}else{
					td = $("<td width=100px />").text('')
					.attr('sc',"{sph:"+toGlassLens(i)+",cyl:"+toGlassLens(j)+"}")
					.attr('sph',toGlassLens(i))
					.attr('cyl',toGlassLens(j));
					tr.append(td)
				}
			}
			table.append(tr) ;
		}

		$("td[sph='0.00']",table).attr('style','background-color:yellow;') ;
		$("td[cyl='0.00']",table).attr('style','background-color:yellow;') ;

		if(param.tdEvent){
			
			if(typeof (param.tdEvent)=='function'){
				$("td",table).bind('click',function(e){
					param.tdEvent(e) ;
				}) ;
			}
			if(typeof(param.tdEvent)=='object'){
				for(var nm in param.tdEvent ){
					$("td",table).bind(nm,function(e){
						param.tdEvent[nm](e) ;
					}) ;
				}
			}
			
		}
		
		return panel ;
	}

;
/**建table*/
function createtable_ff(minSph,maxSph,sphPool,minCyl,maxCyl,cylPool,param){
	tableStyleFun (param) ;
		/*内部table宽高*/
		var panel = $("<div>").attr('id','div_'+param.id).attr("style","overflow:auto;width:990px;height:588px;padding:0px;") ;
		if(param.appendTo)
			$('#'+param.appendTo).append(panel) ;
		
		var tableWidth = (maxCyl - minCyl)/cylPool+2 ;
		var table = $("<table>").attr("border",1).attr('width',tableWidth*50) ;
		if(param.id)
			table.attr('id',param.id) ;
		panel.append(table) ;
		var tr,td ;
		/*sph球镜要多一行做标题*/
		for(var i = maxSph+sphPool,m=0 ;i>=minSph;i-=sphPool,m++){
			
			if(i == (maxSph+sphPool)){
				tr =  $("<tr>").attr('class','tabTh') ;
			}else{
				if((m)%2==0)
					tr = $("<tr>").attr('class','trGreen') ;
				else
					tr = $("<tr>").attr('class','trWidth') ;
			}
			/*cyl柱镜要多一列做行首*/
			for(var j =maxCyl+cylPool  ;j>=minCyl ;j-=cylPool){
				/*左上角球柱显示*/
				if(i==(maxSph+sphPool)&&j==(maxCyl+cylPool)){  
					td = $("<td width=100px />").html("<font color='red'>球\\柱</font>") ;
					tr.append(td);
				}else if(i==(maxSph+sphPool)){
					td = $("<td width=100px />").html("<font color='black'>"+toGlassLens(j)+"</font>") ;
					tr.append(td)
				}else if(j==(maxCyl+cylPool)){
					td = $("<td width=100px class='tabLie' />").html("<font color='black'>"+toGlassLens(i)+"</font>") ;
					tr.append(td)
				}else{
					td = $("<td width=100px />").text('')
					.attr('sc',"{sph:"+toGlassLens(i)+",cyl:"+toGlassLens(j)+"}")
					.attr('sph',toGlassLens(i))
					.attr('cyl',toGlassLens(j));
					tr.append(td) ;
				}
			}
			table.append(tr) ;
		}

		$("td[sph='0.00']",table).attr('style','background-color:yellow;') ;
		$("td[cyl='0.00']",table).attr('style','background-color:yellow;') ;

		if(param.tdEvent){
			
			if(typeof (param.tdEvent)=='function'){
				$("td",table).bind('click',function(e){
					param.tdEvent(e) ;
				}) ;
			}
			if(typeof(param.tdEvent)=='object'){
				for(var nm in param.tdEvent ){
					$("td",table).bind(nm,function(e){
						param.tdEvent[nm](e) ;
					}) ;
				}
			}
			
		}
		
		return panel ;
	}

;
/**建table*/
function createtable_zf(minSph,maxSph,sphPool,minCyl,maxCyl,cylPool,param){
	tableStyleFun (param) ;
		/*内部table宽高*/
		var panel = $("<div>").attr('id','div_'+param.id).attr("style","overflow:auto;width:990px;height:588px;padding:0px;") ;
		if(param.appendTo)
			$('#'+param.appendTo).append(panel) ;
		
		var tableWidth = (maxCyl - minCyl)/cylPool+2 ;
		var table = $("<table>").attr("border",1).attr('width',tableWidth*50) ;
		if(param.id)
			table.attr('id',param.id) ;
		panel.append(table) ;
		var tr,td ;
		
		/*sph球镜要多一行做标题*/
		for(var i = minSph-sphPool,m=0 ;i<=maxSph;i+=sphPool,m++){
			
			if(i == (minCyl-cylPool)){
				tr =  $("<tr>").attr('class','tabTh') ;
			}else{
				if((m)%2==0)
					tr = $("<tr>").attr('class','trGreen') ;
				else
					tr = $("<tr>").attr('class','trWidth') ;
			}
			/*cyl柱镜要多一列做行首*/
			for(var j =maxCyl+cylPool  ;j>=minCyl ;j-=cylPool){
				/*左上角球柱显示*/
				if(i==(minSph-sphPool)&&j==(maxCyl+cylPool)){  
					td = $("<td width=100px />").html("<font color='red'>球\\柱</font>") ;
					tr.append(td);
				}else if(i==(minSph-sphPool)){
					td = $("<td width=100px />").html("<font color='black'>"+toGlassLens(j)+"</font>") ;
					tr.append(td)
				}else if(j==(maxCyl+cylPool)){
					td = $("<td width=100px class='tabLie' />").html("<font color='black'>"+toGlassLens(i)+"</font>") ;
					tr.append(td)
				}else{
					td = $("<td width=100px />").text('')
					.attr('sc',"{sph:"+toGlassLens(i)+",cyl:"+toGlassLens(j)+"}")
					.attr('sph',toGlassLens(i))
					.attr('cyl',toGlassLens(j));
					tr.append(td)
				}
			}
			table.append(tr) ;
		}

		$("td[sph='0.00']",table).attr('style','background-color:yellow;') ;
		$("td[cyl='0.00']",table).attr('style','background-color:yellow;') ;

		if(param.tdEvent){
			
			if(typeof (param.tdEvent)=='function'){
				$("td",table).bind('click',function(e){
					param.tdEvent(e) ;
				}) ;
			}
			if(typeof(param.tdEvent)=='object'){
				for(var nm in param.tdEvent ){
					$("td",table).bind(nm,function(e){
						param.tdEvent[nm](e) ;
					}) ;
				}
			}
			
		}
		
		return panel ;
	}

;
/**上下左右事件*/
function event_4(){
	$("td[sc]").bind('keydown',function(e){
		switch(e.keyCode){
		case 38: /**上*/
			var tdCellIndex = $(e.target).parent()[0].cellIndex +1;
			var prevTr = $(e.target).parent().parent().prev() ;
			var td = $("td:nth-child("+tdCellIndex+")",prevTr) ;
			for(;;){
				if(td.text()=='--'){
					prevTr = prevTr.prev() ;
					td = $("td:nth-child("+tdCellIndex+")",prevTr) ;
					continue ;
				}else{
					break;
				}
				
			}
			td.click() ;
			break ;
		case 40: /**下*/
			var tdCellIndex = $(e.target).parent()[0].cellIndex +1;
			var prevTr = $(e.target).parent().parent().next() ;
			var td = $("td:nth-child("+tdCellIndex+")",prevTr) ;
			for(;;){
				if(td.text()=='--'){
					prevTr = prevTr.next() ;
					td = $("td:nth-child("+tdCellIndex+")",prevTr) ;
					continue ;
				}else{
					break;
				}
				
			}
			td.click() ;
			break ;
		case 37: /**左*/
			var tdCellIndex = $(e.target).parent()[0].cellIndex ;
			var prevTr = $(e.target).parent().parent() ;
			var td = $("td:nth-child("+tdCellIndex+")",prevTr) ;
			for(;;){
				if(td.text()=='--'){
					tdCellIndex = tdCellIndex-1 ;
					td = $("td:nth-child("+tdCellIndex+")",prevTr) ;
					continue ;
				}else{
					break;
				}
				
			}
			td.click() ;
			break ;
		case 39: /**右*/
			var tdCellIndex = $(e.target).parent()[0].cellIndex+2 ;
			var prevTr = $(e.target).parent().parent() ;
			var td = $("td:nth-child("+tdCellIndex+")",prevTr) ;
			for(;;){
				if(td.text()=='--'){
					tdCellIndex = tdCellIndex+1 ;
					td = $("td:nth-child("+tdCellIndex+")",prevTr) ;
					continue ;
				}else{
					break;
				}
				
			}
			td.click() ;
			break ;
		}
	}) ;
}
/**象限*/
function xiangXian_combo(inputEvent){
	var store = Ext.create('Ext.data.Store', {
	    fields: ['_lbl', '_val'],
	    data : [
	        {_lbl:'--',_val:'2'},
	        {_lbl:'++',_val:'1'},
	        {_lbl:'+-',_val:'3'}
	    ]
	});
	 
	var combo = Ext.create('Ext.form.ComboBox', {
		name:'xxCombo',
	    fieldLabel: '象限',
	    editable:false,
	    queryMode: 'local',
	    store: store,
	    width:180,
	    labelWidth:40,
	    labelStyle:'text-align: right;',
	    displayField: '_lbl',
	    valueField: '_val',
	    disabled:true,
	    listeners:{
	    	select:function(combo, records){
	    		var win = combo.up('window') ;
	    		var r = records[0] ;
	    		var sph,cyl ;
				/*以配置好的度数给相应的td添加可编辑标志*/
				var td,div ;
				win.removeAll() ;
	    		if(r.get('_lbl')=='++'){
	    			div = createtable_zz(lens.sphEnd,lens.sphStart,lens.sphPool,lens.cylEnd,lens.cylStart,lens.cylPool,{
	    				id:'lensTableId'
	    			}) ;
	    			win.body.update(div[0].outerHTML) ;
					
	    		}
	    		if(r.get('_lbl')=='--'){
	    			div = createtable_ff(lens.sphEnd,lens.sphStart,lens.sphPool,lens.cylEnd,lens.cylStart,lens.cylPool,{
	    				id:'lensTableId'
	    			}) ;
	    			win.body.update(div[0].outerHTML) ;
					
	    		}
	    		if(r.get('_lbl')=='+-'){
	    			div = createtable_zf(lens.sphEnd,lens.sphStart,lens.sphPool,lens.cylEnd,lens.cylStart,lens.cylPool,{
	    				id:'lensTableId'
	    			}) ;
	    			win.body.update(div[0].outerHTML) ;
					
	    		}
	    		/**生成可以滚动的table*/
	    		$("#lensTableId").FrozenTable(1,0,1);
	    		/**为所有的td添加属性*/
	    		for(var i in lensAr){
					sph = llg.toGlassLens(lensAr[i].sph) ;
					cyl = llg.toGlassLens(lensAr[i].cyl) ;
					td = $("td[sph='"+sph+"'][cyl='"+cyl+"']",$('#lensTableId'))
					if(td.length){
						td.attr('dia',lensAr[i].dia)
						.attr('lensEdit',true)
						.attr('inPrice',lensAr[i].inPrice) 
						.attr('outPrice',lensAr[i].outPrice)
						.attr('lensId',lensAr[i].id) ;
						if(lensAr[i].rnum)
							td.attr('rnum',lensAr[i].rnum) ;
					}
				}
	    		
	    		/**为可编辑td添加事件*/
				$("td[lensEdit='true']",$('#lensTableId')).bind('click',function(e){
					if($('input',$(e.target)).length==0){
						var yz = $(e.target).text() ;
						var input = $("<input style='width:30px' values="+yz+">") ;
						$(e.target).append(input) ;
						input.focus() ;
						input.bind('blur',function(be){
							if($(be.target).val()){
								$(e.target).text($(be.target).val()) ;
							}else{
								$(e.target).text(yz) ;
							}
								
							if($(be.target).val()&&$(be.target).val()!=yz){
								$(e.target).attr('lensChange',"true") ;
							}
							if(inputEvent)
								inputEvent(be,e) ;
						}) ;
					}
				}) ;
	    		/**为上下左右添加事件*/
				event_4() ;
	    		/**为不可编辑td添加显示标志*/
				$("td[lensEdit!='true'][sc]",$('#lensTableId')).each(function(a,b,c){
					$(b).html("<font color='red'>--</font>") ;
					if(($(b).attr('sph')=='0.00')||($(b).attr('cyl')=='0.00')){

					}else{
						$(b).attr('style','background-color:black;') ;
					}
				}) ;
	    	}
	    }
	}) ;
	return combo ;
}
/**标准tbar*/
function bz_tbar(){
	var tbar1 = {name:'lens_bz_tbar',xtype:'toolbar',dock:'top',items:[{xtype:'numberfield',fieldLabel:'球镜',name:'allSph',step:0.25,labelAlign :'right',labelWidth:40,width:120},
	                                               {xtype:'numberfield',name:'allSphNum',labelAlign :'right',width:60},
	                                               {name:'allSphBtn',text:'设置',handler:function(){
		                                           		var sph = this.up().down('[name=allSph]').getValue() ;
		                                           		sph = parseFloat(sph) ;
		                                           		shp = llg.toGlassLens(sph) ;
		                                           		var sphNum = this.up().down('[name=allSphNum]').getValue() ;
		                                           		
		                                           		$("td[lensEdit='true'][sph='"+shp+"']",$('#lensTableId')).each(function(i,el){
		                                           			$(el).text(sphNum) ;
		                                           			$(el).attr('lensChange',true) ;
		                                           		}) ;
                                          			}},
                                          			'-',
	                                               {xtype:'numberfield',fieldLabel:'柱镜',name:'allCyl',step:0.25,labelAlign :'right',labelWidth:40,width:120},
													{xtype:'numberfield',name:'allCylNum',labelAlign :'right',width:60},
													{name:'allCylBtn',text:'设置',handler:function(){
															var cyl = this.up().down('[name=allCyl]').getValue() ;
															cyl = parseFloat(cyl) ;
															cyl = llg.toGlassLens(cyl) ;
															var cylNum = this.up().down('[name=allCylNum]').getValue() ;
															
															$("td[lensEdit='true'][cyl='"+cyl+"']",$('#lensTableId')).each(function(i,el){
																$(el).text(cylNum) ;
																$(el).attr('lensChange',true) ;
															}) ;
														}},
														'-',
														{xtype:'numberfield',fieldLabel:'球',name:'sSph',step:0.25,labelAlign :'right',labelWidth:40,width:120},
														{xtype:'numberfield',fieldLabel:'柱',name:'sCyl',step:0.25,labelAlign :'right',labelWidth:40,width:120},
														{xtype:'numberfield',name:'sNum',labelAlign :'right',width:60},
														{name:'sSphBtn',text:'设置',handler:function(){
																var sph = this.up().down('[name=sSph]').getValue() ;
																var cyl = this.up().down('[name=sCyl]').getValue() ;
																var num = this.up().down('[name=sNum]').getValue() ;
																
																sph = parseFloat(sph) ;
																sph = llg.toGlassLens(sph) ;
																
																cyl = parseFloat(cyl) ;
																cyl = llg.toGlassLens(cyl) ;
																
																$("td[lensEdit='true'][sph='"+sph+"'][cyl='"+cyl+"']",$('#lensTableId')).each(function(i,el){
																	$(el).text(num) ;
																	$(el).attr('lensChange',true) ;
																}) ;
															}}	
	                                               ]} ;
	return tbar1 ;
}
/**数据库中所有的度数信息*/
var lensAr = [] ;
var lens = {} ;
/**分批查询配置度数*/
function pageLoadData(lensLevel,lensKey,goodsId,page,win){
	var param = {lensLevel:lensLevel,notAlertTs:'yes',lensKey:lensKey,goodsId:goodsId} ;
	if(page==null){
		page = {limit:100,start:0,page:1,count:0} ;
	}
	param = Ext.applyIf(param,page) ;
	$.ajax({
	    type: 'POST',
	    url: contextPath+'lensgoods/findLensGoodsInfo.act' ,
	    data: param ,
	    async:false,
	    success: function(a,b,c){
	    	a = Ext.decode(a) ;
	    	if(a.success=='fail'){
	    		eval(a.exceptionMessage.info) ;
	    	}
	    	for(var i in a.data){
	    		lensAr.push(a.data[i]) ;
	    	}
	    	page.count = a.total  ;
	    	page.page+=1 ;
	    	page.start+=page.limit ;
	    	
	    	if(!(page.start>=page.count)){
	    		pageLoadData(lensLevel,lensKey,goodsId,page,win) ;
	    	}else{
	    		alert("数据加载完成") ;
	    		win.down('[name=xxCombo]').enable() ;
	    	}
	    }
	});
}
/**
 * 生成某一度数商品二维图表
 * */
function ds_win(goodsId,level,btns,title,inputEvent){
	lensAr = [] ;
	var tbar1 = bz_tbar() ;
	var tbar = [] ;
	tbar.push(xiangXian_combo(inputEvent)) ;
	for(var i in btns ){
		tbar.push(btns[i]) ;
	}
	var tbar2 = {xtype:'toolbar',dock:'top',items:tbar} ;
	
	var param = {
			notAlertTs:'yes',
			goodsId:goodsId,
			level:level
	} ;
	
	var win = Ext.create('She.view.basic.Window',{
		title:title,
		name:'lensWindow',
//		maxWidth:900,
//		maxHeight:668,
//		width:700,
//		height:600,
		maximizable:true,
		autoScroll:true,
		dockedItems:[tbar1,tbar2]
	}) ;
	win.show() ;
	
	win.lensLevel = level ;
	win.goodsId = goodsId ;
	
	win.maximize() ;
	llg.Ajax("lensgoods/findLensGoods.act",param,function(d){
		lens = d ;
		win.lensKey = d.codeUnique ;
		pageLoadData(level,d.codeUnique,goodsId,null,win) ;
	}) ;
	return win ;
}

















<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=11" />
		<title>QUICK UI V4.0 组件示例</title>
		<!--框架必需start-->
		<link href="/quickui/libs/css/import_main.css" rel="stylesheet" type="text/css" />
		<link href="/quickui/libs/skins/blue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="blue" positionTarget="positionContent" defaultFontSize="14" ie6detect="/quickui/sample/templete/detect.jsp"/>
		<link href="/quickui/system/main/skin/style.css" rel="stylesheet" type="text/css" id="skin" skinPath="system/main/skin/"  />
		<script type="text/javascript" src="/quickui/libs/js/jquery.js"></script>
		<script type="text/javascript" src="/quickui/libs/js/language/cn.js"></script>
		<script type="text/javascript" src="/quickui/libs/js/main.js"></script>
		<!--框架必需end-->

		<!--引入弹窗组件start-->
		<script type="text/javascript" src="/quickui/libs/js/popup/drag.js"></script>
		<script type="text/javascript" src="/quickui/libs/js/popup/dialog.js"></script>
		<!--引入弹窗组件end-->

		<!--引入提示组件start-->
		<script type="text/javascript" src="/quickui/libs/js/popup/toast.js"></script>
		<script type="text/javascript" src="/quickui/libs/js/popup/notice.js"></script>
		<!--引入提示组件end-->

		<!--引入小铃铛start-->
		<link rel="stylesheet" type="text/css" href="/quickui/libs/skins/blue/msgbox.css" />
		<script src="/quickui/libs/js/popup/msgbox.js" type="text/javascript" charset="utf-8"></script>
		<!--引入小铃铛end-->

		<!--引入导航处理start-->
		<script src="/quickui/system/main/js/nav.js" type="text/javascript" charset="utf-8"></script>
		<!--引入导航处理end-->

		
		<style>
		body{
			width: 100%!important;
			margin: 0;
			padding:0;

		}	
		.hbox_content_right_colorbox span.blue_box{
			background-color: #2DA9FA;
		}
		.hbox_content_right_colorbox span.green_box{
			background-color: #1fc695;
		}
		.hbox_content_right_colorbox span.red_box{
			background-color: #74D6DD;
		}
		</style>
	</head>

	<body onload="validate()">
		<div id="mainFrame">
			<!--头部与导航start-->
			<div id="hbox">
				<div class="hbox_content_win">
					<div class="hbox_content clearfloat">
						<div class="hbox_content_left clearfloat">
							<a href="http://www.uileader.com" target="_blank"></a>
							<span>
								QUICK UI
								<b>v4.0.4</b>
							</span>
						</div>
						<div class="hbox_content_right clearfloat">
							<ul class="clearfloat hbox_content_right_menu">
								<li><i></i>表单</li>
								<li><i></i>表格</li>
								<li><i></i>导航于布局</li>
								<li><i></i>特效</li>
								<li><i></i>资源</li>
							</ul>
							<a href="javascript:;" class="right_tip clearfloat"></a>
							<div class="hbox_content_right_colorbox clearfloat">
								换肤：
								<span class="blue_box" onclick="changeSkin('blue','skin')" ></span>
								<span class="green_box" onclick="changeSkin('green','green')" ></span>
								<span class="red_box" onclick="changeSkin('mintgreen','mint')" ></span>
							</div>
							<a href="javascript:;" class="right_toggle">
								<span></span>
								<ul class="right_toggle_content">
									<li class="clearfloat right_toggle_fullscreen"><i></i><div>全屏</div></li>
									<li class="clearfloat right_toggle_lock_screen" onclick="lockScreen()"><i></i>锁屏</li>
									<li class="clearfloat right_toggle_sign_out" onclick="exitHandler()"><i></i>退出</li>
								</ul>
							</a>
						</div>
					</div>
				</div>
			</div>
			<div class="table_win">
				<table width="100%" cellpadding="0" cellspacing="0" class="table_border0">
					<tr>
						<!--左侧区域start-->
						<td id="hideCon" class="ver01 ali01">
							<div id="lbox">
								<div id="lbox_topcenter">
									<div class="bs_left_shrink clearfloat">
										<i class="active"></i>
									</div>
								</div>
								<div id="lbox_middlecenter" class="">
									<div id="bs_left" class="lbox_middlecenter_content">
										<!--左侧导航内容-->
										<!--收缩导航-->
										
									</div>
								</div>
								<div id="lbox_bottomcenter">

								</div>
							</div>
						</td>
						<!--左侧区域end-->

						<!--右侧区域start-->
						<td class="ali01 ver01" width="100%">
							<div id="rbox">
								<div id="rbox_topcenter">
									<div class="rbox_topcenter">
										<span>表单</span>
									</div>
								</div>
								<div id="rbox_middlecenter">
									<div id="bs_right">
										<IFRAME style="box-sizing: border-box; background: white;" height="100%" width="100%" frameBorder=0 id=frmright name=frmright src="/quickui/system/main/guide1.jsp" allowTransparency="true"></IFRAME>
									</div>
								</div>
								<div id="rbox_bottomcenter">

								</div>
							</div>
							<!--<div class="tip_allcontent_win">
								<div class="tip_allcontent">
									<div class="all_title clearfloat">
										<span>消息</span>
										<em class="all_close"></em>
									</div>
									<div class="all_content">
										<ul>
											<li><a href="javascript:;">待办</a></li>
											<li><a href="javascript:;">通知</a></li>
											<li><a href="javascript:;">部门</a></li>
										</ul>
									</div>
								</div>
							</div>-->
						</td>
						<!--右侧区域end-->
					</tr>
				</table>
			</div>
			<div id="fbox"></div>
			
		</div>
		<script type="text/javascript">
			var sNodes=[
	{ "id":"1", "parentId":"0", "name":"表单","url":"guide1.jsp", "isParent": "true","icon": "./skin/index_icon.png","backgroundPosition":"-111px -48px"},
	
	{ "id":"101", "parentId":"1", "name":"表单元素","icon": "./skin/index_icon.png","backgroundPosition":"-111px -48px"},
	{ "id":"1101", "parentId":"101", "name":"文本框 密码框","url":"/quickui/sample/form/textinput-password.jsp", "target":"frmright","title":"textinput/password","icon": "./skin/index_icon.png","backgroundPosition":"-160px -48px"},
	{ "id":"1102", "parentId":"101", "name":"文本域","url":"/quickui/sample/form/textarea.jsp", "target":"frmright","title":"textarea","icon": "./skin/index_icon.png","backgroundPosition":"-96px -48px"},
	{ "id":"1103", "parentId":"101", "name":"基本按钮","url":"/quickui/sample/form/button.jsp", "target":"frmright","title":"button","icon": "./skin/index_icon.png","backgroundPosition":"-128px -48px"},
	{ "id":"1104", "parentId":"101", "name":"单选按钮","url":"/quickui/sample/form/radio.jsp", "target":"frmright","title":"radio","icon": "./skin/index_icon.png","backgroundPosition":"-209px -48px"},
	{ "id":"1105", "parentId":"101", "name":"多选按钮","url":"/quickui/sample/form/checkbox.jsp", "target":"frmright","title":"checkbox","icon": "./skin/index_icon.png","backgroundPosition":"-176px -32px"},
	{ "id":"1106", "parentId":"101", "name":"单选下拉框*","url":"/quickui/sample/form/select-single.jsp", "target":"frmright","title":"select","icon": "./skin/index_icon.png","backgroundPosition":"-157px -81px"},
	{ "id":"1107", "parentId":"101", "name":"过滤下拉框*","url":"/quickui/sample/form/select-suggestion.jsp", "target":"frmright","title":"selectSuggestion","icon": "./skin/index_icon.png","backgroundPosition":"-157px -81px"},
	{ "id":"1108", "parentId":"101", "name":"树形下拉框*","url":"/quickui/sample/form/select-tree.jsp", "target":"frmright","title":"selectTree","icon": "./skin/index_icon.png","backgroundPosition":"-157px -81px"},
	{ "id":"1109", "parentId":"101", "name":"多选下拉框*","url":"/quickui/sample/form/select-multi.jsp", "target":"frmright","title":"selectTree multi","icon": "./skin/index_icon.png","backgroundPosition":"-157px -81px"},
	{ "id":"1110", "parentId":"101", "name":"组合下拉框*","url":"/quickui/sample/form/select-custom.jsp", "target":"frmright","title":"selectCustom","icon": "./skin/index_icon.png","backgroundPosition":"-157px -81px"},
	{ "id":"1112", "parentId":"101", "name":"自动提示框*","url":"/quickui/sample/form/suggestion.jsp", "target":"frmright","title":"suggestion","icon": "./skin/index_icon.png","backgroundPosition":"-172px -81px"},
	{ "id":"1113", "parentId":"101", "name":"日期选择器","url":"/quickui/sample/form/date.jsp", "target":"frmright","title":"date","icon": "./skin/index_icon.png","backgroundPosition":"-144px -48px"},
	{ "id":"1114", "parentId":"101", "name":"双向选择器*","url":"/quickui/sample/form/lister.jsp", "target":"frmright","title":"lister","icon": "./skin/index_icon.png","backgroundPosition":"-48px -48px"},
	{ "id":"1115", "parentId":"101", "name":"树形双选器*","url":"/quickui/sample/form/lister-tree.jsp", "target":"frmright","title":"listerTree","icon": "./skin/index_icon.png","backgroundPosition":"-48px -48px"},
	{ "id":"1116", "parentId":"101", "name":"颜色选择器","url":"/quickui/sample/form/color.jsp", "target":"frmright","title":"color","icon": "./skin/index_icon.png","backgroundPosition":"-64px -48px"},
	{ "id":"1117", "parentId":"101", "name":"数字步进器","url":"/quickui/sample/form/stepper.jsp", "target":"frmright","title":"stepter","icon": "./skin/index_icon.png","backgroundPosition":"-193px -48px"},
	{ "id":"1118", "parentId":"101", "name":"软键盘控件","url":"/quickui/sample/form/keypad.jsp", "target":"frmright","title":"keypad","icon": "./skin/index_icon.png","backgroundPosition":"-32px -48px"},
	{ "id":"1119", "parentId":"101", "name":"评星级控件","url":"/quickui/sample/form/rating.jsp", "target":"frmright","title":"rating","icon": "./skin/index_icon.png","backgroundPosition":"-16px -48px"},
	{ "id":"1120", "parentId":"101", "name":"异步上传控件*","url":"/quickui/sample/form/fileupload.jsp", "target":"frmright","title":"fileUpload","icon": "./skin/index_icon.png","backgroundPosition":"0px -48px"},
	{ "id":"1121", "parentId":"101", "name":"上传文件列表*","url":"/quickui/sample/form/filelist.jsp", "target":"frmright","title":"fileList","icon": "./skin/index_icon.png","backgroundPosition":"0px -48px"},
	{ "id":"1122", "parentId":"101", "name":"同步上传控件*","url":"/quickui/sample/form/file.jsp", "target":"frmright","title":"file","icon": "./skin/index_icon.png","backgroundPosition":"0px -48px"},
	{ "id":"1123", "parentId":"101", "name":"条件过滤器1*","url":"/quickui/sample/form/filter.jsp", "target":"frmright","title":"filter","icon": "./skin/index_icon.png","backgroundPosition":"-207px -32px"},
	{ "id":"1124", "parentId":"101", "name":"条件过滤器2","url":"/quickui/sample/form/condition.jsp", "target":"frmright","title":"condition","icon": "./skin/index_icon.png","backgroundPosition":"-207px -32px"},
	{ "id":"1125", "parentId":"101", "name":"条件过滤器3","url":"/quickui/sample/form/condition-nav.jsp", "target":"frmright","title":"conditionNav","icon": "./skin/index_icon.png","backgroundPosition":"-207px -32px"},
	
	{ "id":"102", "parentId":"1", "name":"综合表单","icon": "./skin/index_icon.png","backgroundPosition":"-208px -64px"},
	{ "id":"1201", "parentId":"102", "name":"表单布局","url":"/quickui/sample/fullform/layout.jsp", "target":"frmright","title":"table","icon": "./skin/index_icon.png","backgroundPosition":"-178px -64px"},
	{ "id":"1202", "parentId":"102", "name":"表单验证*","url":"/quickui/sample/fullform/validation.jsp", "target":"frmright","title":"validation","icon": "./skin/index_icon.png","backgroundPosition":"-114px -66px"},
	{ "id":"1203", "parentId":"102", "name":"表单验证-直接提示*","url":"/quickui/sample/fullform/validation2.jsp", "target":"frmright","title":"validation","icon": "./skin/index_icon.png","backgroundPosition":"-114px -66px"},
	{ "id":"1204", "parentId":"102", "name":"表单响应式布局","url":"/quickui/sample/fullform/layout-auto.jsp", "target":"frmright","title":"formLayout","icon": "./skin/index_icon.png","backgroundPosition":"-192px -64px"},
	{ "id":"1205", "parentId":"102", "name":"表单拆分","url":"/quickui/sample/fullform/step.jsp", "target":"frmright","title":"stepForm","icon": "./skin/index_icon.png","backgroundPosition":"-161px -64px"},
	{ "id":"1206", "parentId":"102", "name":"异步提交*","url":"/quickui/sample/fullform/submit-ajax.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-130px -64px"},
	{ "id":"1207", "parentId":"102", "name":"异步弹窗提交","url":"/quickui/sample/fullform/submit-ajax-pop.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-130px -64px"},
	{ "id":"1209", "parentId":"102", "name":"异步编辑*","url":"/quickui/sample/fullform/edit-ajax-notag.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-112px -32px"},
	{ "id":"1210", "parentId":"102", "name":"动态创建表单并赋值","url":"/quickui/sample/fullform/create-form.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-145px -64px"},
	{ "id":"1211", "parentId":"102", "name":"条件过滤器综合示例1","url":"/quickui/sample/fullform/filter-demo.jsp", "target":"frmright","title":"filter","icon": "./skin/index_icon.png","backgroundPosition":"-207px -32px"},
	{ "id":"1212", "parentId":"102", "name":"条件过滤器综合示例2","url":"/quickui/sample/fullform/condition-demo.jsp", "target":"frmright","title":"condition","icon": "./skin/index_icon.png","backgroundPosition":"-207px -32px"},
	{ "id":"1213", "parentId":"102", "name":"条件过滤器综合示例3","url":"/quickui/sample/fullform/condition-nav-demo.jsp", "target":"frmright","title":"conditionNav","icon": "./skin/index_icon.png","backgroundPosition":"-207px -32px"},
	
	
	
	
	
	{ "id":"2", "parentId":"0", "name":"表格","url":"guide2.jsp","icon": "./skin/index_icon.png","backgroundPosition":"-48px -64px"},
	
	{ "id":"201", "parentId":"2", "name":"grid基本特性","icon": "./skin/index_icon.png","backgroundPosition":"-48px -64px"},
	{ "id":"2101", "parentId":"201", "name":"完整应用示例*","url":"/quickui/sample/datagrid/basic-url.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2102", "parentId":"201", "name":"使用远程data*","url":"/quickui/sample/datagrid/basic-data.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2103", "parentId":"201", "name":"使用本地数据","url":"/quickui/sample/datagrid/basic-local.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2104", "parentId":"201", "name":"分页记忆行号","url":"/quickui/sample/datagrid/rownumber-memory.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2133", "parentId":"201", "name":"本地数据滚动分页","url":"/quickui/sample/datagrid/scroll-paging-local.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2134", "parentId":"201", "name":"远程数据滚动分页*","url":"/quickui/sample/datagrid/scroll-paging-url.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},

	{ "id":"2105", "parentId":"201", "name":"长文本提示","url":"/quickui/sample/datagrid/longtext.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2106", "parentId":"201", "name":"内容格式化","url":"/quickui/sample/datagrid/format.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2107", "parentId":"201", "name":"对行列渲染","url":"/quickui/sample/datagrid/render.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2108", "parentId":"201", "name":"锁定列","url":"/quickui/sample/datagrid/lock.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2109", "parentId":"201", "name":"单元格操作","url":"/quickui/sample/datagrid/cell.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2110", "parentId":"201", "name":"弹窗中的表格","url":"/quickui/sample/datagrid/basic-pop.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2111", "parentId":"201", "name":"分页记忆选中项","url":"/quickui/sample/datagrid/checkrows-memory.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2112", "parentId":"201", "name":"动态选中","url":"/quickui/sample/datagrid/checkrows.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2132", "parentId":"201", "name":"点击行选中","url":"/quickui/sample/datagrid/checkrows-click.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},

	{ "id":"2113", "parentId":"201", "name":"表格动态操作","url":"/quickui/sample/datagrid/dynamic.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2114", "parentId":"201", "name":"动态切换表格","url":"/quickui/sample/datagrid/change-grid.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2115", "parentId":"201", "name":"动态设置url*","url":"/quickui/sample/datagrid/seturl.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2116", "parentId":"201", "name":"滚动条位置","url":"/quickui/sample/datagrid/scroll-to.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2117", "parentId":"201", "name":"滚动到特定行","url":"/quickui/sample/datagrid/scroll-to-row.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2118", "parentId":"201", "name":"工具栏","url":"/quickui/sample/datagrid/toolbar.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2119", "parentId":"201", "name":"表格右键菜单","url":"/quickui/sample/datagrid/context-menu.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2120", "parentId":"201", "name":"表头过滤","url":"/quickui/sample/datagrid/head-filter.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2131", "parentId":"201", "name":"大数据测试","url":"/quickui/sample/datagrid/large-data.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},

	{ "id":"202", "parentId":"2", "name":"grid综合示例","icon": "./skin/index_icon.png","backgroundPosition":"-50px 0px"},
	
	{ "id":"2201", "parentId":"202", "name":"自定义样式","url":"/quickui/sample/datagrid/custom-style.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2202", "parentId":"202", "name":"跨列附加一行","url":"/quickui/sample/datagrid/append-row.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2203", "parentId":"202", "name":"单元格信息提示","url":"/quickui/sample/datagrid/cell-tip.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2204", "parentId":"202", "name":"选中显示条件","url":"/quickui/sample/datagrid/auto-condition.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2205", "parentId":"202", "name":"点击单元格展开详情","url":"/quickui/sample/datagrid/cell-detail.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2206", "parentId":"202", "name":"打开选项卡查看详情","url":"/quickui/sample/datagrid/cell-detail2.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2213", "parentId":"202", "name":"最大化表格","url":"/quickui/sample/datagrid/max-grid.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},	
	{ "id":"2214", "parentId":"202", "name":"列数定制","url":"/quickui/sample/datagrid/column-order.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2215", "parentId":"202", "name":"数据筛选","url":"/quickui/sample/datagrid/grid-condition.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2232", "parentId":"202", "name":"数据筛选2","url":"/quickui/sample/datagrid/grid-filter.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2217", "parentId":"202", "name":"表格与卡片切换","url":"/quickui/sample/datagrid/grid-card.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2216", "parentId":"202", "name":"双表格","url":"/quickui/sample/datagrid/two-grid.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2207", "parentId":"202", "name":"表格双选器(小型)*","url":"/quickui/sample/datagrid/lister-grid.jsp", "target":"frmright","title":"listerGrid","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2208", "parentId":"202", "name":"表格双选器(左右满屏)*","url":"/quickui/sample/datagrid/lister-grid2.jsp", "target":"frmright","title":"listerGrid","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2209", "parentId":"202", "name":"表格双选器(上下满屏)*","url":"/quickui/sample/datagrid/lister-grid3.jsp", "target":"frmright","title":"listerGrid","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
		{ "id":"2230", "parentId":"202", "name":"组织机构维护*","url":"/quickui/sample/unit/org-management.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2231", "parentId":"202", "name":"系统用户管理*","url":"/quickui/sample/unit/user-management.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},

	


	{ "id":"205", "parentId":"2", "name":"grid树形表与父子表*","icon": "./skin/index_icon.png","backgroundPosition":"-50px 0px"},
	{ "id":"2123", "parentId":"205", "name":"树形表格*","url":"/quickui/sample/datagrid/treegrid.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2124", "parentId":"205", "name":"树形表格-个性设置","url":"/quickui/sample/datagrid/treegrid-dynamic.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2125", "parentId":"205", "name":"树形表格-异步加载*","url":"/quickui/sample/datagrid/treegrid-ajax.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2126", "parentId":"205", "name":"父子表格*","url":"/quickui/sample/datagrid/detailgrid.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2210", "parentId":"205", "name":"父子表-操作子表*","url":"/quickui/sample/datagrid/detailgrid-edit.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2211", "parentId":"205", "name":"父子表-自定义内容","url":"/quickui/sample/datagrid/detailgrid-custom.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2212", "parentId":"205", "name":"父子表-自定义样式","url":"/quickui/sample/datagrid/detailgrid-style.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	

		
	
	{ "id":"203", "parentId":"2", "name":"table表格*","icon": "./skin/index_icon.png","backgroundPosition":"-97px -66px"},
	{ "id":"2301", "parentId":"203", "name":"特性举例","url":"/quickui/sample/table/guide.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2302", "parentId":"203", "name":"多表头示例","url":"/quickui/sample/table/multiheader.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2303", "parentId":"203", "name":"表格动态操作","url":"/quickui/sample/table/dynamic.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2304", "parentId":"203", "name":"树形表格*","url":"/quickui/sample/table/treetable.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2305", "parentId":"203", "name":"父子表格*","url":"/quickui/sample/table/detailtable.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},

	{ "id":"207", "parentId":"2", "name":"数据打印", "isParent": "true","icon": "./skin/index_icon.png","backgroundPosition":"-155px -97px"},
	{ "id":"2701", "parentId":"207", "name":"列表打印","url":"/quickui/sample/datagrid/print-grid.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2702", "parentId":"207", "name":"表格打印","url":"/quickui/sample/datagrid/print-table.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	
	{ "id":"7", "parentId":"0", "name":"表格(高级)","url":"guide7.jsp", "isParent": "true","icon": "./skin/index_icon.png","backgroundPosition":"-180px 0px"},


	{ "id":"206", "parentId":"7", "name":"报表展现","icon": "./skin/index_icon.png","backgroundPosition":"-50px 0px"},
	{ "id":"2601", "parentId":"206", "name":"复杂表头","url":"/quickui/sample/report/multiheader.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2602", "parentId":"206", "name":"跨行示例","url":"/quickui/sample/report/multibody1.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2603", "parentId":"206", "name":"跨列示例","url":"/quickui/sample/report/multibody2.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2604", "parentId":"206", "name":"跨行+跨列","url":"/quickui/sample/report/multibody3.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2605", "parentId":"206", "name":"数据分组","url":"/quickui/sample/report/group.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2606", "parentId":"206", "name":"数据汇总","url":"/quickui/sample/report/summary.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2607", "parentId":"206", "name":"分组+汇总","url":"/quickui/sample/report/group-summary.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2608", "parentId":"206", "name":"明细表","url":"/quickui/sample/report/detail.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2609", "parentId":"206", "name":"条件分片","url":"/quickui/sample/report/head-group.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2610", "parentId":"206", "name":"条件分片+锁定列","url":"/quickui/sample/report/head-group-frozen.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2611", "parentId":"206", "name":"分级展现1","url":"/quickui/sample/report/grade1.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2612", "parentId":"206", "name":"分级展现2","url":"/quickui/sample/report/grade2.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2613", "parentId":"206", "name":"分级展现3","url":"/quickui/sample/report/grade3.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2614", "parentId":"206", "name":"图形渲染1","url":"/quickui/sample/report/graphic1.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2615", "parentId":"206", "name":"图形渲染2","url":"/quickui/sample/report/graphic2.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2616", "parentId":"206", "name":"报表录入","url":"/quickui/sample/report/report-edit.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2616", "parentId":"206", "name":"多标签报表","url":"/quickui/sample/report/report-edit2.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},

	{ "id":"204", "parentId":"7", "name":"即时编辑*","icon": "./skin/index_icon.png","backgroundPosition":"-147px -15px"},
	{ "id":"2401", "parentId":"204", "name":"点击编辑-基本*","url":"/quickui/sample/unit/edit.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2402", "parentId":"204", "name":"点击编辑-高级*","url":"/quickui/sample/unit/edit2.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2403", "parentId":"204", "name":"点击编辑-树形表1*","url":"/quickui/sample/unit/edit-treegrid.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2404", "parentId":"204", "name":"点击编辑-树形表2*","url":"/quickui/sample/unit/edit-treegrid2.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2405", "parentId":"204", "name":"点击编辑-excel*","url":"/quickui/sample/unit/edit-excel.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2406", "parentId":"204", "name":"点击编辑-详情页*","url":"/quickui/sample/unit/edit-info.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2407", "parentId":"204", "name":"整行编辑*","url":"/quickui/sample/unit/edit-row.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2408", "parentId":"204", "name":"整行编辑-下拉框联动*","url":"/quickui/sample/unit/edit-row-ld.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2409", "parentId":"204", "name":"整行编辑-下拉框联动2*","url":"/quickui/sample/unit/edit-row-ld2.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2410", "parentId":"204", "name":"整行编辑-父子表*","url":"/quickui/sample/unit/edit-row-detail.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2411", "parentId":"204", "name":"展开表单编辑1*","url":"/quickui/sample/unit/edit-detail.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"2412", "parentId":"204", "name":"展开表单编辑2*","url":"/quickui/sample/unit/edit-detail-form.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	
	{ "id":"3", "parentId":"0", "name":"导航与布局","url":"guide3.jsp", "isParent": "true","icon": "./skin/index_icon.png","backgroundPosition":"-180px 0px"},
	{ "id":"401", "parentId":"3", "name":"提示类组件","icon": "./skin/index_icon.png","backgroundPosition":"-50px -16px"},
	{ "id":"4101", "parentId":"401", "name":"提示 toast","url":"/quickui/sample/popup/toast.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-66px 0px"},
	{ "id":"4102", "parentId":"401", "name":"通知 notice","url":"/quickui/sample/popup/notice.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-113px -16px"},
	{ "id":"4103", "parentId":"401", "name":"消息 message","url":"/quickui/sample/popup/message.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-98px -0px"},
	{ "id":"4104", "parentId":"401", "name":"鼠标提示 tooltip","url":"/quickui/sample/popup/tooltip.jsp", "target":"frmright","title":"title","icon": "./skin/index_icon.png","backgroundPosition":"-97px -16px"},
	{ "id":"4105", "parentId":"401", "name":"弹窗 dialog","url":"/quickui/sample/popup/dialog.jsp", "target":"frmright","title":"Dialog","icon": "./skin/index_icon.png","backgroundPosition":"-66px -16px"},
	
		
	{ "id":"302", "parentId":"3", "name":"导航组件","icon": "./skin/index_icon.png","backgroundPosition":"-195px 0px"},
	{ "id":"3201", "parentId":"302", "name":"弹出菜单","url":"/quickui/sample/nav/menu-popup.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-186px -81px"},
	{ "id":"3202", "parentId":"302", "name":"右键菜单","url":"/quickui/sample/nav/menu-rightclick.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-16px -16px"},
	{ "id":"3203", "parentId":"302", "name":"基本选项卡","url":"/quickui/sample/nav/tab-basic.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-32px -80px"},
	{ "id":"3204", "parentId":"302", "name":"基本选项卡-样式2","url":"/quickui/sample/nav/tab-basic-modern.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-32px -80px"},
	{ "id":"3205", "parentId":"302", "name":"基本选项卡-流程","url":"/quickui/sample/nav/tab-basic-process.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-32px -80px"},
	{ "id":"3206", "parentId":"302", "name":"动态选项卡","url":"/quickui/sample/nav/tab-dynamic.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-32px -80px"},
	{ "id":"3207", "parentId":"302", "name":"抽屉容器","url":"/quickui/sample/nav/accordion.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-96px -80px"},
	{ "id":"3208", "parentId":"302", "name":"图标工具栏","url":"/quickui/sample/nav/icon-toolbar.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-80px -80px"},
	{ "id":"3209", "parentId":"302", "name":"数字分页控件","url":"/quickui/sample/nav/paging-number.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-120px -81px"},
	{ "id":"3210", "parentId":"302", "name":"箭头分页控件","url":"/quickui/sample/nav/paging-arrow.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"0px -80px"},

	{ "id":"301", "parentId":"3", "name":"树组件*","icon": "./skin/index_icon.png","backgroundPosition":"0px -32px"},
	{ "id":"3101", "parentId":"301", "name":"基本使用*","url":"/quickui/sample/tree/basic.jsp", "target":"frmright","title":"基本使用","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3102", "parentId":"301", "name":"异步加载数据*","url":"/quickui/sample/tree/async.jsp", "target":"frmright","title":"异步加载数据","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3103", "parentId":"301", "name":"添加复选框","url":"/quickui/sample/tree/checktree.jsp", "target":"frmright","title":"添加复选框","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3104", "parentId":"301", "name":"树的拖拽","url":"/quickui/sample/tree/dragdrop.jsp", "target":"frmright","title":"树的拖拽","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3105", "parentId":"301", "name":"树其他DOM节点拖拽","url":"/quickui/sample/tree/dragdrop-other.jsp", "target":"frmright","title":"树其他DOM节点拖拽","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3106", "parentId":"301", "name":"树的编辑","url":"/quickui/sample/tree/edit.jsp", "target":"frmright","title":"树的编辑","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3107", "parentId":"301", "name":"右键菜单","url":"/quickui/sample/tree/contextmenu.jsp", "target":"frmright","title":"右键菜单","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3108", "parentId":"301", "name":"自定义外观","url":"/quickui/sample/tree/style-custom.jsp", "target":"frmright","title":"自定义外观","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3109", "parentId":"301", "name":"添加自定义控件","url":"/quickui/sample/tree/diy-dom.jsp", "target":"frmright","title":"添加自定义控件","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3110", "parentId":"301", "name":"节点隐藏","url":"/quickui/sample/tree/node-hide.jsp", "target":"frmright","title":"节点隐藏","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3111", "parentId":"301", "name":"树的分页*","url":"/quickui/sample/tree/page.jsp", "target":"frmright","title":"树的分页","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},

	
	
	{ "id":"304", "parentId":"3", "name":"综合布局", "isParent": "true","icon": "./skin/index_icon.png","backgroundPosition":"-178px -64px"},

	{ "id":"3401", "parentId":"304", "name":"纵向导航(一级)","url":"/quickui/sample/layout/v-single.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3402", "parentId":"304", "name":"纵向导航(一级滚动)","url":"/quickui/sample/layout/v-single-scroll.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3403", "parentId":"304", "name":"纵向导航(二级)","url":"/quickui/sample/layout/v-double.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3404", "parentId":"304", "name":"横向导航(基本选项卡)","url":"/quickui/sample/layout/h-basictab.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},	
	{ "id":"3405", "parentId":"304", "name":"横向导航(流程选项卡)","url":"/quickui/sample/layout/h-processtab.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3406", "parentId":"304", "name":"横向导航(滑动)","url":"/quickui/sample/layout/h-single-scroll.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3407", "parentId":"304", "name":"横向导航(tab含表格)","url":"/quickui/sample/layout/h-basictab-grid.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3408", "parentId":"304", "name":"横向导航(tab含表格2)","url":"/quickui/sample/layout/h-basictab-grid2.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3409", "parentId":"304", "name":"纵向+横向导航(动态tab)","url":"/quickui/sample/layout/hv-dynamictab.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3410", "parentId":"304", "name":"分隔条布局(左右)","url":"/quickui/sample/layout/spliter-h.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3411", "parentId":"304", "name":"分隔条布局(上下)","url":"/quickui/sample/layout/spliter-v.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3412", "parentId":"304", "name":"分隔条布局(综合)","url":"/quickui/sample/layout/spliter-full.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3413", "parentId":"304", "name":"分隔条布局(弹窗)","url":"/quickui/sample/layout/spliter-popup.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3414", "parentId":"304", "name":"资源维护","url":"/quickui/sample/layout/nav-management.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3415", "parentId":"304", "name":"用户管理","url":"/quickui/sample/layout/user-management.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3416", "parentId":"304", "name":"分配资源","url":"/quickui/sample/layout/user-assignment.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3417", "parentId":"304", "name":"新窗口打开页面(分离模式)","url":"/quickui/sample/split/split-index.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3418", "parentId":"304", "name":"框架右侧嵌入第三方页面","url":"http://www.uileader.com", "target":"frmright","showProgess":false,"icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	

	
	
	{ "id":"4", "parentId":"0", "name":"特效与模板","url":"guide4.jsp", "isParent": "true","icon": "./skin/index_icon.png","backgroundPosition":"-122px -97px"},


	{ "id":"402", "parentId":"4", "name":"图片特效", "isParent": "true","icon": "./skin/index_icon.png","backgroundPosition":"-16px -96px"},
	{ "id":"4201", "parentId":"402", "name":"图片列表","url":"/quickui/sample/pic/list.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-131px 0px"},
	{ "id":"4202", "parentId":"402", "name":"图片标题特效","url":"/quickui/sample/pic/title.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-114px 0px"},
	{ "id":"4203", "parentId":"402", "name":"图片预览", "url":"/quickui/sample/pic/preview.jsp","target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-32px -96px"},
	{ "id":"4204", "parentId":"402", "name":"图片部分放大", "url":"/quickui/sample/pic/zoom.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-64px -96px"},
	{ "id":"4205", "parentId":"402", "name":"图片滚动", "url":"/quickui/sample/pic/scroll.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-80px -96px"},
	{ "id":"4206", "parentId":"402", "name":"图片轮播", "url":"/quickui/sample/pic/swiper.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"0px -96px"},
	{ "id":"4207", "parentId":"402", "name":"相册", "url":"/quickui/sample/pic/gallery.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"0px -96px"},
	{ "id":"4208", "parentId":"402", "name":"图片瀑布流", "url":"/quickui/sample/pic/waterfall.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"0px -96px"},

	
	{ "id":"403", "parentId":"4", "name":"文本特效", "isParent": "true","icon": "./skin/index_icon.png","backgroundPosition":"-98px -96px"},
	{ "id":"4301", "parentId":"403", "name":"文本截取", "url":"/quickui/sample/text/slice.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"4302", "parentId":"403", "name":"文章列表", "url":"/quickui/sample/text/list.jsp" ,"target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"4303", "parentId":"403", "name":"文本滚动", "url":"/quickui/sample/text/scroll.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	
	{ "id":"404", "parentId":"4", "name":"拖拽特效", "isParent": "true","icon": "./skin/index_icon.png","backgroundPosition":"-80px -32px"},
	{ "id":"4401", "parentId":"404", "name":"拖拽改变尺寸", "url":"/quickui/sample/drag/drag-resize.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"0px -112px"},
	{ "id":"4402", "parentId":"404", "name":"拖放与接收", "url":"/quickui/sample/drag/drag-drop.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-34px -112px"},
	{ "id":"4403", "parentId":"404", "name":"图标排序", "url":"/quickui/sample/drag/drag-sort.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-147px 0px"},
	{ "id":"4404", "parentId":"404", "name":"列表排序", "url":"/quickui/sample/drag/drag-sort-list.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-165px 0px"},
	
	{ "id":"405", "parentId":"4", "name":"其他特效", "isParent": "true","icon": "./skin/index_icon.png","backgroundPosition":"-114px -112px"},
	{ "id":"4501", "parentId":"405", "name":"加入收藏","url":"/quickui/sample/other/addfav.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-50px -112px"},
	{ "id":"4502", "parentId":"405", "name":"平滑锚点链接", "url":"/quickui/sample/other/smoothlink.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-16px -64px"},
	{ "id":"4503", "parentId":"405", "name":"遮罩", "url":"/quickui/sample/other/mask.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-66px -112px"},
	{ "id":"4504", "parentId":"405", "name":"进度条", "url":"/quickui/sample/other/progressbar.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-98px -112px"},

	{ "id":"303", "parentId":"4", "name":"常用模板","icon": "./skin/index_icon.png","backgroundPosition":"-180px 0px"},
	{ "id":"3303", "parentId":"303", "name":"欢迎模板","url":"/quickui/sample/templete/welcome.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3304", "parentId":"303", "name":"操作向导模板","url":"/quickui/sample/templete/open-process.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3305", "parentId":"303", "name":"操作结果","url":"/quickui/sample/templete/result.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3306", "parentId":"303", "name":"404效果","url":"/quickui/sample/templete/404.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3307", "parentId":"303", "name":"404效果2","url":"/quickui/sample/templete/404-2.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3308", "parentId":"303", "name":"九宫格图标","url":"/quickui/sample/templete/icon-grid.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"3309", "parentId":"303", "name":"九宫格图标(拖动)","url":"/quickui/sample/templete/icon-grid2.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
		
	{ "id":"5", "parentId":"0", "name":"图形化","url":"guide5.jsp", "isParent": "true","icon": "./skin/index_icon.png","backgroundPosition":"-207px -80px"},
	{ "id":"501", "parentId":"5", "name":"地图","icon": "./skin/index_icon.png","backgroundPosition":"-131px -14px"},
	{ "id":"5101", "parentId":"501", "name":"基础示例","url":"/quickui/sample/graphical/map1.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5102", "parentId":"501", "name":"数据控制颜色","url":"/quickui/sample/graphical/map2.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5103", "parentId":"501", "name":"外部控制","url":"/quickui/sample/graphical/map3.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5104", "parentId":"501", "name":"标记点操作","url":"/quickui/sample/graphical/map4.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5105", "parentId":"501", "name":"其他省市地图","url":"/quickui/sample/graphical/map5.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},	
	{ "id":"5106", "parentId":"501", "name":"世界地图","url":"/quickui/sample/graphical/map6.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5107", "parentId":"501", "name":"切换子地图","url":"/quickui/sample/graphical/map7.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	
	{ "id":"502", "parentId":"5", "name":"进度","icon": "./skin/index_icon.png","backgroundPosition":"-173px -97px"},
	{ "id":"5201", "parentId":"502", "name":"进度环1","url":"/quickui/sample/graphical/progress-ring.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5202", "parentId":"502", "name":"进度环2","url":"/quickui/sample/graphical/progress-ring2.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5203", "parentId":"502", "name":"进度环3","url":"/quickui/sample/graphical/progress-ring3.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5204", "parentId":"502", "name":"进度条1","url":"/quickui/sample/graphical/progress-rect.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5205", "parentId":"502", "name":"进度条2","url":"/quickui/sample/graphical/progress-rect2.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5206", "parentId":"502", "name":"仪表盘","url":"/quickui/sample/graphical/dashboard.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	
	{ "id":"503", "parentId":"5", "name":"图表","icon": "./skin/index_icon.png","backgroundPosition":"-190px -95px"},
	{ "id":"5301", "parentId":"503", "name":"柱状图","url":"/quickui/sample/graphical/chart-column.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5302", "parentId":"503", "name":"折线图","url":"/quickui/sample/graphical/chart-line.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5303", "parentId":"503", "name":"曲线图","url":"/quickui/sample/graphical/chart-curve.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5304", "parentId":"503", "name":"曲面图","url":"/quickui/sample/graphical/chart-area.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5305", "parentId":"503", "name":"饼状图","url":"/quickui/sample/graphical/chart-pie.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5306", "parentId":"503", "name":"环形图","url":"/quickui/sample/graphical/chart-ring.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5307", "parentId":"503", "name":"半环图","url":"/quickui/sample/graphical/chart-ring-half.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5308", "parentId":"503", "name":"多环图","url":"/quickui/sample/graphical/chart-ring-multi.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5309", "parentId":"503", "name":"雷达图","url":"/quickui/sample/graphical/chart-radar.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5310", "parentId":"503", "name":"散点图","url":"/quickui/sample/graphical/chart-dots.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	

	{ "id":"504", "parentId":"5", "name":"流程与关系","icon": "./skin/index_icon.png","backgroundPosition":"-207px -96px"},
	{ "id":"5401", "parentId":"504", "name":"时间轴","url":"/quickui/sample/graphical/timeline1.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5402", "parentId":"504", "name":"时间轴综合示例","url":"/quickui/sample/graphical/timeline2.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5403", "parentId":"504", "name":"鱼骨图","url":"/quickui/sample/graphical/fishbone.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5404", "parentId":"504", "name":"关系图","url":"/quickui/sample/graphical/relation.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	{ "id":"5405", "parentId":"504", "name":"拓扑图","url":"/quickui/sample/graphical/mindmap.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},
	
	
	
	{ "id":"506", "parentId":"5", "name":"其他","icon": "./skin/index_icon.png","backgroundPosition":"-205px -113px"},
	{ "id":"5601", "parentId":"506", "name":"文档管理","url":"/quickui/sample/graphical/computer.jsp", "target":"frmright","title":"toast","icon": "./skin/index_icon.png","backgroundPosition":"-192px -32px"},

	
	{ "id":"6", "parentId":"0", "name":"资源","url":"guide6.jsp", "isParent": "true","icon": "./skin/index_icon.png","backgroundPosition":"-139px -97px"},
	
	{ "id":"601", "parentId":"6", "name":"图标库","icon": "./skin/index_icon.png","backgroundPosition":"-1px 0"},
	{ "id":"6101", "parentId":"601", "name":"小图标索引","url":"/quickui/sample/icons/icons.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"6102", "parentId":"601", "name":"小图标索引-按钮","url":"/quickui/sample/icons/icons-button.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"6105", "parentId":"601", "name":"扁平图标索引","url":"/quickui/sample/icons/icons-flat.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"6106", "parentId":"601", "name":"扁平图标组合","url":"/quickui/sample/icons/icons-group.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"6103", "parentId":"601", "name":"fontawsome使用","url":"/quickui/sample/icons/icons-awesome-use.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"6104", "parentId":"601", "name":"fontawsome索引","url":"/quickui/sample/icons/icons-awesome-index.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},


	{ "id":"505", "parentId":"6", "name":"页面组件结合图表","icon": "./skin/index_icon.png","backgroundPosition":"-207px -80px"},
	{ "id":"5501", "parentId":"505", "name":"上图下表1","url":"/quickui/sample/graphical/grid-chart1.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"5502", "parentId":"505", "name":"上图下表2","url":"/quickui/sample/graphical/grid-chart2.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"5503", "parentId":"505", "name":"左表右图","url":"/quickui/sample/graphical/grid-chart3.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"5504", "parentId":"505", "name":"表格与图表切换","url":"/quickui/sample/graphical/grid-chart4.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"5505",  "parentId":"505", "name":"表单与图表","url":"/quickui/sample/fullform/form-chart.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},

	{ id:602, parentId:6, name:"highchart：基本类型","icon": "./skin/index_icon.png","backgroundPosition":"-131px -112px"},
	{ id:6201, parentId:602, name:"折线图",url:"/quickui/sample/thirdparty/highcharts/sample6_1_1.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6202, parentId:602, name:"折线图(直接显示值)",url:"/quickui/sample/thirdparty/highcharts/sample6_1_2.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6203, parentId:602, name:"曲线图",url:"/quickui/sample/thirdparty/highcharts/sample6_1_3.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6204, parentId:602, name:"面积图",url:"/quickui/sample/thirdparty/highcharts/sample6_1_4.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6205, parentId:602, name:"面积图(密集)",url:"/quickui/sample/thirdparty/highcharts/sample6_1_7.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6206, parentId:602, name:"面积图(曲面)",url:"/quickui/sample/thirdparty/highcharts/sample6_1_8.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6207, parentId:602, name:"叠加面积图",url:"/quickui/sample/thirdparty/highcharts/sample6_1_5.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6208, parentId:602, name:"叠加面积图(百分比)",url:"/quickui/sample/thirdparty/highcharts/sample6_1_6.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6209, parentId:602, name:"条形图",url:"/quickui/sample/thirdparty/highcharts/sample6_1_10.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6210, parentId:602, name:"叠加条形图",url:"/quickui/sample/thirdparty/highcharts/sample6_1_11.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6211, parentId:602, name:"叠加条形图(反向)",url:"/quickui/sample/thirdparty/highcharts/sample6_1_12.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6212, parentId:602, name:"柱状图",url:"/quickui/sample/thirdparty/highcharts/sample6_1_13.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6213, parentId:602, name:"叠加柱状图",url:"/quickui/sample/thirdparty/highcharts/sample6_1_14.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6214, parentId:602, name:"叠加柱状图(多列)",url:"/quickui/sample/thirdparty/highcharts/sample6_1_15.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6215, parentId:602, name:"叠加柱状图(百分比)",url:"/quickui/sample/thirdparty/highcharts/sample6_1_16.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6216, parentId:602, name:"柱状图(文字倾斜)",url:"/quickui/sample/thirdparty/highcharts/sample6_1_17.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6217, parentId:602, name:"饼图",url:"/quickui/sample/thirdparty/highcharts/sample6_1_18.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6218, parentId:602, name:"散点图",url:"/quickui/sample/thirdparty/highcharts/sample6_1_19.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6219, parentId:602, name:"气泡图",url:"/quickui/sample/thirdparty/highcharts/sample6_1_20.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},

	{ id:603, parentId:6, name:"highchart：组合使用","icon": "./skin/index_icon.png","backgroundPosition":"-151px -112px"},
	{ id:6302, parentId:603, name:"面积区间+线",url:"/quickui/sample/thirdparty/highcharts/sample6_2_2.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6303, parentId:603, name:"柱+线+饼",url:"/quickui/sample/thirdparty/highcharts/sample6_2_3.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6304, parentId:603, name:"柱+线+双坐标",url:"/quickui/sample/thirdparty/highcharts/sample6_2_4.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6305, parentId:603, name:"柱+线+多坐标",url:"/quickui/sample/thirdparty/highcharts/sample6_2_5.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6306, parentId:603, name:"散点+线",url:"/quickui/sample/thirdparty/highcharts/sample6_2_6.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},

	
	{ id:604, parentId:6, name:"highchart：动态效果","icon": "./skin/index_icon.png","backgroundPosition":"-168px -112px"},
	{ id:6401, parentId:604, name:"点击切换子图表",url:"/quickui/sample/thirdparty/highcharts/sample6_3_1.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6402, parentId:604, name:"图表动态变化",url:"/quickui/sample/thirdparty/highcharts/sample6_3_2.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6403, parentId:604, name:"点击添加节点",url:"/quickui/sample/thirdparty/highcharts/sample6_3_3.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6404, parentId:604, name:"框选查看详情",url:"/quickui/sample/thirdparty/highcharts/sample6_3_4.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},

	
	
	{ id:605, parentId:6, name:"highchart：其他类型","icon": "./skin/index_icon.png","backgroundPosition":"-188px -112px"},
	{ id:6501, parentId:605, name:"纵向曲线图",url:"/quickui/sample/thirdparty/highcharts/sample6_4_1.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6502, parentId:605, name:"纵向面积图",url:"/quickui/sample/thirdparty/highcharts/sample6_4_2.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6503, parentId:605, name:"区间面积图",url:"/quickui/sample/thirdparty/highcharts/sample6_4_3.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6504, parentId:605, name:"区间条形图",url:"/quickui/sample/thirdparty/highcharts/sample6_4_4.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6505, parentId:605, name:"区间柱状图",url:"/quickui/sample/thirdparty/highcharts/sample6_4_5.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6506, parentId:605, name:"双层饼图",url:"/quickui/sample/thirdparty/highcharts/sample6_4_6.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6507, parentId:605, name:"半环形图",url:"/quickui/sample/thirdparty/highcharts/sample6_4_7.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6508, parentId:605, name:"模拟时钟",url:"/quickui/sample/thirdparty/highcharts/sample6_4_8.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6509, parentId:605, name:"仪表盘1",url:"/quickui/sample/thirdparty/highcharts/sample6_4_9.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6510, parentId:605, name:"仪表盘2",url:"/quickui/sample/thirdparty/highcharts/sample6_4_10.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6511, parentId:605, name:"仪表盘3",url:"/quickui/sample/thirdparty/highcharts/sample6_4_11.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6512, parentId:605, name:"雷达图",url:"/quickui/sample/thirdparty/highcharts/sample6_4_12.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6513, parentId:605, name:"蛛网图",url:"/quickui/sample/thirdparty/highcharts/sample6_4_13.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6514, parentId:605, name:"玫瑰图",url:"/quickui/sample/thirdparty/highcharts/sample6_4_14.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6515, parentId:605, name:"漏斗图",url:"/quickui/sample/thirdparty/highcharts/sample6_4_15.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6516, parentId:605, name:"蜡烛图",url:"/quickui/sample/thirdparty/highcharts/sample6_4_16.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ id:6517, parentId:605, name:"流程图",url:"/quickui/sample/thirdparty/highcharts/sample6_4_17.jsp", target:"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	
	
	
	{ "id":"607", "parentId":"6", "name":"其他", "isParent": "true","icon": "./skin/index_icon.png","backgroundPosition":"-112px -32px"},
	{ "id":"6701", "parentId":"607", "name":"UEditor编辑器","url":"/quickui/sample/thirdparty/ueditor.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"},
	{ "id":"6702", "parentId":"607", "name":"甘特图","url":"/quickui/sample/thirdparty/gantt.jsp", "target":"frmright","icon": "./skin/index_icon.png","backgroundPosition":"-191px -32px"}

];
			var data = [
				{ "news": "请假正在审批中" },
				{ "news": "请各位周五前提交周报" },
				{ "news": "QUICKUI新版本上线啦" }
			];
			tip_construction(data, ".right_tip", 10000); //数据，div的class或者id，定时器时间
			function exitHandler(){

				top.Dialog.confirm("确定要退出系统吗",function(){
					window.location="/quickui/system/login/login.jsp"
				});
			}
			
			function lockScreen(){
	top.Dialog.confirm("确定要锁屏吗，锁屏后只有重新输入密码才能解除。",function(){
		openLockWindow();
	});
}
function openLockWindow(){
	var diag = new top.Dialog();
		diag.Title = "系统锁屏";
		diag.ID = "a1";
		diag.URL="/quickui/sample/templete/lock.jsp";
		diag.Width=350;
		diag.Height=150;
		diag.ShowCloseButton=false;
		diag.ShowCancelButton=false;
		diag.ShowOkButton=false;
		diag.ButtonAlign="center";
		diag.AllowChangeIndex=false;
		diag.show();
		diag.addButton("btn1","注销系统",function(){
			top.Dialog.confirm("确定要退出系统吗",function(){window.location="/quickui/userAction.do?method=logout"});
		})
		diag.addButton("btn2","确定解锁",function(){
			top.document.getElementById("_DialogFrame_a1").contentWindow.validateForm();
		})
		$.post("/quickui/userAction.do?method=lockScreen");
}

function validate(){
	
}
function changeSkin(themeColor,skinName){
	top.Dialog.confirm("确定应用该皮肤吗？",function(){
		document.getElementById("sessionSkin").src="/quickui/system/main/skin.jsp?skinName="+skinName+"&themeColor="+themeColor;
	})
}
		</script>
	</body>
<!--窗口任务栏区域start-->
<div id="dialogTask" class="dialogTaskBg" style="display:none;"></div>
<!--窗口任务栏区域end-->
<iframe id="sessionSkin" src="" width="0" height="0" style="display:none;"></iframe>
</html>
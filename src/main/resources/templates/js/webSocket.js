/**
 *
 */

var ws = null;	//websocket对象
var urlHost = "172.30.34.108:8080";
// var urlHost = "localhost:8080";

//设置需要显示的列
var columns = [
    {
        field: 'scAddtime',
        title: '记录时间',
        formatter: function(value,row,index){
            return parseTime(value);
        }
    }, {
        field: 'scDeviceAreaName',
        title: '闸机位置'
    }, {
        field: 'scDoorno',
        title: '闸机编号'
    }, {
        field: 'scName',
        title: '姓名'
    }, {
        field: 'scInoutstatus',
        title: '进出'
    }, {
        field: 'scWorkerno',
        title: '工号'
    }, {
        field: 'scDepartmentname',
        title: '部门'
    }, {
        field: 'scMobileno',
        title: '卡号'
    }, {
        field: 'scCheckResultName',
        title: '事件类型'
    }/*, {
        field: 'scEventtypeid',
        visible: false,
        title: '事件类型'
    }, {
        field: 'scRecordtime',
        visible: false,
        title: '时间'
    }, {
        field: 'scCardguidno',
        visible: false,
        title: '卡号'
        // .align: 'center'
    }, {
        field: 'scDepartmentid',
        visible: false,
        title: '部门号'
    }, {
        field: 'scIdtypeid',
        visible: false,
        title: 'scIdtypeid'
    }, {
        field: 'scSerierno',
        visible: false,
        title: 'ID'
    }*/];

var columns2 = [
    {
        field: 'scDepartmentname',
        title: '部门'
    }, {
        field: 'scDepartmentid',
        visible: false,
        title: '部门号'
    }, {
        field: 'scMobileno',
        title: '卡号'
    }, {
        field: 'scWorkerno',
        title: '工号'
    }, {
        field: 'scName',
        title: '姓名'
    }, {
        field: 'zuizaojinru',
        title: '最早进入'
    }, {
        field: 'zuihouchuqu',
        title: '最后出去'
    }, {
        field: 'times',
        title: '进入次数'
    }, {
        field: 'innerTime',
        title: '室内时长',
        formatter: function(value,row,index){
            return "<a href='javascript:;' onclick='inoutdetail(\""
                +row.zuizaojinru+"\",\""
                +row.zuihouchuqu+"\",\""
                +row.scDepartmentname+"\",\""
                +row.scDepartmentid+"\",\""
                +row.scMobileno+"\",\""
                +row.scWorkerno+"\",\""
                +row.scName+"\")'>"+value+"</a>";
            }
    }];

//初始化表格数据
var data = [];

var data2 = [{
    scAddtime:"dddddd",
    scDepartmentname:"dddddd",
    scDeviceAreaName:"dddddd",
    scDoorno:"dddddd",
    scCheckResultName:"dddddd",
    scInoutstatus:"dddddd",
    scMobileno:"dddddd",
    scName:"dddddd",
    scWorkerno:"dddddd"
}];


//初始化，建立websocket连接
function startWebSocket() {
    initTable();
    var usercookie = getCookie("username");
    //alert("usercookie为"+usercookie);
    if (null != usercookie && "\"\"" != usercookie) {
        if ('WebSocket' in window) {
            try {
                ws = new WebSocket("ws://" + urlHost + "/menjin_at/webSocket");
            } catch (e) {
                $("#tipsContent").text("建立连接失败");
                $("#tips").modal('show');
            }
        } else if ('MozWebSocket' in window) {
            ws = new MozWebSocket("ws://" + urlHost + "/menjin_at/webSocket");
        } else {
            $("#tipsContent").text("抱歉，您的浏览器不支持WebSocket");
            $("#tips").modal('show');
        }
        ws.onmessage = function (evt) {
            if ("1" == evt.data) {//被顶掉了
                logout("othersLogin");
            } else {
                //alert(evt.data);
                // console.log("后台传来的消息：" + evt.data);
                // console.log("尝试取消息类型：" + JSON.parse(evt.data).messageType);
                var jsonVar = JSON.parse(evt.data);
                var mType = jsonVar.messageType;
                var tData = jsonVar.t;
                if(2==mType){
                    //向表格1中追加实时内容
                    //alert("向表格1中加数据");
                    appendTable(tData,"#tab1");
                }else if(3==mType){
                    //alert("向表格2中加数据");
                    appendTable(tData,"#tab2");
                }else if(4==mType){
                    for (var i = 0; i < tData.length; i++) {
                        $("#departmentX").append("<option value='"+tData[i].scDepartmentid+"'>"+tData[i].scDepartmentname+"</option>");
                        $("#div3_departmentX").append("<option value='"+tData[i].scDepartmentid+"'>"+tData[i].scDepartmentname+"</option>");
                    }
                }else if(5==mType){
                    appendTable(tData,"#tab2");
                }

            }
        };
        ws.onclose = function (evt) {
            //alert("服务器已断开!");
            window.location.href = "/menjin_at/login.html";
        };
        ws.onopen = function (evt) {
            onOpen(evt);
            loadDepartment();//是否需要放到首页加载时
        };
    }
    else {
        logout("logout");
    }
}

/*
时间戳 转 时间
 */
function parseTime(shijianchuo)
{
//shijianchuo是整数，否则要parseInt转换
    var time = new Date(shijianchuo);
    var y = time.getFullYear();
    var m = time.getMonth()+1;
    var d = time.getDate();
    var h = time.getHours();
    var mm = time.getMinutes();
    var s = time.getSeconds();
    return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
}

/**
 * 如果月份/日期是5 换成05
 * @param m
 * @returns {string}
 */
function add0(m){return m<10?'0'+m:m }

/**
 * 导航切换
 * @param parm
 */
function loadK(parm) {
    //alert("切换到页面:"+parm);
    for (var i = 1; i <= 3; i++) {
        var p1 = '#div' + i;
        var p2 = '#li0' + i;
        if(2==i){
            initTable3();
        }
        if(3==i){
            initTable4();
        }
        if (parm == i) {
            $(p1).show();
            $(p2).addClass('active');
        } else {
            $(p1).hide();
            $(p2).removeClass('active');
        }
    }
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    ws.close();
};

/**
 * 初始化第一个页面的表格
 */
function initTable() {
    $('#tab1').bootstrapTable({
        //toolbar:"#toolbar1",
        //showLoading: false,
        data: data,
        columns: columns,
        pagination: false
    });
    $('#tab1').bootstrapTable('hideLoading');
    $('#tab2').bootstrapTable({
        //toolbar:"#toolbar2",
        //showLoading: false,
        data: data,
        columns: columns,
        pagination: false
    });
    $('#tab2').bootstrapTable('hideLoading');

    $("#begin_time").datetimepicker({
        minView: "month", //选择日期后，不会再跳转去选择时分秒
        format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
        language: 'zh-CN', //汉化
        weekStart: 1,
        todayBtn: true,
        todayHighlight: 1,
        autoclose:true //选择日期后自动关闭
    });
    $("#begin_time").datetimepicker("setDate", new Date());
    $("#end_time").datetimepicker({
        minView: "month", //选择日期后，不会再跳转去选择时分秒
        format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
        language: 'zh-CN', //汉化
        weekStart: 1,
        todayBtn: true,
        todayHighlight: 1,
        autoclose:true //选择日期后自动关闭
    });
    $("#end_time").datetimepicker("setDate", new Date());

    $("#div3_begin_time").datetimepicker({
        minView: "month", //选择日期后，不会再跳转去选择时分秒
        format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
        language: 'zh-CN', //汉化
        weekStart: 1,
        todayBtn: true,
        todayHighlight: 1,
        autoclose:true //选择日期后自动关闭
    });
    $("#div3_begin_time").datetimepicker("setDate", new Date());
    $("#div3_end_time").datetimepicker({
        minView: "month", //选择日期后，不会再跳转去选择时分秒
        format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
        language: 'zh-CN', //汉化
        weekStart: 1,
        todayBtn: true,
        todayHighlight: 1,
        autoclose:true //选择日期后自动关闭
    });
    $("#div3_end_time").datetimepicker("setDate", new Date());
}

/**
 * 初始化第2个页面的表格 和 条件控件
 */
function initTable3() {

    /*$("#begin_time").datetimepicker({
        minView: "month", //选择日期后，不会再跳转去选择时分秒
        format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
        language: 'zh-CN', //汉化
        weekStart: 1,
        todayBtn: true,
        todayHighlight: 1,
        autoclose:true //选择日期后自动关闭
    });
    $("#begin_time").datetimepicker("setDate", new Date());
    $("#end_time").datetimepicker({
        minView: "month", //选择日期后，不会再跳转去选择时分秒
        format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
        language: 'zh-CN', //汉化
        weekStart: 1,
        todayBtn: true,
        todayHighlight: 1,
        autoclose:true //选择日期后自动关闭
    });
    $("#end_time").datetimepicker("setDate", new Date());*/


    var queryUrl = '/menjin_at/accessRecord/getTab3Record';
    $('#tab3').bootstrapTable({
        toolbar:"#div2_tab3_bar",
        showLoading: true,
        columns: columns,
        url: queryUrl,                      //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        contentType: 'application/x-www-form-urlencoded',
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        clickToSelect: true,                //是否启用点击选中行
        queryParamsType : "",
        queryParams : queryParams,
        onLoadSuccess: function () {
            console.log("onLoadSuccess！");
            //alert("onLoadSuccess！");
        },
        onLoadError: function () {
            console.log("数据加载失败！");
            //alert("数据加载失败！");
        },
        onDblClickRow: function (row, $element) {
            // var id = row.ID;
            // EditViewById(id, 'view');
        }
    });
}

/**
 * 向后台websocket请求加载部门控件
 */
function loadDepartment(){
    // departmentX
    var jsonMsg = {
        "targetType":"initDepartment",
        "targetId":"1"
    };
    ws.send(JSON.stringify(jsonMsg));
}

/**
 * 刷新室内人员
 */
function shuaxin(){
    var jsonMsg = {
        "targetType":"shuaxin",
        "targetId":"2"
    };
    ws.send(JSON.stringify(jsonMsg));
}

function shuaxin0(){
    var jsonMsg = {
        "targetType":"shuaxin0",
        "targetId":"3"
    };
    ws.send(JSON.stringify(jsonMsg));
}

/**
 * 查询进出记录
 */
function queryTab3(){
    var time1 = $("#begin_time").val();
    var time2 = $("#end_time").val();

    var day1 = new Date(time1);
    var day2 = new Date(time2);
    var time_diff =day2.getTime() - day1.getTime(); //时间差的毫秒数
    var days = Math.floor(time_diff / (24 * 3600 * 1000));
    if(days>31){
        alert("查询时间跨度请勿超过一个月！");
        return;
    }
    $('#tab3').bootstrapTable('refreshOptions',{pageNumber:1,pageSize:10});

}

function exportExcelTab3(){
    var time1 = $("#begin_time").val();
    var time2 = $("#end_time").val();
    var options=$("#floorX");
    var floorx = options.val();
    var options2=$("#departmentX");
    var departmentx = options2.val();
    var nameX = $("#nameX").val().trim();
    var jobX = $("#jobX").val().trim();
    time1 = time1+" 00:00:00";
    var now = new Date();
    var today = now.getFullYear()+"-"+add0(now.getMonth()+1)+"-"+add0(now.getDate());
    if(time2==today){
        var t = now.getTime() - 300000;//数据库时间慢5分钟，那么零点5分以内加载会有问题
        var d = new Date(t);
        var str = add0(d.getHours())+":"+add0(d.getMinutes())+":"+add0(d.getSeconds());
        time2 = time2+" "+str;
    }else{
        time2 += " 23:59:59";
    }

    var url3 = "/menjin_at/out/excel";
    var url2 = "http://"+urlHost+url3;
    var export_path = url2 + "?time1="+time1+"&time2=" + time2 + "&floorx=" + floorx + "&departmentx=" + departmentx + "&nameX=" + nameX + "&jobX=" + jobX;
    window.open(export_path);
}

function exportExcelTab2(){

    var url3 = "/menjin_at/out/excelPeople";
    var url2 = "http://"+urlHost+url3;
    window.open(url2);

}

//获取参数方法
function queryParams(params) {
    var time1 = $("#begin_time").val();
    var time2 = $("#end_time").val();
    var options=$("#floorX");
    var floorx = options.val();
    var options2=$("#departmentX");
    var departmentx = options2.val();
    var nameX = $("#nameX").val().trim();
    var jobX = $("#jobX").val().trim();

    //alert(time1.length);

    if(time1.length>10){

    }else{
        time1 = time1+" 00:00:00";
        var now = new Date();
        var today = now.getFullYear()+"-"+add0(now.getMonth()+1)+"-"+add0(now.getDate());
        if(time2==today){
            var t = now.getTime() - 300000;//数据库时间慢5分钟，那么零点5分以内加载会有问题
            var d = new Date(t);
            var str = add0(d.getHours())+":"+add0(d.getMinutes())+":"+add0(d.getSeconds());
            time2 = time2+" "+str;
        }else{
            time2 += " 23:59:59";
        }
    }

    //alert("time1:"+time1+",time2:"+time2+",floorx:"+floorx+",departmentx:"+departmentx+",nameX:"+nameX+",jobX:"+jobX);
    var temp = {
        offset: params.offset,  //页码
        //pageSize : params.limit,
        pageSize:params.pageSize,
        pageNumber:params.pageNumber,//this  params
        time1: time1,
        time2: time2,
        floorx: floorx,
        departmentx: departmentx,
        nameX: nameX,
        jobX: jobX,
        length: 6
    };
    return temp;
}

/**
 * 更新表格1,2数据
 * @param dataSS
 */
function appendTable(doorIdArr,tableId) {
    // alert("准备追加到表格："+dataSS);
    var zTreeDoorData = [];
    //var doorIdArr = JSON.parse(dataSS);
    $(tableId).bootstrapTable('removeAll');

    for(var i=0;i<doorIdArr.length;i++){
        var newData = [{
            scAddtime:doorIdArr[i].scAddtime,
            // scCardguidno:doorIdArr[i].scCardguidno,
            // scDepartmentid:doorIdArr[i].scDepartmentid,
            scDepartmentname:doorIdArr[i].scDepartmentname,
            scDeviceAreaName:doorIdArr[i].scDeviceAreaName,
            scDoorno:doorIdArr[i].scDoorno,
            //scEventtypeid:doorIdArr[i].scEventtypeid,
            scCheckResultName:doorIdArr[i].scCheckResultName,
            // scIdtypeid:doorIdArr[i].scIdtypeid,
            scInoutstatus:doorIdArr[i].scInoutstatus,
            scMobileno:doorIdArr[i].scMobileno,
            scName:doorIdArr[i].scName,
            // scRecordtime:doorIdArr[i].scRecordtime,
            // scSerierno:doorIdArr[i].scSerierno,
            scWorkerno:doorIdArr[i].scWorkerno
        }];

        $(tableId).bootstrapTable('append', newData);
    }
}

//建立webSocket连接时的方法
function onOpen(evt) {
    /*document.getElementById("chatBox").onkeydown = function(event) {
        if (13 == event.keyCode) {
            sendMsg();
        };
    };*/
}

//获取到cookie
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else return null;
}

//监听键盘Ctry+Enter发送消息
function keySend(event) {
    if (event.ctrlKey && event.keyCode == 13 && "A" == state) {
        sendMessage();
    }
}

//退出登录
function logout(reason) {
    url = "logout?reason=" + reason;
    window.location.href = url;
}

function exportExcelTab4(){
    var time1 = $("#div3_begin_time").val();
    var time2 = $("#div3_end_time").val();
    /*var options=$("#div3_floorX");
    var floorx = options.val();*/
    var options2=$("#div3_departmentX");
    var departmentx = options2.val();
    var nameX = $("#div3_nameX").val().trim();
    var jobX = $("#div3_jobX").val().trim();
    time1 = time1+" 00:00:00";
    var now = new Date();
    var today = now.getFullYear()+"-"+add0(now.getMonth()+1)+"-"+add0(now.getDate());
    if(time2==today){
        var t = now.getTime() - 300000;//数据库时间慢5分钟，那么零点5分以内加载会有问题
        var d = new Date(t);
        var str = add0(d.getHours())+":"+add0(d.getMinutes())+":"+add0(d.getSeconds());
        time2 = time2+" "+str;
    }else{
        time2 += " 23:59:59";
    }

    // var data3 = '{"time1":"'+time1+'","time2":"'+time2+'","floorx":"'+floorx+'","departmentx":"'+departmentx+'","nameX":"'+nameX+'","jobX":"'+jobX+'"}';
    // var data4 = JSON.parse(data3);

    var url3 = "/menjin_at/out/excel2";
    var url2 = "http://"+urlHost+url3;
    var export_path = url2 + "?time1="+time1+"&time2=" + time2 + "&departmentx=" + departmentx + "&nameX=" + nameX + "&jobX=" + jobX;
    window.open(export_path);
}

function queryTab4(){
    /*$("#div3_tab3query").click(function () {
        $(this).button('loading');//禁用按钮并显示提交中
        //$(this).button('reset');//重置按钮
    });*/

    var time1 = $("#div3_begin_time").val();
    var time2 = $("#div3_end_time").val();

    var day1 = new Date(time1);
    var day2 = new Date(time2);
    var time_diff =day2.getTime() - day1.getTime(); //时间差的毫秒数
    var days = Math.floor(time_diff / (24 * 3600 * 1000));
    if(days>31){
        alert("查询时间跨度请勿超过一个月！");
        return;
    }
    $("#tab4").bootstrapTable('removeAll');
    $('#tab4').bootstrapTable('refreshOptions',{pageNumber:1,pageSize:10});

}

function initTable4() {
    // $("#div3_begin_time").datetimepicker({
    //     minView: "month", //选择日期后，不会再跳转去选择时分秒
    //     format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
    //     language: 'zh-CN', //汉化
    //     weekStart: 1,
    //     todayBtn: true,
    //     todayHighlight: 1,
    //     autoclose:true //选择日期后自动关闭
    // });
    // $("#div3_begin_time").datetimepicker("setDate", new Date());
    // $("#div3_end_time").datetimepicker({
    //     minView: "month", //选择日期后，不会再跳转去选择时分秒
    //     format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
    //     language: 'zh-CN', //汉化
    //     weekStart: 1,
    //     todayBtn: true,
    //     todayHighlight: 1,
    //     autoclose:true //选择日期后自动关闭
    // });
    // $("#div3_end_time").datetimepicker("setDate", new Date());

    var queryUrl = '/menjin_at/accessRecord/getTab4Record';
    $('#tab4').bootstrapTable({
        toolbar:"#div3_tab4_bar",
        showLoading: true,
        columns: columns2,
        url: queryUrl,                      //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        contentType: 'application/x-www-form-urlencoded',
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        clickToSelect: true,                //是否启用点击选中行
        queryParamsType : "",
        queryParams : queryParams2,
        onLoadSuccess: function () {
            console.log("onLoadSuccess！");
            //alert("onLoadSuccess！");
        },
        onLoadError: function () {
            console.log("数据加载失败！");
            //alert("数据加载失败！");
        },
        onDblClickRow: function (row, $element) {
            // var id = row.ID;
            // EditViewById(id, 'view');
        }
    });
}

function queryParams2(params) {
    var time1 = $("#div3_begin_time").val();
    var time2 = $("#div3_end_time").val();
    /*var options=$("#div3_floorX");
    var floorx = options.val();*/
    var options2=$("#div3_departmentX");
    var departmentx = options2.val();
    var nameX = $("#div3_nameX").val().trim();
    var jobX = $("#div3_jobX").val().trim();

    time1 = time1+" 00:00:00";
    var now = new Date();
    var today = now.getFullYear()+"-"+add0(now.getMonth()+1)+"-"+add0(now.getDate());
    if(time2==today){
        var t = now.getTime() - 300000;//数据库时间慢5分钟，那么零点5分以内加载会有问题
        var d = new Date(t);
        var str = add0(d.getHours())+":"+add0(d.getMinutes())+":"+add0(d.getSeconds());
        time2 = time2+" "+str;
    }else{
        time2 += " 23:59:59";
    }

    //alert("time1:"+time1+",time2:"+time2+",floorx:"+floorx+",departmentx:"+departmentx+",nameX:"+nameX+",jobX:"+jobX);
    var temp = {
        offset: params.offset,  //页码
        //pageSize : params.limit,
        pageSize:params.pageSize,
        pageNumber:params.pageNumber,//this  params
        time1: time1,
        time2: time2,
        //floorx: floorx,
        departmentx: departmentx,
        nameX: nameX,
        jobX: jobX,
        length: 6
    };
    return temp;
}

function inoutdetail(zuizaojinru,zuihouchuqu,scDepartmentname,scDepartmentid,scMobileno,scWorkerno,scName){
    /*alert("部门："+scDepartmentname
        +"，卡号："+scMobileno
        +"，工号："+scWorkerno
        +"，姓名："+scName
        +"，最早进入："+zuizaojinru
        +"，最后出去："+zuihouchuqu
    );*/

    $('#div3').hide();
    $('#li03').removeClass('active');
    $('#div2').show();
    $('#li02').addClass('active');

    var sdtime3 = new Date(new Date(zuizaojinru).getTime()-1000*60*60*4);
    var sdtime4 = new Date(new Date(zuihouchuqu).getTime()+1000*60*60*4);//前后推4个小时

    $("#begin_time").val(parseTime(sdtime3));
    $("#end_time").val(parseTime(sdtime4));
    $("#departmentX").val(scDepartmentid);
    $("#nameX").val(scName);
    $("#jobX").val(scWorkerno);

    queryTab3();
}

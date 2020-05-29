/**
 *
 */

var ws = null;	//websocket对象
var targetId = 0;	//聊天对象ID
var staffId = 0;	//当前用户ID
var staffName = "";	//当前用户姓名
var power = "N";	//当前用户权限
var userCount = 0;	//当前在线用户数
var headUrl = "";	//当前用户头像
var count = 0;		//消息板消息数量
var cc = new Array(0, 0);	//私聊未查看的消息数目
var recentMsg = new Array("", "");	//最近微信消息
var state = "A";	//用户状态
var flag = 0;		//检查微信面板开闭状态
var urlHost = "http://172.30.34.96:8080";

// var queryUrl = '/accessRecord/getTab3Record';

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
        title: '进出',
        formatter: function(value,row,index){
            if(value=="1"){
                return "进";
            }else if(value=="201"){
                return "出";
            }else{
                return value;
            }
        }
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
        var localhost = "localhost:8080";
        // var localhost = "106.75.32.166:8080";
//         var localhost = "possible2dream.cn";//nginx
        if ('WebSocket' in window) {
            try {
                ws = new WebSocket("ws://" + localhost + "/menjin_at/webSocket");
            } catch (e) {
                $("#tipsContent").text("建立连接失败");
                $("#tips").modal('show');
            }
        } else if ('MozWebSocket' in window) {
            ws = new MozWebSocket("ws://" + localhost + "/menjin_at/webSocket");
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
    for (var i = 1; i <= 6; i++) {
        var p1 = '#div' + i;
        var p2 = '#li0' + i;
        if(2==i){
            initTable3();
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
}

/**
 * 初始化第2个页面的表格 和 条件控件
 */
function initTable3() {
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
        alert("查询时间跨度请勿超过一个月！")
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

    // var data3 = '{"time1":"'+time1+'","time2":"'+time2+'","floorx":"'+floorx+'","departmentx":"'+departmentx+'","nameX":"'+nameX+'","jobX":"'+jobX+'"}';
    // var data4 = JSON.parse(data3);

    var url3 = "/menjin_at/out/excel";
    var url2 = urlHost+url3;
    /*var params2 = {
        time1: time1,
        time2: time2,
        floorx: floorx,
        departmentx: departmentx,
        nameX: nameX,
        jobX: jobX
    };
    $.ajax({
        type:'POST',
        url: url2,
        data: params2,
        beforeSend: function(request) {
            //request.setRequestHeader("Authorization", "token信息，验证身份");
        },
        success: function(redata) {

            // 创建a标签，设置属性，并触发点击下载
            // var $a = $("<a>");
            // $a.attr("href", redata.data.file);
            // $a.attr("download", redata.data.filename);
            // $("body").append($a);
            // $a[0].click();
            // $a.remove();
        },
        error: function () {
            alert("错误");
        }
    });*/
    var export_path = url2 + "?time1="+time1+"&time2=" + time2 + "&floorx=" + floorx + "&departmentx=" + departmentx + "&nameX=" + nameX + "&jobX=" + jobX;
    window.open(export_path);
}

function exportExcelTab2(){

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


//转换时间格式
/**
 * 对Date的扩展，将 Date 转化为指定格式的String
 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
 * eg:
 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04
 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04
 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04
 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
 */
Date.prototype.pattern = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    var week = {
        "0": "日",
        "1": "一",
        "2": "二",
        "3": "三",
        "4": "四",
        "5": "五",
        "6": "六"
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    if (/(E+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "星期" : "") : "") + week[this.getDay() + ""]);
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};

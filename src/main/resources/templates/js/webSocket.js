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

/*
* {
"scAddtime":1590040984477,
"scCardguidno":1411969022,
"scDepartmentid":39,
"scDepartmentname":"封装生产部",
"scDoorno":10,
"scEventtypeid":0,
"scIdtypeid":1,
"scInoutstatus":-55,
"scMobileno":"1411969022",
"scName":"任显浩",
"scRecordtime":1590040982000,
"scSerierno":2976369,
"scWorkerno":"CQ04661"
}
* */
//设置需要显示的列

var columns = [
    {
        field: 'scAddtime',
        title: '记录时间',
        formatter: function(value,row,index){
            return parseTime(value);
        }
    }, {
        field: 'scDoorno',
        title: '门号'
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
    }, {
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
    }];
//初始化表格数据
var data = [
    /*{
        scAddtime:'1590040984477',
        scCardguidno:'1411969022',
        scDepartmentid:'39',
        scDepartmentname:'封装生产部',
        scDoorno:'10',
        scEventtypeid:'0',
        scIdtypeid:'1',
        scInoutstatus:'-55',
        scMobileno:'1411969022',
        scName:'任显浩',
        scRecordtime:'1590040982000',
        scSerierno:'2976369',
        scWorkerno:'CQ04661'
    }*/
];


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
                //console.log("后台传来的消息：" + evt.data);
                //向表格中追加实时内容
                appendTable(evt.data);
            }
        };
        ws.onclose = function (evt) {
            //alert("服务器已断开!");
            window.location.href = "/menjin_at/login.html";
        };
        ws.onopen = function (evt) {
            onOpen(evt);
        };
    }
    else {
        logout("logout");
    }
}

/**
 * 时间戳 转 时间
 * @param timestamp
 * @returns {string}
 */
/*function parseTime(timestamp) {
    var datetime = new Date();
    datetime.setTime(timestamp);
    var year = datetime.getFullYear();
    var month = datetime.getMonth()+1;
    var date = datetime.getDate();
    var hour = datetime.getHours();
    var minute = datetime.getMinutes();
    var second = datetime.getSeconds();
    // var msecond = datetime.getMilliseconds();//+"  "+msecond
    return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
}*/


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
 * 更新表格数据
 * @param dataSS
 */
function appendTable(dataSS) {
    // alert("准备追加到表格："+dataSS);
    var zTreeDoorData = [];
    var doorIdArr = JSON.parse(dataSS);
    $('#tab1').bootstrapTable('removeAll');
    // zTreeDoorData.splice(0,zTreeDoorData.length);

    for(var i=0;i<doorIdArr.length;i++){
        var newData = [{
            scAddtime:doorIdArr[i].scAddtime,
            scCardguidno:doorIdArr[i].scCardguidno,
            scDepartmentid:doorIdArr[i].scDepartmentid,
            scDepartmentname:doorIdArr[i].scDepartmentname,
            scDoorno:doorIdArr[i].scDoorno,
            scEventtypeid:doorIdArr[i].scEventtypeid,
            scCheckResultName:doorIdArr[i].scCheckResultName,
            scIdtypeid:doorIdArr[i].scIdtypeid,
            scInoutstatus:doorIdArr[i].scInoutstatus,
            scMobileno:doorIdArr[i].scMobileno,
            scName:doorIdArr[i].scName,
            scRecordtime:doorIdArr[i].scRecordtime,
            scSerierno:doorIdArr[i].scSerierno,
            scWorkerno:doorIdArr[i].scWorkerno
        }];

        $('#tab1').bootstrapTable('append', newData);
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

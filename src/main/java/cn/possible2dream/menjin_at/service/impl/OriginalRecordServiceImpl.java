package cn.possible2dream.menjin_at.service.impl;

import cn.possible2dream.menjin_at.entity.*;
import cn.possible2dream.menjin_at.mapper.AccessRecordMapper;
import cn.possible2dream.menjin_at.mapper.DepartmentMapper;
import cn.possible2dream.menjin_at.service.OriginalRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("originalRecordService")
public class OriginalRecordServiceImpl implements OriginalRecordService {

    @Resource
    private AccessRecordMapper accessRecordMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public Long selectMaxScSerierno() {
        return accessRecordMapper.selectMaxScSerierno();
    }

    @Override
    public AccessRecord getAccessRecordByScSerierno(Long scSerierno) {
        return accessRecordMapper.selectByPrimaryKey(scSerierno);
    }

    @Override
    public List<OriginalRecord> getOriginalRecordListByMaxId(Long scSerierno) {
        return accessRecordMapper.selectAllByMaxId(scSerierno);
    }

    @Override
    public List<OriginalRecord> getTop25() {
        return accessRecordMapper.selectTop25();
    }

    @Override
    public List<OriginalRecord> getMaxAddTime8h() {
        List<OriginalRecord> list = accessRecordMapper.selectRealTimeInner1();//可以试试 selectRealTimeInner2
        for(int i=list.size()-1;i>=0;i--){
            if(1!=list.get(i).getScInoutstatus()){
                list.remove(i);
            }
        }
        return list;
    }

    @Override
    public TableSplitResult<List<OriginalRecord>> getInOutRecordByConditions(Conditions conditions) {

        Integer total = 0;
        total = accessRecordMapper.selectGetInOutRecordByConditionsTotal(conditions);
        conditions.setTotal(total);
        int page = conditions.getPageNumber();
        if(page==1){
            conditions.setMinRow(1);
            conditions.setMaxRow(conditions.getPageSize());
        }else{
            conditions.setMinRow((conditions.getPageNumber()-1)*conditions.getPageSize()+1);
            conditions.setMaxRow(conditions.getPageNumber()*conditions.getPageSize());
        }
        List<OriginalRecord> list = new ArrayList<>();
        if(null!=total&&total!=0){
            list = accessRecordMapper.selectGetInOutRecordByConditions(conditions);
        }
        TableSplitResult<List<OriginalRecord>> fanhui = new TableSplitResult<List<OriginalRecord>>(page,total,list);

        return fanhui;
    }

    @Override
    public List<OriginalRecord> getInOutRecordByConditionsWithoutPages(Conditions conditions) {
        List<OriginalRecord> list = new ArrayList<>();
        list = accessRecordMapper.selectGetInOutRecordByConditionsConditionsWithoutPages(conditions);
        return list;
    }

    @Override
    public List<OriginalRecordInner> getInnerTimeByConditionsWithoutPages(Conditions conditions) {
        List<OriginalRecord> list = new ArrayList<>();
        List<OriginalRecordInner> listInner = new ArrayList<>();//返回前台
        list = accessRecordMapper.selectInnerTime(conditions);
        int size = list.size();
        if (0!=size) {
            long kahao = 0;//确定一个具体的人
            Date jiluTime = new Date();
            int qishi = 0;
            int jieshu = 0;
            for(int i=0;i<size;i++){
                OriginalRecord or = list.get(i);
                if(0==i){//每个阶段开始（continue之前），都需要做如下初始化
                    kahao = or.getScCardguidno();
                    jiluTime = or.getScAddtime();
                    qishi = i;
                    continue;
                }
                if(or.getScCardguidno()==kahao){
                    int hours = (int) ((or.getScAddtime().getTime() - jiluTime.getTime()) / (1000 * 60 * 60));
                    if(hours>10){//表示此人已是第二天上班了，对前一组数据进行阶段统计
                        jieshu = i-1;
                        //阶段统计 输入list qishi jieshu  返回List<OriginalRecordInner>
                        List<OriginalRecord> list2 = new ArrayList<>();//要处理的部分单独拿出来
                        for(int j=qishi;j<=jieshu;j++){
                            list2.add(list.get(j));
                        }
                        OriginalRecordInner ort = jieduantongji(list2,qishi,jieshu);
                        if(null!=ort){
                            listInner.add(ort);
                        }
                        jiluTime = or.getScAddtime();
                        qishi = i;
                        jieshu = 0;
                        continue;
                    }else{
                        if(i!=size-1){
                            jiluTime = or.getScAddtime();
                            continue;
                        }else{
                            jieshu = i-1;
                            List<OriginalRecord> list2 = new ArrayList<>();//要处理的部分单独拿出来
                            for(int j=qishi;j<=jieshu;j++){
                                list2.add(list.get(j));
                            }
                            OriginalRecordInner ort = jieduantongji(list2,qishi,jieshu);
                            if(null!=ort){
                                listInner.add(ort);
                            }
                            continue;
                        }
                    }
                }else{
//                    if(i!=size-1){
//                    }
                    jieshu = i-1;
                    List<OriginalRecord> list2 = new ArrayList<>();
                    for(int j=qishi;j<=jieshu;j++){
                        list2.add(list.get(j));
                    }
                    OriginalRecordInner ort = jieduantongji(list2,qishi,jieshu);
                    if(null!=ort){
                        listInner.add(ort);
                    }
                    jiluTime = or.getScAddtime();
                    kahao = or.getScCardguidno();
                    qishi = i;
                    jieshu = 0;
                    continue;
                }
            }
        }

        return listInner;
    }

    @Override
    public List<Department> getAllDepartment() {
        return departmentMapper.selectAll();
    }

    @Override
    public TableSplitResult<List<OriginalRecordInner>> getInnerTimeByConditions(Conditions conditions) {

        List<OriginalRecord> list = new ArrayList<>();
        List<OriginalRecordInner> listInner = new ArrayList<>();//返回前台
        list = accessRecordMapper.selectInnerTime(conditions);
        int size = list.size();
        if (0!=size) {
            long kahao = 0;//确定一个具体的人
            Date jiluTime = new Date();
            int qishi = 0;
            int jieshu = 0;
            for(int i=0;i<size;i++){
                OriginalRecord or = list.get(i);
                if(0==i){//每个阶段开始（continue之前），都需要做如下初始化
                    kahao = or.getScCardguidno();
                    jiluTime = or.getScAddtime();
                    qishi = i;
                    continue;
                }
                if(or.getScCardguidno()==kahao){
                    int hours = (int) ((or.getScAddtime().getTime() - jiluTime.getTime()) / (1000 * 60 * 60));
                    if(hours>10){//表示此人已是第二天上班了，对前一组数据进行阶段统计
                        jieshu = i-1;
                        //阶段统计 输入list qishi jieshu  返回List<OriginalRecordInner>
                        List<OriginalRecord> list2 = new ArrayList<>();//要处理的部分单独拿出来
                        for(int j=qishi;j<=jieshu;j++){
                            list2.add(list.get(j));
                        }
                        OriginalRecordInner ort = jieduantongji(list2,qishi,jieshu);
                        if(null!=ort){
                            listInner.add(ort);
                        }
                        jiluTime = or.getScAddtime();
                        qishi = i;
                        jieshu = 0;
                        continue;
                    }else{
                        if(i!=size-1){
                            jiluTime = or.getScAddtime();
                            continue;
                        }else{
                            jieshu = i-1;
                            List<OriginalRecord> list2 = new ArrayList<>();//要处理的部分单独拿出来
                            for(int j=qishi;j<=jieshu;j++){
                                list2.add(list.get(j));
                            }
                            OriginalRecordInner ort = jieduantongji(list2,qishi,jieshu);
                            if(null!=ort){
                                listInner.add(ort);
                            }
                            continue;
                        }
                    }
                }else{
//                    if(i!=size-1){
//                    }
                    jieshu = i-1;
                    List<OriginalRecord> list2 = new ArrayList<>();
                    for(int j=qishi;j<=jieshu;j++){
                        list2.add(list.get(j));
                    }
                    OriginalRecordInner ort = jieduantongji(list2,qishi,jieshu);
                    if(null!=ort){
                        listInner.add(ort);
                    }
                    jiluTime = or.getScAddtime();
                    kahao = or.getScCardguidno();
                    qishi = i;
                    jieshu = 0;
                    continue;
                }
            }
        }

        int page = conditions.getPageNumber();//第几页
        int pageSize = conditions.getPageSize();//每页条数
        List<OriginalRecordInner> listInner2 = new ArrayList<>();
        int yubeiListSize = listInner.size();

        for(int i=(page-1)*pageSize+1;i<=Math.min((page)*pageSize,yubeiListSize);i++){
            listInner2.add(listInner.get(i-1));
        }
        return new TableSplitResult(page, yubeiListSize, listInner2);//yubeiListSize 是 total
    }

    public static OriginalRecordInner jieduantongji( List<OriginalRecord> list,int qishi,int jieshu){
        OriginalRecordInner ort = new OriginalRecordInner();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(jieshu>qishi){
            int size = list.size();
            //判断是否常规的：
            boolean flag = true;//默认是异类
            if(flag){//异类（看了一下，基本上都只能按照异类来处理）
                Date date = new Date();
                int inout = -1;// 1 表示已经进去了 0表示出去了  -1表示还没开始处理
                int shijian = 0;//累计时长
                int cishu = 0;//进入次数
                //构造 OriginalRecordInner 需要的参数
                Date zuizaojinru = new Date();//最早进
                Date zuihouchuqu = new Date();//最晚出
                for(int i=0;i<size;i++){
                    OriginalRecord or = list.get(i);
                    if(-1==inout){//第一次进
                        if(or.getScEventtypeid()==0){//进成功
                            inout = 1;
                            date = or.getScAddtime();
                            zuizaojinru = date;
                            cishu++;
                        }
                    }else if(0==inout){//进
                        if(or.getScEventtypeid()==0&&((int)(or.getScAddtime().getTime()-date.getTime())/(1000))>20){//大于20秒 才做计算
                            inout = 1;
                            date = or.getScAddtime();
                            cishu++;
                        }
                    }else{//出
                        if(or.getScEventtypeid()==0&&((int)(or.getScAddtime().getTime()-date.getTime())/(1000))>20){//20秒内就认为它是出   不合适  当然也有可能它是真的出 拿错就错了
                            inout = 0;
                            zuihouchuqu = or.getScAddtime();
                            shijian += (int) (or.getScAddtime().getTime() - date.getTime());
                        }
                    }
                }

                int zongfen = shijian/(1000*60);
                int xiaoshi = zongfen/60;
                int fen = zongfen%60;
                String tishi = xiaoshi+"时"+fen+"分";
                if(1==inout){
                    //可能算错了 字符串提示用户
                    tishi += "(查验)";
                }

                ort.setTimes(cishu);
                ort.setInnerTime(tishi);
                ort.setZuizaojinru(formatter.format(zuizaojinru));
                ort.setZuihouchuqu(formatter.format(zuihouchuqu));
                ort.setScCardguidno(list.get(0).getScCardguidno());
                ort.setScWorkerno(list.get(0).getScWorkerno());
                ort.setScName(list.get(0).getScName());
                ort.setScMobileno(list.get(0).getScMobileno());
                ort.setScIdtypeid(list.get(0).getScIdtypeid());
                ort.setScDepartmentid(list.get(0).getScDepartmentid());
                ort.setScDepartmentname(list.get(0).getScDepartmentname());

            }else{//常规  //未完，已废弃
            }
            return ort;
        }
        return null;
    }


    /**
     * 关注 SC_EventTypeID=0 和 SC_InOutStatus  发现所有人的刷卡记录存在两种性质：正常通过的时候，进出信息以1 和 201 进行区分的，以及不进行区分的（）
     *
     * select a.SC_SerierNO,a.SC_DoorNO,a.SC_CardGuidNO,a.SC_EventTypeID
     *       ,a.SC_InOutStatus,a.SC_AddTime
     * 	  ,b.SC_WorkerNO,b.SC_Name,b.SC_MobileNO,b.SC_IDTypeID,b.SC_DepartmentID
     *       ,c.SC_DepartmentName
     * from PongeeESD6806_CN.dbo.SC_AccessRecord a,PongeeESD6806_CN.dbo.SC_Employee b,PongeeESD6806_CN.dbo.SC_Department c
     * where 1=1
     *
     * and a.SC_AddTime between '2020-06-02 00:00:00' and '2020-06-02 09:00:00'
     * --<if test="time1!=null and time2!=null">
     * --    and a.SC_AddTime between #{time1,jdbcType=TIMESTAMP} and #{time2,jdbcType=TIMESTAMP}
     * --</if >
     * and a.SC_DoorNO in (1,5,6,13,15,7,16)
     * --<if test="floorx != null and floorx != '' and floorx ==0 ">
     * --    and a.SC_DoorNO in (1,5,6,13,15,7,16)
     * --</if >
     * --<if test="floorx != null and floorx != '' and floorx ==1 ">
     * --    and a.SC_DoorNO in (2,3,8,12,14,9)
     * --</if >
     * --<if test="floorx != null and floorx != '' and floorx ==2 ">
     * --    and a.SC_DoorNO in (4,10,11)
     * --</if >
     *
     * and b.SC_DepartmentID=c.SC_DepartmentID
     * and RIGHT('000'+convert(varchar(20),a.SC_CardGuidNO),10) = b.SC_MobileNO
     *
     * --and b.SC_WorkerNO like '%CQ02754%'
     * --<if test="jobX!=null and jobX!=''">
     * --    and b.SC_WorkerNO like '%'+#{jobX,jdbcType=NVARCHAR}+'%'
     * --</if >
     * --and b.SC_Name like '%浩%'
     * --<if test="nameX!=null and nameX!=''">
     * --    and b.SC_Name like '%'+ #{nameX,jdbcType=NVARCHAR} +'%'
     * --</if >
     * and b.SC_DepartmentID = 39
     * --<if test="departmentx!=null">
     * --    and b.SC_DepartmentID = #{departmentx,jdbcType=INTEGER}
     * --</if >
     *
     * order by a.SC_CardGuidNO,a.SC_SerierNO
     */
}

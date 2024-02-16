package com.spring.controller;
import com.spring.entity.Shoucangjilu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import util.*;
import java.util.*;
import dao.CommDAO;
import net.jntoo.db.Query;
import com.alibaba.fastjson.*;

import javax.servlet.http.HttpServletRequest;
/**
 * 首页控制器
 */
@Controller
public class IndexController extends BaseController{

    // 首页
    @RequestMapping(value = {"/" , "index"})
    public String Index(HttpServletRequest request)
    {

            ArrayList<HashMap> bhtList = Query.make("lunbotu").order("id desc").limit(5).select();
            assign("bhtList" , bhtList);


            ArrayList<HashMap> jingdianxinxilist1 = Query.make("jingdianxinxi").limit(4).order("liulanliang desc").select();
            if(request.getSession().getAttribute("username")!=null)
                jingdianxinxilist1 = autoSort(request);
            assign("jingdianxinxilist1" , jingdianxinxilist1);


            ArrayList<HashMap> difangmeishilist2 = Query.make("difangmeishi").limit(4).order("id desc").select();
            assign("difangmeishilist2" , difangmeishilist2);


            ArrayList<HashMap> lvyouxianlulist3 = Query.make("lvyouxianlu").limit(4).order("id desc").select();
            assign("lvyouxianlulist3" , lvyouxianlulist3);


            ArrayList<HashMap> xinwenxinxilist4 = Query.make("xinwenxinxi").limit(4).order("id desc").select();
            assign("xinwenxinxilist4" , xinwenxinxilist4);
                            if(isAjax())
        {
            return json();
        }
        return "index";

    }

    /**
     * 协同算法（按收藏推荐）
     */
    public ArrayList<HashMap> autoSort(HttpServletRequest request){
        String username = request.getSession().getAttribute("username").toString();
        //查看用户收藏的景点
        //获得当前登录用户收藏的景点集合 最新收藏的排在最前
        ArrayList<HashMap> storeups = Query.make("shoucangjilu")
                .where("username", username)
                .where("biao","jingdianxinxi")
                .limit(0,4)
                .order("addtime desc").select();
        List<String> inteltypes = new ArrayList<String>();
        ArrayList<HashMap> jingdianEntityList = new ArrayList<HashMap>();
        //去重
        // 判断当前用户是否收藏了景点
        if(storeups!=null && storeups.size()>0) {
            // 收藏了 然后遍历当前收藏的景点合集
            for(HashMap s : storeups) {
                // 根据用户喜欢的景点类型获得当前类型的其他景点
                jingdianEntityList.addAll(Query.make("jingdianxinxi").where("jingdianmingcheng",s.get("biaoti")).select());
            }
        }
        //查询用户收藏景点的对应分类列表
        List<HashMap> pageList = Query.make("jingdianxinxi")
                .order("id desc").select();
        int limit = 4;
        if(jingdianEntityList.size() < limit) {
            //如果推荐的景点数量不够，获取同类型的景点进行填充
            int toAddNum = (4-jingdianEntityList.size())<=pageList.size()?(limit-jingdianEntityList.size()):pageList.size();
            for(HashMap o1 : pageList) {
                boolean addFlag = true;
                for(HashMap o2 : jingdianEntityList) {
                    if(String.valueOf(o1.get("id")).equals(String.valueOf(o2.get("id")))) {
                        addFlag = false;
                        break;
                    }
                }
                if(addFlag) {
                    jingdianEntityList.add(o1);
                    if(--toAddNum==0) break;
                }
            }
        }
        return jingdianEntityList;
    }
}

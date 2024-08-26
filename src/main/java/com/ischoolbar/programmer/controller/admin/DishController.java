package com.ischoolbar.programmer.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ischoolbar.programmer.entity.admin.Dish;
import com.ischoolbar.programmer.entity.admin.Pager;
import com.ischoolbar.programmer.entity.admin.dish.Dishes;
import com.ischoolbar.programmer.service.admin.dish.DishService;
import com.ischoolbar.programmer.util.BillCodeUtil;
import com.ischoolbar.programmer.util.PagerTools;
import com.ischoolbar.programmer.util.RedisUtil;

@Controller
public class DishController {
    @Autowired
    private DishService dishService;
    @Resource(name = "redisUtil")
    private RedisUtil redisUtil;

    @ResponseBody
    @RequestMapping(value = "/addDish.do", produces = "application/json;charset=utf-8")
    public Object addDish(Dish dish, @RequestParam(required = false) MultipartFile file,
                          HttpServletRequest request) {
        boolean addFlag = false;//添加标记
        String fifleFlag = null;//文件上传
        if (!file.isEmpty()) {
        	String savePath = request.getServletContext().getRealPath("/") + "/resources/upload/";
        	String contextPath = request.getContextPath();
    		File savePathFile = new File(savePath);
    		if(!savePathFile.exists()){
    			//若不存在改目录，则创建目录
    			savePathFile.mkdir();
    		}
            System.out.println("存储地址:" + savePath);
            String oldFileName = file.getOriginalFilename();//获取原文件名
            System.out.println("原文件名:" + oldFileName);
            String newFileName = BillCodeUtil.getBillCode() + "_"+oldFileName;//新文件名
            String prefix = FilenameUtils.getExtension(oldFileName);//获取文件后缀，对后缀进行判断，过滤不符合条件文件
            System.out.println("~~~文件类型:" + prefix + "~~~");
            System.out.println("~~~文件大小:" + file.getSize() + "~~~");
            if(!"jpg,jpeg,gif,png".toUpperCase().contains(prefix.toUpperCase())){
            	  fifleFlag = "请上传正确格式图片";
    		}else {
    			  System.out.println("~~~文件类型验证通过~~~");
    			  if (file.getSize() < PagerTools.maxFileSize) {
                      System.out.println("~~~~~~~~~文件大小验证通过~~~~~~~~~~~");
                      try {
                          file.transferTo(new File(savePath+newFileName));
                          System.out.println("上传成功");
                          dish.setFileName(request.getServletContext().getContextPath() + "/resources/upload/" + newFileName);//将新文件名保存在对象
                          addFlag = dishService.addDish(dish);//添加至数据库
                          System.out.println("菜品" + dish.getDishName() + "添加成功");
                      } catch (IllegalStateException e) {
                          e.printStackTrace();
                      } catch (IOException e) {
                          e.printStackTrace();
                      }
                  } else {
                      fifleFlag = "图片不能大于500KB";
                  }
    		} 
        }
        if (addFlag) {//添加成功
            //刷新缓存
            redisUtil.del("allDishes");
            return JSON.toJSONString("OK");
        } else {
            return "{'fifleFlag':'" + fifleFlag + "'}";
        }
    }

    //ajax验证菜品名是否存在
    @ResponseBody
    @RequestMapping("/dishNameIsExist.do")
    public Object dishNameIsExist(@RequestParam(required = false) String dishName) {
        boolean existFlag = false;
        if (dishName != "" && dishName != null) {
            existFlag = dishService.isExist(dishName);
        }
        if (existFlag) {
            return JSON.toJSONString("0");//存在该菜品
        }
        return JSON.toJSONString("1");//不存在该菜品
    }


    //加载菜品列表
    @ResponseBody
    @RequestMapping("/dishList.do")
    public ModelAndView dishList(Pager pager, ModelAndView modelAndView,
                                 @RequestParam(required = false) String dishName) {
        if (dishName == null || "".equals(dishName)) {//如果没有输入查询名字则当作opr=allDish
            pager.setOpr("allDish");
        }
        modelAndView.setViewName("/daily/dish/dishList");
        Dishes dishes = Dishes.getDishes();
        pager.setPageSize(PagerTools.delDishListPagerSize);
        if (dishes.getAllDishCount() == 0) {
            if(redisUtil.getList("allDishes")==null){
                redisUtil.addList("allDishes",dishService.getDish());
            }
            dishes.setAllDish(redisUtil.getList("allDishes"));
        }
        pager.setTotalCount(dishes.getAllDishCount());
        if ("serchDish".equals(pager.getOpr())) {//优先查询
            pager.setPageSize(dishes.getAllDishCount());//搜索时把所有菜品全部查询出来
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~" + dishName);
        }
        pager.count();
        List<Dish> list = dishes.getAllDish(pager);
        if ("serchDish".equals(pager.getOpr())) {//优先查询
            List<Dish> serchList = new ArrayList<Dish>();
            if (dishName != null && !"".equals(dishName)) {
                System.out.println("~~~~~~~~查找相同菜品的数据~~~~~~~~~~");
                for (Dish dish : list) {
                    if(dish.getDishName().contains(dishName)){
                        System.out.println("~~~~~~~~~~~找到 ?"+dishName+"相似的菜品~~~~~~~~~~~");
                        serchList.add(dish);
                    }
                }
                pager.setList(serchList);
            }
        } else {
            pager.setList(list);
        }
        modelAndView.addObject("pager", pager);
        return modelAndView;
    }

    //ajax删除菜品
    @ResponseBody
    @RequestMapping("/delDishFromMenu.do")
    public Object delDish(@RequestParam(required = false)String id){
        System.out.println("~~~~~~~~~~~~~~ ?始删除Dish~~~~~~~~~~~~~~~");
        boolean delFlag=false;
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~id="+id);
        if(id!=null && !"".equals(id)){
            System.out.println("~~~~~~~~~~~~~~~进入删除~~~~~~~~~~~~~~~~");
            delFlag=dishService.delDishById(id);
        }
        if(delFlag){//删除成功
            System.out.println("~~~~~~~~~~~~~~删除Dish成功~~~~~~~~~~~~~~~");
            redisUtil.del("allDishes");//刷新redis
            Dishes.getDishes().setAllDish(null);//刷新单例
            return JSON.toJSONString(0);//删除成功
        }
        return JSON.toJSONString(1);//删除失败
    }

}

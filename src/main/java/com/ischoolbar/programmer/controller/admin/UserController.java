package com.ischoolbar.programmer.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ischoolbar.programmer.entity.admin.Pager;
import com.ischoolbar.programmer.entity.admin.User;
import com.ischoolbar.programmer.service.admin.user.UserService;
import com.ischoolbar.programmer.util.PagerTools;

@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping("/addUser.do")
    public Object addUser(User user){
        return JSON.toJSONString(userService.addUser(user));
    }


    @RequestMapping("/showUserList.do")
    public ModelAndView showUserList(ModelAndView modelAndView, Pager pager){
        pager.setPageSize(PagerTools.deskBillPagerSize);
        pager.setTotalCount(userService.getTotalCount(pager));
        pager.count();
        pager.setList(userService.showUserList(pager));
        modelAndView.addObject("pager",pager);
        modelAndView.setViewName("/daily/user/userList");
        return modelAndView;
    }
    @RequestMapping("/delUserById.do")
    @ResponseBody
    public Object delUserById(User user){
        return JSON.toJSONString(userService.delUserById(user));
    }
    @RequestMapping("/updateUser.do")
    @ResponseBody
    public Object updateUser(User user){
        return JSON.toJSONString(userService.updateUser(user));
    }
    @RequestMapping("/loadUserInfoById.do")
    public ModelAndView loadUserInfoById(ModelAndView modelAndView,Pager pager){
        pager.setPageSize(1);
        pager.setTotalCount(userService.getTotalCount(pager));
        pager.count();
        modelAndView.addObject("user",userService.showUserList(pager).get(0));
        modelAndView.setViewName("/daily/user/updateUser");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/userIsExist.do")
    public Object userIsExist(User user){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(user.getIdentity()+user.getUserCode()+user.getPhone());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        int phoneFlag=-1;
        int identityFlag=-1;
        int userCodeFlag=-1;
        if(user.getUserCode()!=null && !user.getUserCode().equals("")){
            userCodeFlag=userService.userIsExist(user);
        }
        if(user.getIdentity()!=null && !"".equals(user.getIdentity())){
            identityFlag=userService.userIsExist(user);
        }
        if(user.getPhone()!=null && !"".equals(user.getPhone())){
            phoneFlag=userService.userIsExist(user);
        }
        return "{\"userCodeFlag\":\""+userCodeFlag+"\",\"phoneFlag\":\""+phoneFlag+"\",\"identityFlag\":\""+identityFlag+"\"}";
    }
}

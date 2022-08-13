package com.atguigu.myssm.myspringmvc;

/**
 * 设计PageController的目的 是为了在进行页面跳转的时候可以让页面经过thymeleaf的渲染 让其中的thymeleaf表达式起作用
 * 即 负责找到所需要的页面资源
 */
public class PageController {
    public String page(String page){
        return page ;       // frames/left
    }
}

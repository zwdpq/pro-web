package com.atguigu.myssm.filters;

import com.atguigu.myssm.trans.TransManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 事务过滤器
 * 确保业务的操作处在事务的监听之下
 */
@WebFilter("*.do")
public class TransActionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            TransManager.begingTrans();
            System.out.println("事务已开启了");
            filterChain.doFilter(servletRequest,servletResponse);
            System.out.println("TransFilter已放行");
            TransManager.commitTrans();
            System.out.println("事务已提交了");
        }catch (Exception e){
            e.printStackTrace();
            try {
                TransManager.rollbackTrans();
                System.out.println("事务已回滚了");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}

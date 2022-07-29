package com.atguigu.myssm.myspringmvc;

import com.atguigu.myssm.Util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet的核心处理器 用于分发request到各种Controller中
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
    private Map<String,Object> beanMap = new HashMap<>();

    /**
     * 将application中的class加载到Map容器中
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        try {
            //读取applicationContext.xml文件
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            //进行指定到对应Controller解析
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(is);
            NodeList beans = document.getElementsByTagName("bean");
            for (int i = 0; i < beans.getLength(); i++){
                Node node = beans.item(i);
                //抽取出Element子节点 封装到Map中
                if (Node.ELEMENT_NODE == node.getNodeType()){
                    Element element = (Element)node;
                    String beanId =  element.getAttribute("id");
                    String classPath =  element.getAttribute("class");
                    Class controllerBeanClass = Class.forName(classPath);
                    Object beanObj = controllerBeanClass.newInstance();
                    //57~58 为了让ViewBaseServlet能够获取到ServletContext 故在此赋值
                    Method setSerlvetContextMethod = controllerBeanClass.getDeclaredMethod("setServletContext",ServletContext.class);
                    setSerlvetContextMethod.invoke(beanObj, this.getServletContext());
                    beanMap.put(beanId,beanObj);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理请求 执行方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //获取请求对应的要运行的Controller方法名
        StringBuffer url = req.getRequestURL();
        int start = url.indexOf("pro08");
        String subUrl = url.substring(start);
        int end = subUrl.lastIndexOf(".do");
        subUrl = subUrl.substring(6, end);
        Object controllerBeanObj = beanMap.get(subUrl);
        String operate = req.getParameter("operate");
        if (StringUtil.isEmpty(operate)){
            operate = "index";
        }
        //反射执行对应的方法
        try {
            Method method = controllerBeanObj.getClass().getDeclaredMethod(operate, HttpServletRequest.class, HttpServletResponse.class);
            if (method != null){
                method.setAccessible(true);
                method.invoke(controllerBeanObj,req,resp);
            }else{
                throw new RuntimeException("operate参数有误");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

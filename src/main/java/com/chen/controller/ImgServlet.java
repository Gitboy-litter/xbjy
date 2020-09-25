package com.chen.controller;

import com.alibaba.fastjson.JSONObject;
import com.chen.Utils.VerifyCode;
import com.chen.constants.Constant;
import com.chen.entiy.User;
import com.chen.service.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.controller
 * @Author: ChenZengWen
 * @Description: 描述
 * @Date: 2020/9/23 15:00
 * @Version: 1.0
 */
@WebServlet("/img/*")
public class ImgServlet extends BaseServlet {
    private UserService userService = new UserService();

    public void headimg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method headimg
         * @Author ChenZW
         * @Version  1.0
         * @Param [request, response]
         * @Description 显示头像
         * @Return void
         * @Date 2020/9/24 19:46
         *@版权归属于ChenZW
         */
        String img = request.getParameter("img");
        String path = Constant.LOGIN_IMG + img;
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        ServletOutputStream outputStream = response.getOutputStream();
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }
        outputStream.flush();
        outputStream.close();
        fileInputStream.close();
    }

    public void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method upload
         * @Author ChenZW
         * @Version  1.0
         * @Param [request, response]
         * @Description 头像更新
         * @Return void
         * @Date 2020/9/24 19:46
         *@版权归属于ChenZW
         */
        HashMap<String, String> hashMap = new HashMap<>();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        String pic = "";
        String path = "";
        //创建解析类的实例 传入工厂类获取文件上传对象
        ServletFileUpload sfu = new ServletFileUpload(factory);
        try {
            List<FileItem> fileItems = sfu.parseRequest(request);
            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()) {
                    String name = fileItem.getName();
                    String suffix = name.split("\\.")[1];
                    pic = (UUID.randomUUID() + "." + suffix).replace("-", "");
                    path = Constant.LOGIN_IMG + pic;
                    File file = new File(path);
                    if (!file.exists()) {
                        fileItem.write(file);
                    }
                }
            }
            User loginUser = (User) request.getSession().getAttribute(Constant.SESSION_LOGIN);
            userService.updatePic(loginUser.getId(), pic);
            loginUser.setPic(pic);
            request.getSession().setAttribute(Constant.SESSION_LOGIN, loginUser);
            hashMap.put("pic", pic);
            hashMap.put("code", "200");
        } catch (Exception e) {
            e.printStackTrace();
            hashMap.put("code", "400");
        }
        response.getWriter().write(JSONObject.toJSONString(hashMap));
    }
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method verifyCode
         * @Author ChenZW
         * @Version  1.0
         * @Param [request, response]
         * @Description 调用验证码生产工具，生产验证码
         * @Return void
         * @Date 2020/9/24 19:45
         *@版权归属于ChenZW
         */
        VerifyCode vc=new VerifyCode();
        BufferedImage image = vc.getImage();
        String str = vc.getText();
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        request.getSession().setAttribute(Constant.LOGIN_CODE,str);
        ImageIO.write(image,"JPEG", response.getOutputStream());
    }
}

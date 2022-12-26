package com.zls.project.servlet;

import java.io.*;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

import com.zls.project.dao.CookieDao;
import com.zls.project.dao.impl.CookieDaoImpl;
import com.zls.project.dao.impl.StudentDaoImpl;
import com.zls.project.entity.Cookie;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

// 扩展 HttpServlet 类
public class UploadFileHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "resource_upload";

    // 上传配置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
    private String message;

    public void init() throws ServletException {
    }

    public String queryShouldDirPath(String uploadPath, String fieldName, String CookieId, String filename) {
        com.zls.project.entity.Cookie cookie = new CookieDaoImpl().getCookieById(CookieId);
        String Pid = cookie.getPid();
        String Sname = new StudentDaoImpl().getStudentNameById(Pid);
        String[] infoList = fieldName.split("_");
        String Jid = infoList[0];
//        String fileNumber = infoList[1];

        String pathDir = uploadPath + File.separator + Pid + " " + Sname;
        File pathDirFile = new File(pathDir);
        if (!pathDirFile.exists()) {
            pathDirFile.mkdir();
        }
        pathDir =  pathDir + File.separator + Jid;
        pathDirFile = new File(pathDir);
        if (!pathDirFile.exists()) {
            pathDirFile.mkdir();
        }
        return uploadPath + File.separator + Pid + " " + Sname + File.separator + Jid + File.separator + filename;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return;
        }
        String CookieId = null;
        for (javax.servlet.http.Cookie cookie : cookies) {
            if (cookie.getName().equals("CookieId")) {
                if (!new CookieDaoImpl().existById(cookie.getValue())) {
                    return;
                } else {
                    CookieId = cookie.getValue();
                }
                break;
            }
        }
        int autoNameFlag = 0;
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            response.setHeader("Content-Type", "text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        // 中文处理
        upload.setHeaderEncoding("UTF-8");

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
//        String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
        String uploadPath = request.getServletContext().getRealPath("./") + UPLOAD_DIRECTORY;
        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // 解析请求的内容提取文件数据
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理文件
                    if (!item.isFormField() && !item.getName().equals("")) {
                        String fileName = new File(item.getName()).getName();
                        System.out.println("item.getName():" + item.getName());
                        System.out.println("new File(item.getName()).getName():" + new File(item.getName()).getName());
                        System.out.println("item.getFieldName()" + item.getFieldName());
//                        System.out.println("item.getString()" + item.getString());
                        String filePath = null;
                        String fieldName = item.getFieldName();
                        filePath = this.queryShouldDirPath(uploadPath, fieldName, CookieId, fileName);

                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                        request.setAttribute("message",
                                "文件上传成功!");
                    } else {
                        System.out.println("" + item.getName() + "is not type of file");
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "错误信息: " + ex.getMessage());
        }
        System.out.println("");
        // 跳转到 message.jsp
        request.getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    public void destroy() {
    }
}
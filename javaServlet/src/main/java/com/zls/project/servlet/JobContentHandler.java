package com.zls.project.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zls.project.db.DatabaseAccess;
import com.zls.project.entity.Job;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

public class JobContentHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ////////////////////////////////////////////////////////////////////////////
        response.setHeader("Content-Type", "text/html;charset=utf-8");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            if (!"id".equals(parameterName)) {
                continue;
            }
            String[] parameterValues = request.getParameterValues(parameterName);
            String id = parameterValues[0];
            List<Job> jobs = DatabaseAccess.getJobCourse(id);
            String course_name = new String(DatabaseAccess.getCourseName(id));
            ObjectMapper mapper = new ObjectMapper();
            String json_job = mapper.writeValueAsString(jobs);
            String json = "{\"id\":\"" + id + "\", \"course_name\":\"" + course_name + "\",\"jobs\":" + json_job + "}";
            PrintWriter out = response.getWriter();
            out.write(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}

package com.huatugz.flycommen;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;

import com.google.gson.Gson;
import com.huatugz.flycommen.db.DbBeanInterface;
import com.huatugz.flycommen.db.DbService;
import com.huatugz.flycommen.framework.LogUtils;
import com.huatugz.flycommen.helper.SqlLiteHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
        System.out.println("--------------------------------------");

    }

    public void testDb() {
        Context context = getContext();
        Student s1 = new Student("李丽华", 20, "10010");
        SqlLiteHelper sqlLiteHelper = new SqlLiteHelper(getSystemContext());
        System.out.println("--------------------------------------" + sqlLiteHelper == null);
        final DbService service = new DbService(sqlLiteHelper);
        service.deleteByType("no_type");


        for (int i=0;i<100;i++)
        {
            final int finalI = i;
            new Thread(){
                @Override
                public void run() {
                    service.insert(new Student("线程"+(finalI+1),100-finalI,"线程id"+finalI));
                  LogUtils.d("flyisme", finalI +"线程  批量插入:" + new Gson().toJson(service.queryByType("no_type", Student.class)));
                }
            }.start();
        }
        try {
            Thread.sleep(2000);
            LogUtils.d("flyisme", "条数:" + service.queryByType("no_type", Student.class,5000).size());
            LogUtils.d("flyisme", "数据查找:" + new Gson().toJson(service.queryById("线程id10", Student.class,5000)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public class Student implements DbBeanInterface {

        private String name;
        private int age;
        private String persionId;

        public Student(String name, int age, String persionId) {
            this.name = name;
            this.age = age;
            this.persionId = persionId;
        }

        @Override
        public String getDb_Id() {
            return persionId;
        }

        @Override
        public String getDb_Type() {
            return null;
        }
    }
}
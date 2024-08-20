package org.example.person_management.pub.handler;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;
import java.sql.Timestamp;

@Configuration
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("updateDt", new Timestamp(System.currentTimeMillis()), metaObject);
        this.setFieldValByName("insertDt", new Timestamp(System.currentTimeMillis()), metaObject);
        this.setFieldValByName("dr", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateDt", new Timestamp(System.currentTimeMillis()), metaObject);
    }
}

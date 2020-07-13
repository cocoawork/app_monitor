package top.cocoawork.conf;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TableFieldHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter("createAt")) {
            if (metaObject.getValue("createAt") == null) {
                this.setFieldValByName("createAt", LocalDateTime.now(), metaObject);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter("updateAt")) {
            if (metaObject.getValue("updateAt") == null) {
                this.setFieldValByName("updateAt", LocalDateTime.now(), metaObject);
            }
        }
    }
}

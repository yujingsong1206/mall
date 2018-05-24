package com.mall.util;

import com.google.common.collect.Lists;
import com.mall.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by sjyjs
 */
public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //对象的所有字段全部列入
        objectMapper.setSerializationInclusion(Inclusion.ALWAYS);
        //取消默认转化timestamps形式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        //忽略空Bean转json的错误
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        //所有日期格式都统一为以下格式，即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.STANARD_FORMAT));

        //忽略在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 把对象转化成json字符串（未格式化）
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String obj2String(T obj){
        if(obj == null){
            return null;
        }
        try{
            return obj instanceof String ? (String)obj : objectMapper.writeValueAsString(obj);
        }catch (Exception e){
            logger.warn("Pares object to String error.", e);
            return null;
        }
    }

    /**
     * 把对象转化成json字符串（格式化）
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String obj2StringPretty(T obj){
        if(obj == null){
            return null;
        }
        try{
            return obj instanceof String ? (String)obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        }catch (Exception e){
            logger.warn("Pares Object to String error.", e);
            return null;
        }
    }

    public static <T> T string2Obj(String str, Class<T> clazz){
        if(StringUtils.isEmpty(str) || clazz == null){
            return null;
        }
        try{
            return clazz.equals(String.class) ? (T)str: objectMapper.readValue(str,clazz);
        }catch (Exception e){
            logger.warn("Pares String to Object error.", e);
            return null;
        }
    }

    public static <T> T string2Obj(String str, Class<?> collectionClass, Class<?>... elementClass){
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClass);
        try{
            return objectMapper.readValue(str,javaType);
        }catch (Exception e){
            logger.warn("Pares String to Object error.", e);
            return null;
        }
    }

    public static <T> T string2Obj(String str, TypeReference<T> typeReference){
        if(StringUtils.isEmpty(str) || typeReference == null){
            return null;
        }
        try{
            return (T)(typeReference.getType().equals(String.class) ? (T)str: objectMapper.readValue(str,typeReference));
        }catch (Exception e){
            logger.warn("Pares String to Object error.", e);
            return null;
        }
    }

    public static void main(String[] args) {
        User user1 = new User();
        user1.setId(1);
        user1.setPhone("123");
        User user2 = new User();
        user2.setId(2);
        user2.setPhone("123");
        List<User> userList = Lists.newArrayList();
        userList.add(user1);
        userList.add(user2);
        String userListStr = JsonUtil.obj2String(userList);
        logger.info(userListStr);
        List<User> ss1 = JsonUtil.string2Obj(userListStr, new TypeReference<List<User>>() {
        });
        List<User> ss2 = JsonUtil.string2Obj(userListStr, List.class, User.class);
        System.out.println("end");
    }

}

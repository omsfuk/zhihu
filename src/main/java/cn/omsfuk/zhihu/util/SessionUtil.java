package cn.omsfuk.zhihu.util;

import cn.omsfuk.zhihu.entity.User;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Talk is cheap. Show me the code
 * 多说无益，代码上见真章
 * -------  by omsfuk  2017/8/15
 */
public abstract class SessionUtil {

    public static User user() {
        return (User) RequestContextHolder.getRequestAttributes().getAttribute("user", RequestAttributes.SCOPE_SESSION);
    }

    public static void setAttribute(String key, Object object) {
        RequestContextHolder.getRequestAttributes().setAttribute(key, object, RequestAttributes.SCOPE_SESSION);
    }
}

package cn.gaozheng.mini.util;
import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static cn.gaozheng.mini.util.Constants.SESSION_USER_CACHE_KEY;

/**
 * SessionUtil
 *
 * @author Cheng Bo
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SessionUtil {

    private static <T> T get(HttpSession session, String key) {
        Preconditions.checkNotNull(session);
        return (T) session.getAttribute(key);
    }

    public static <T> T  getSignInClient(HttpSession session) {
        return get(session, SESSION_USER_CACHE_KEY);
//        return (T) getClient();
    }


}

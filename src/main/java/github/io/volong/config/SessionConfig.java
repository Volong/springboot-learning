package github.io.volong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * maxInactiveIntervalInSeconds: 设置 Session 失效时间，使用 Redis Session 之后，SpringBoot 的 server.session.timeout 属性不再生效
 *
 * @time 2019-04-11
 */

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400 * 30)
public class SessionConfig {


}

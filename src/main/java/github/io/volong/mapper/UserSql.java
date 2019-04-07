package github.io.volong.mapper;

import github.io.volong.domain.UserSexEnum;
import github.io.volong.param.UserParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserSql {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSql.class);

    public String getList(UserParam userParam) {

        StringBuffer sql = new StringBuffer("select id, userName, passWord, user_sex as userSex, nick_name as nickName");
        sql.append(" from users where 1 = 1 ");
        if (userParam != null) {
            if (StringUtils.isNotBlank(userParam.getUserName())) {
                sql.append(" and userName = #{userName}");
            }
            if (StringUtils.isNotBlank(userParam.getUserSex())) {
                sql.append(" and user_sex = #{userSex}");
            }
        }

        sql.append(" order by id desc");
        sql.append(" limit " + userParam.getBeginLine() + "," + userParam.getPageSize());

        LOGGER.info("getList sql is: " + sql.toString());

        return sql.toString();
    }

    /**
     * 如果觉得上面拼接 SQL 太麻烦，可以使用下面的结构化 SQL
     *
     * @param userParam
     * @return
     */
    public String getCount(UserParam userParam) {
        String sql = new SQL() {{
            SELECT("count(1)");
            FROM("users");

            if (StringUtils.isNotBlank(userParam.getUserName())) {
                WHERE("userName = #{userName}");
            }
            if (StringUtils.isNotBlank(userParam.getUserSex())) {
                WHERE("user_sex = #{userSex}");
            }
        }}.toString();

        LOGGER.info("getCount sql is :" + sql);

        return sql;
    }
}

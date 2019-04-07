package github.io.volong.mapper.annotation;

import github.io.volong.domain.UserSexEnum;
import github.io.volong.entity.UserEntity;
import github.io.volong.mapper.UserSql;
import github.io.volong.param.UserParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 通过注解的方式进行配置
 */
public interface AnnotationUserMapper {


    @Select("select * from users")
    @Results({
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    List<UserEntity> getAll();


    @Select("select * from users where id = #{id}")
    @Results({
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    UserEntity getOneById(Long id);


    /**
     * 推荐使用 #，使用 $ 会有 SQL 注入的风险
     * @param user
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into users(userName, passWord, user_sex) values(#{userName}, #{passWord}, #{userSex})")
    Long insert(UserEntity user);

    @Update("update users set userName = #{userName}, nick_name = #{nickName} where id = #{id}")
    void update(UserEntity user);

    @Delete("delete from users where id = #{id}")
    int delete(Long id);

    @SelectProvider(type = UserSql.class, method = "getList")
    List<UserEntity> getList(UserParam userParam);

    @SelectProvider(type = UserSql.class, method = "getCount")
    int getCount(UserParam userParam);
}

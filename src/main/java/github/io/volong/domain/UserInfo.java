package github.io.volong.domain;

/**
 * 该接口用来接收连表查询后的结果
 */
public interface UserInfo {
    String getUserName();

    String getEmail();

    String getAddress();

    String getHobby();
}
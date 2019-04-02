package github.io.volong.mapper;

import github.io.volong.entity.UserEntity;

import java.util.List;

/**
 * 方法名需要与 UserMapper.xml 中的 id 名一致
 */
public interface UserMapper {

    List<UserEntity> getAll();

    UserEntity getOne(Long id);

    void insert(UserEntity user);

    void update(UserEntity user);

    void delete(Long id);
}
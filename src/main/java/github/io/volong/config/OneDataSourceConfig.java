package github.io.volong.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "github.io.volong.mapper.one", sqlSessionTemplateRef = "oneSqlSessionTemplate")
public class OneDataSourceConfig {

    @Primary
    @Bean("oneSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("oneDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Primary
    @Bean("oneTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("oneDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    @Primary
    @Bean("oneSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("oneSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

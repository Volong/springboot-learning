package github.io.volong.swagger.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()

                // 扫描指定包下面的接口
                // .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("github.io.volong.springbootswagger.controller"))

                // 扫描指定的接口
                // .paths(PathSelectors.any())
                .paths(Predicates.or(PathSelectors.ant("/user/add"),
                        PathSelectors.ant("/user/find/*")))

                .build()
                .apiInfo(apiInfo())

                // 不实用默认的 HTTP 响应
                .useDefaultResponseMessages(false)

                // 覆盖 GET 方法的 500 与 403 错误信息
                .globalResponseMessage(RequestMethod.GET, newArrayList(
                        new ResponseMessageBuilder()
                                .code(500)
                                .message("服务器异常")
                                .responseModel(new ModelRef("Error"))
                                .build(),
                        new ResponseMessageBuilder()
                                .code(403)
                                .message("资源不可用")
                                .build()));
    }

    /**
     * 配置相关信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfo("SpringBoot 集成 Swagger Demo", "Demo", "0.0.0", "",
                new Contact("", "", ""),
                "Apache", "", Collections.emptyList());
    }
}

package top.cocoawork.monitor.web.conf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@ConditionalOnProperty(value = "swagger.enable", havingValue = "true")
public class SwaggerConfig {

    @Bean
    public Docket docket() {

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("app_monitor")
                .description("定时抓取appstore数据")
                .version("1.0.0")
                .build();


        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("token").description("user token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build(); //header中的ticket参数非必填，传空也可以
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.cocoawork.monitor.web.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

}

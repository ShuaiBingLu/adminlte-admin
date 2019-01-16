package cn.bin2.sport.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.ConcurrentSessionFilter;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 13:31 2019/1/16
 * @Modified By:
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = false)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
     *
     * @Description:
     * 该方法用来实现spring security的一些自定义的配置，其中就包括Filter的创建。
     * 其中http.authorizeRequests()、http.formLogin()、http.httpBasic()
     * 分别创建了ExpressionUrlAuthorizationConfigurer，FormLoginConfigurer，
     * HttpBasicConfigurer。在三个类从父级一直往上找，
     * 会发现它们都是SecurityConfigurer的子类。
     * SecurityConfigurer中又有configure方法。该方法被子类实现就用于创建各个过滤器，
     * 并将过滤器添加进HttpSecurity中维护的装有Filter的List中，
     * 比如HttpBasicConfigurer中的configure方法
     * @auther: bingshuai.lu
     * @date: 15:27 2019/1/16
     * @param: [http] HttpSecurity的父类是AbstractConfiguredSecurityBuilder
     * ，该类中有个configure方法用来获取所有SecurityConfigurer，
     * 并调用所有SecurityConfigurer的configure方法。
     * @return: void
     *
     */
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationProvider securityProvider;
    @Override
    protected UserDetailsService userDetailsService() {
        //自定义用户信息类
        return this.userDetailsService;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .anyRequest().authenticated() //其他的路径均需要认证才能访问
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    //通过spring secuirty提供的后处理bean的方式
                    //往FilterSecurityInterceptor中注入自定义的AccessDecisionManager
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O filterSecurity) {
                        filterSecurity.setAccessDecisionManager(myAccessDecisionManager);
                        return filterSecurity;
                    }
                }) //其他的页面必须登录才可以访问
                .and()
                .formLogin() //表单登录
                .loginPage("/login") //登录页面
                .successHandler((req,resp,auth)->{
                    //获取登录者信息
                    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    if (principal != null && principal instanceof UserDetails) {
                        UserDetails user = (UserDetails) principal;
                        //维护在session中
                        req.getSession().setAttribute("userDetail", user);
                        resp.sendRedirect("/");
                    }
                }) //认证成功后的处理
                .failureHandler((req,resp,authException)->{

                    resp.sendRedirect("/login?error");
                })//认证失败后的处理
                .permitAll() //给登录页面的url，处理登录的url赋予permitAll的ConfigureAttribute，在AccessDecision中将会被放行
                .and()
                .authenticationProvider(securityProvider) //自定义验证的provider
                .logout() //退出登录
                .logoutUrl("/logout") //退出登录的地址
                .logoutSuccessUrl("/template/logout.jsp") //退出登录后的跳转地址
                .permitAll();//给退出登录后跳转的地址打上perimitAll的ConfigureAttribute
    }

    @Value("${login.auth.path}")
    private String authPath;
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.POST,authPath).and().ignoring().antMatchers(
                HttpMethod.GET,
                "/*.html",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js"
        );
        super.configure(web);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(securityProvider);
    }
}

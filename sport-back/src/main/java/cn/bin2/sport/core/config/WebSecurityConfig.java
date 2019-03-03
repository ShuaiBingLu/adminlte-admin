package cn.bin2.sport.core.config;

import cn.bin2.sport.common.domain.Admin;
import cn.bin2.sport.core.security.SpringSecurityUserHolder;
import cn.bin2.sport.core.security.UserInfoHolder;
import cn.bin2.sport.core.service.MyUserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 13:31 2019/1/16
 * @Modified By:
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
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
    @Bean
    public UserInfoHolder userInfoHolder(){
        return new SpringSecurityUserHolder();
    }
    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler() { //登入处理
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                //MyUserDetail userDetails = (MyUserDetail) authentication.getPrincipal();
                logger.info("USER :  LOGIN SUCCESS !  ");

                response.getWriter().write("ok");

                //super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SimpleUrlLogoutSuccessHandler urlLogoutHandler = new SimpleUrlLogoutSuccessHandler();
        urlLogoutHandler.setDefaultTargetUrl("/login?logout");
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
        http.authorizeRequests()
                .antMatchers("/adminlte/**", "/js/**","/plugins/**", "/webjars/**", "**/favicon.ico")
                .permitAll();
        http.authorizeRequests().antMatchers("/**").authenticated();
        http.formLogin() //表单登录
                .loginPage("/login").permitAll() //登录页面
                .failureUrl("/login?error").and().httpBasic();
        http.logout().logoutUrl("/user/logout").invalidateHttpSession(true).clearAuthentication(true)
               // .deleteCookies("JSESSIONID").
                 .logoutSuccessHandler(urlLogoutHandler);
                //.and().sessionManagement().maximumSessions(10).expiredUrl("/login").and()
                 //给登录页面的url，处理登录的url赋予permitAll的ConfigureAttribute，在AccessDecision中将会被放行


                /*
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    //通过spring secuirty提供的后处理bean的方式
                    //往FilterSecurityInterceptor中注入自定义的AccessDecisionManager
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O filterSecurity) {
                        //filterSecurity.setAccessDecisionManager(myAccessDecisionManager);
                        return filterSecurity;
                    }
                }); //其他的页面必须登录才可以访问*/

        http.exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
    }

    @Value("${login.auth.path}")
    private String authPath;

    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/adminlte/**", "/js/**","/plugins/**", "/webjars/**", "**/favicon.ico");
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
            }

            /**
             * @param charSequence 明文
             * @param s 密文
             * @return
             */
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(DigestUtils.md5DigestAsHex(charSequence.toString().getBytes()));
            }
        });

    }
}

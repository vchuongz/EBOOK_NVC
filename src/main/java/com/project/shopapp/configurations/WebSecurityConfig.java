package com.project.shopapp.configurations;

import com.project.shopapp.filtes.JwtTokenFilter;
import com.project.shopapp.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Configuration
//@EnableMethodSecurity
@EnableWebSecurity
@EnableWebMvc
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;
    @Value("${api.prefix}")
    private String apiPrefix;
    @Bean
    //Pair.of(String.format("%s/products", apiPrefix), "GET"),
    public SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception{
        http
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> {
                    requests
                            .requestMatchers(
                                    String.format("%s/users/register", apiPrefix),
                                    String.format("%s/users/login", apiPrefix)


                            )
                            .permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/roles**", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/users", apiPrefix)).hasAnyRole(Role.ADMIN)

                            .requestMatchers(GET,
                                    String.format("%s/payments/create-payment/**", apiPrefix)).permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/payments/create-payment", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/payments/vnpay-return", apiPrefix)).permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/payments/vnpay-return/**", apiPrefix)).permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/payments/momo/notify", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/categories**", apiPrefix)).permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/categories/**", apiPrefix)).hasAnyRole(Role.ADMIN)

                            .requestMatchers(PUT,
                                    String.format("%s/categories/**", apiPrefix)).hasAnyRole(Role.ADMIN)

                            .requestMatchers(DELETE,
                                    String.format("%s/categories/**", apiPrefix)).hasAnyRole(Role.ADMIN)

                            .requestMatchers(GET,
                                    String.format("%s/products", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/products**", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/products/search**", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/products/**", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/products/images/*", apiPrefix)).permitAll()

//                            .requestMatchers(POST,
//                                    String.format("%s/products**", apiPrefix)).hasAnyRole(Role.ADMIN, Role.OWNER)

                            .requestMatchers(POST,
                                    String.format("%s/products", apiPrefix)).hasRole(Role.ADMIN)


                            .requestMatchers(PUT,
                                    String.format("%s/products/**", apiPrefix)).hasAnyRole(Role.ADMIN, Role.OWNER)

                            .requestMatchers(DELETE,
                                    String.format("%s/products/**", apiPrefix)).hasAnyRole(Role.ADMIN, Role.OWNER)

                            .requestMatchers(GET,
                                    String.format("%s/products/shop/**", apiPrefix)).permitAll()



                            .requestMatchers(GET,
                                    String.format("%s/orders/**", apiPrefix)).permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/orders", apiPrefix)).permitAll()

                            .requestMatchers(PUT,
                                    String.format("%s/orders/**", apiPrefix)).hasRole(Role.ADMIN)

                            .requestMatchers(DELETE,
                                    String.format("%s/orders/**", apiPrefix)).hasRole(Role.ADMIN)



                            .requestMatchers(GET,
                                    String.format("%s/downloads/user/**", apiPrefix)).permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/downloads", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/downloads/product/**", apiPrefix)).permitAll()


                            .requestMatchers(POST,
                                    String.format("%s/order_details", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/order_details/order/**", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/order_details/**", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/order_details/top_selling**", apiPrefix)).permitAll()

                            .requestMatchers(PUT,
                                    String.format("%s/order_details/**", apiPrefix)).hasRole(Role.ADMIN)

                            .requestMatchers(DELETE,
                                    String.format("%s/order_details/**", apiPrefix)).hasRole(Role.ADMIN)

                            .requestMatchers(GET,
                                    String.format("%s/shops", apiPrefix)).permitAll() // Ai cũng xem được danh sách shop
                            .requestMatchers(GET,
                                    String.format("%s/shops/**", apiPrefix)).permitAll()
                            .requestMatchers(DELETE,
                                    String.format("%s/shops/**", apiPrefix)).permitAll()
                            .requestMatchers(PUT,
                                    String.format("%s/shops/**", apiPrefix)).permitAll()
                            .requestMatchers(POST,
                                    String.format("%s/shops/**", apiPrefix)).permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/shops/user/**", apiPrefix)).permitAll() // Cho phép truy cập shop theo userId





//                            .requestMatchers(POST,
//                                    String.format("%s/review**", apiPrefix)).permitAll()
//                            .requestMatchers(POST,
//                                    String.format("%s/review**", apiPrefix)).permitAll()
//                            .requestMatchers(POST,
//                                    String.format("%s/review/**", apiPrefix)).permitAll()
                            .requestMatchers(POST,
                                    String.format("%s/reviews", apiPrefix)).permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/reviews/product/**", apiPrefix)).permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/reviews/user/**", apiPrefix)).hasAnyRole(Role.OWNER, Role.ADMIN)
                            .requestMatchers(DELETE,
                                    String.format("%s/reviews/**", apiPrefix)).hasRole(Role.ADMIN)



                            .anyRequest().authenticated();
//                            .anyRequest().permitAll();

                })

                .csrf(csrf -> csrf.ignoringRequestMatchers(
                        String.format("%s/review/**", apiPrefix) // Vô hiệu hóa CSRF chỉ với Review API
                ))

                .csrf(AbstractHttpConfigurer::disable);
                http.cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
                    @Override
                    public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.setAllowedOrigins(List.of("*"));
                        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
                        configuration.setExposedHeaders(List.of("x-auth-token"));
                        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                        source.registerCorsConfiguration("/**", configuration);
                        httpSecurityCorsConfigurer.configurationSource(source);
                    }
                });
                return http.build();
    }



}

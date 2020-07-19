package com.udemy.restudemy.security;

import com.udemy.restudemy.context.SpringApplicationContext;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityConstants {

    public static final long EXPIRATION_TIME= 864000000;

    public static final String TOKEN_PREFIX= "Bearer ";

    public static final String HEADER_STRING= "Authorization";

    public static final String SIGN_UP_URL= "/users/create";

    public static String getTokenSecret()
    {
        AppProperties appProperties=(AppProperties) SpringApplicationContext.getBean("appProperties");
        return appProperties.getTokenSecret();
    }

}

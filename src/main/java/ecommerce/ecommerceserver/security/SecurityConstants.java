package ecommerce.ecommerceserver.security;

public class SecurityConstants {

    public static final String SIGN_UP_URL ="/api/users/**";

    public static final String H2_URL = "h2-console/**";

    public static final String SECRET = "SecretKeyToGenJwt";

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";

    public static final Long EXPIRATION_TIME = 30000L; //30 sec

}

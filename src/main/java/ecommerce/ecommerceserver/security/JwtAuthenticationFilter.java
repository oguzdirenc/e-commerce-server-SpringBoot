package ecommerce.ecommerceserver.security;

import ecommerce.ecommerceserver.domain.ApplicationUser;
import ecommerce.ecommerceserver.services.impl.CustomUserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

import static ecommerce.ecommerceserver.security.SecurityConstants.HEADER_STRING;
import static ecommerce.ecommerceserver.security.SecurityConstants.TOKEN_PREFIX;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailServiceImpl customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
            , FilterChain filterChain) throws ServletException, IOException {
        try {

            String jwt = getJwtFromRequest(httpServletRequest);

            if(StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
                UUID applicationUserId = jwtTokenProvider.getUserIdFromJwt(jwt);
                ApplicationUser applicationUser = customUserDetailService.loadUserById(applicationUserId);

                UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(
                        applicationUser,null, Collections.emptyList());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }

        }catch (Exception ex){
            logger.error("Kullanıcı doğrulaması gerçekleştirilemedi",ex);
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }


    private String getJwtFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader(HEADER_STRING);

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)){
            return bearerToken.substring(7,bearerToken.length());
        }
        return null;
    }

}

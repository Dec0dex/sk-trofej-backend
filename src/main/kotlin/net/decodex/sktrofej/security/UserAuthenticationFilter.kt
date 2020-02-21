package net.decodex.sktrofej.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import net.decodex.sktrofej.domain.dto.AuthenticationDto
import net.decodex.sktrofej.domain.dto.TokenDto
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class UserAuthenticationFilter(authenticationManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter() {

    init {
        this.authenticationManager = authenticationManager
        setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher(SecurityConstants.AUTH_LOGIN_URL, "POST"))
    }

    val gson by lazy { Gson() }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val credentials = ObjectMapper().readValue(request.inputStream, AuthenticationDto::class.java)
        val authenticationToken = UsernamePasswordAuthenticationToken(credentials.username, credentials.password)

        return authenticationManager.authenticate(authenticationToken)
    }

    override fun successfulAuthentication(
            request: HttpServletRequest, response: HttpServletResponse,
            filterChain: FilterChain, authentication: Authentication
    ) {
        val user = authentication.principal as SkTrofejUserPrincipal
        val roles = user.authorities.map(GrantedAuthority::getAuthority)

        val signingKey = SecurityConstants.JWT_SECRET.toByteArray()

        val token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("type", SecurityConstants.TOKEN_TYPE)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setSubject(user.username)
                .claim("roles", roles)
                .compact()

        response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token)
        response.contentType = "application/json"

        val tokenDto = TokenDto()
        tokenDto.token = token

        response.writer.write(gson.toJson(tokenDto))
        response.writer.flush()
        response.writer.close()
    }
}
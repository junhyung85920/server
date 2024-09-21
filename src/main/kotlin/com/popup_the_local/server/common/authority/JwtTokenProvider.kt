package com.popup_the_local.server.common.authority

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import io.jsonwebtoken.security.Keys
import java.util.*

const val ACCESS_TOKEN_EXPIRATION: Long = 1000 * 60 * 60

@Component
class JwtTokenProvider {
    @Value("\${jwt.secret}")
    lateinit var secretKey: String

    private val key by lazy {
        Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
    }

    fun createToken(memberId: String): String {
        val now = Date()
        val accessExpiration = Date(now.time + ACCESS_TOKEN_EXPIRATION)

        val accessToken =
            Jwts
                .builder()
                .setSubject(memberId.toString())
                .claim("memberId", memberId)
                .setIssuedAt(now)
                .setExpiration(accessExpiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact()

        return "Bearer $accessToken"
    }

    fun getAuthentication(token: String): Authentication {
        val claims: Claims = getClaims(token)

        val auth = claims["memberId"] ?: throw RuntimeException("잘못된 토큰입니다.")

        val authorities: Collection<GrantedAuthority> =
            (auth as String)
                .split(",")
                .map { SimpleGrantedAuthority(it) }

        val principal: UserDetails = User(claims.subject, "", authorities)

        return UsernamePasswordAuthenticationToken(principal, "", authorities)
    }

    fun validateToken(accessToken: String): Boolean {
        try {
            getClaims(accessToken)
            return true
        } catch (e: Exception) {
            when (e) {
                is SecurityException -> {}
                is MalformedJwtException -> {}
                is ExpiredJwtException -> {}
                is UnsupportedJwtException -> {}
                is IllegalArgumentException -> {}
                else -> {}
            }
        }
        return false
    }

    private fun getClaims(token: String): Claims =
        Jwts
            .parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body

    fun getJwtContents(bearerToken: String): UUID {
        val token = bearerToken.substring(7)
        val claims = getClaims(token)
        val auth = claims["memberId"] ?: throw RuntimeException("잘못된 토큰입니다.")

        return UUID.fromString(auth.toString())
    }
}

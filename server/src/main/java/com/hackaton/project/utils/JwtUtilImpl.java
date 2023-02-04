package com.hackaton.project.utils;

import com.hackaton.project.dtos.UserAuthDTO;
import com.hackaton.project.dtos.UserDTO;
import com.hackaton.project.exceptions.JWTExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtilImpl {
    private final String PAYLOAD_NAME = "payload";
    private String jwtSecret = "disajidahsuhHDASUUHui31iu31IUGIGIDHIU1JIHDASHUIDUIASHIGHFYUEWGFEWGGFEWGFUEWGYUDHEWUHDUEWUYIGFWYEGFUWEFGWUEGFYUWEGFYUWEUFWBUCWBEYUFGEWYUCGBCEWYUGBUYCEWIURH";
    private long tokenValidityMilliseconds = 1000000000;
    @Autowired
    private ModelMapper modelMapper;

    public String encode(UserAuthDTO userAuthDTO) {
        return Jwts.builder()
                .setSubject(userAuthDTO.getName())
                .claim(PAYLOAD_NAME, userAuthDTO)
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidityMilliseconds))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public UserAuthDTO decode(String jwt) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        Date expirationDate = claims.getExpiration();

        try {
            if (isExpired(expirationDate)) {
                throw new JWTExpiredException();
            }

            return this.modelMapper.map(claims.get(PAYLOAD_NAME), UserAuthDTO.class);
        } catch (JWTExpiredException exception) {
            return null;
        }
    }

    private boolean isExpired(Date tokenDate) {
        return tokenDate.before(new Date());
    }
}

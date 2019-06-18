package com.app.patest;

import com.app.patest.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatestApplicationTests {

    @Test
    public void contextLoads() {
        String username = JWT.decode("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYXRjYXRib3kiLCJyb2xlIjowLCJjcmVhdGVfdGltZSI6MTU2MDgyMDI3MDUwNH0.tke-iDTF6NkVA5LWugA4Mf3zVEMS6hEQ9Sw2UxSTjEs")
                .getAudience().get(0);

        System.out.println(username);
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("cat123456")).build();
        verifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYXRjYXRib3kiLCJyb2xlIjowLCJjcmVhdGVfdGltZSI6MTU2MDgyMDI3MDUwNH0.tke-iDTF6NkVA5LWugA4Mf3zVEMS6hEQ9Sw2UxSTjEs");

    }

}

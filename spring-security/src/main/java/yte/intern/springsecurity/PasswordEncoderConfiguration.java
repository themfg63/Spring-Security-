package yte.intern.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        /*
        zaman içerisinde password encoder algoritmaları değişebilir. Hashing algoritmaları zamanla kırılabilir hale gelebiliyor.
        burada arka tarafta bir hashing algoritması kullanılıyor. Bu algoritma değiştirilebilirdir.
         */
    }
}

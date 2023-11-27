//package MindHub.ecommerce.configurations;
//
//import MindHub.ecommerce.models.Client;
//import MindHub.ecommerce.repositories.ClientRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {
//    @Autowired
//    private ClientRepository clientRepository;
//
//    @Override
//    public void init(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(inputEmail -> {
//            Client client = clientRepository.findByEmail(inputEmail);
//            if (client != null) {
//                if (client.getEmail().contains("velvetAdm")) {
//                    return new User(client.getEmail(), client.getPassword(),
//                            AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN,CLIENT"));
//                } else {
//                    return new User(client.getEmail(), client.getPassword(),
//                            AuthorityUtils.createAuthorityList("CLIENT"));
//                }
//            } else {
//                throw new UsernameNotFoundException("Unknown client" + inputEmail);
//            }
//        });
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//}
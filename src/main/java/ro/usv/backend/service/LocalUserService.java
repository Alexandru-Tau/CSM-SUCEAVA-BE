package ro.usv.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.usv.backend.dto.RegisterDto;
import ro.usv.backend.model.LocalUser;
import ro.usv.backend.repository.LocalUserRepository;

@Service
public class LocalUserService implements UserDetailsService {
    private final LocalUserRepository localUserRepository;

    @Autowired
    public LocalUserService(LocalUserRepository localUserRepository) {
      this.localUserRepository = localUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return localUserRepository.findByEmail(username).orElseThrow();
    }

    public void register(RegisterDto registerDto) {
        LocalUser model = new LocalUser();
        model.setEmail(registerDto.getEmail());
        model.setUsername(registerDto.getUsername());
        model.setPassword(registerDto.getPassword());
        model.setLocalUserRole(registerDto.getLocalUserRole());
       localUserRepository.save(model);

    }


}

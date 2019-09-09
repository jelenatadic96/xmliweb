package com.megatravel.korisniciservice.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.megatravel.korisniciservice.model.Korisnik;
import com.megatravel.korisniciservice.repository.KorisnikRepository;

@Service
public class ImplementedUserDetails implements UserDetailsService {

	@Autowired
	private KorisnikRepository userRepository;

	// izvuce iz baze usere koji nam trebaju i automatski se aktivira za rad sa
	// bazom
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Korisnik user = userRepository.findByMejl(email).get();

		if (user == null) {
			throw new UsernameNotFoundException("User '" + email + "' not found");
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));


		return new org.springframework.security.core.userdetails.User(email, user.getLozinka(), true, true, true, true, grantedAuthorities);
	}

}
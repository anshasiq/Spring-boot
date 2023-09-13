package com.example.auth.Repos;

import com.example.auth.model.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteUrepo extends JpaRepository<SiteUser,Long> {

    SiteUser findByUsername(String username);
}

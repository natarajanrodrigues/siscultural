/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repositories;

import io.github.siscultural.entities.Provider;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public interface ProviderRepository extends JpaRepository<Provider, Long>{
    
    Provider findById(Long id);

    @Query(value = "select * from provider p where p.type=?1", nativeQuery = true)
    public List<Provider> findByType(String type);
    
}

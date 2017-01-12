/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repositories;

import io.github.siscultural.entities.Provider;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public interface ProviderRepository extends JpaRepository<Provider, Long>{

//    private static Pageable pageable = new PageRequest(0, 20, new Sort(new Sort.Order(Sort.Direction.ASC, "name"), new Sort.Order(Sort.Direction.DESC, "name")));

//    static Pageable pageablebyName = new PageRequest(0, 20, new Sort(new Sort.Order(Sort.Direction.ASC, "name")));

    
    Provider findById(Long id);

    @Query(value = "select * from provider p where p.type=?1", nativeQuery = true)
    public List<Provider> findByType(String type);

    @Query(value = "select * from provider p where p.cpf=?1 or p.cnpj=?1", nativeQuery = true)
    public List<Provider> findByCpfOrCNPJ(String cpfOrCnpj);

    Page<Provider> findAllByOrderByNameAsc(Pageable pageable);
    
}

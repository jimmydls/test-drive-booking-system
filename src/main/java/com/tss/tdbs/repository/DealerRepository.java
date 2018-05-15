package com.tss.tdbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tss.tdbs.model.Dealer;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long>{

}

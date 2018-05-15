package com.tss.tdbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tss.tdbs.model.DealerAgent;
import org.springframework.stereotype.Repository;


@Repository
public interface DealerAgentRepository extends JpaRepository<DealerAgent, String> {

}

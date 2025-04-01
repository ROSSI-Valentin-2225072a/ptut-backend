package jfc.isis.dashboard.dao;

import jfc.isis.dashboard.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Integer> {
}

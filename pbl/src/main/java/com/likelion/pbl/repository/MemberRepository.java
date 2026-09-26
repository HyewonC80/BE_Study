package com.likelion.pbl.repository;

import com.likelion.pbl.domain.role.Role;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

	void save(Role member);

	Optional<Role> findByName(String name);

	List<Role> findAll();

	boolean existsByName(String name);

	List<Role> findByPart(String part);

	List<String> getRegisteredParts();

	void updateByName(String name, Role member);

	boolean deleteByName(String name);
}

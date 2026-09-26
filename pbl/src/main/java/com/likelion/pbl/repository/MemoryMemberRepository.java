package com.likelion.pbl.repository;

import com.likelion.pbl.domain.role.Role;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryMemberRepository implements MemberRepository {

	private final List<Role> members = new ArrayList<>();
	private final Map<String, List<Role>> membersByPart = new LinkedHashMap<>();

	@Override
	public void save(Role member) {
		members.add(member);
		membersByPart.computeIfAbsent(member.getPart(), part -> new ArrayList<>()).add(member);
	}

	@Override
	public Optional<Role> findByName(String name) {
		return members.stream()
			.filter(member -> member.getName().equals(name))
			.findFirst();
	}

	@Override
	public List<Role> findAll() {
		return new ArrayList<>(members);
	}

	@Override
	public boolean existsByName(String name) {
		return findByName(name).isPresent();
	}

	@Override
	public List<Role> findByPart(String part) {
		return new ArrayList<>(membersByPart.getOrDefault(part, List.of()));
	}

	@Override
	public List<String> getRegisteredParts() {
		return new ArrayList<>(membersByPart.keySet());
	}

	@Override
	public void updateByName(String name, Role member) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getName().equals(name)) {
				Role old = members.get(i);
				members.set(i, member);

				List<Role> oldPartList = membersByPart.get(old.getPart());
				if (oldPartList != null) {
					oldPartList.removeIf(m -> m.getName().equals(name));
				}
				membersByPart.computeIfAbsent(member.getPart(), part -> new ArrayList<>()).add(member);
				return;
			}
		}
	}

	@Override
	public boolean deleteByName(String name) {
		boolean removed = members.removeIf(member -> member.getName().equals(name));
		membersByPart.values().forEach(list -> list.removeIf(member -> member.getName().equals(name)));
		return removed;
	}
}

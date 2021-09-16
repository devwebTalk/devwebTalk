package com.example.devwebtalk.repository.friend;

import com.example.devwebtalk.entity.FriendsGroup;
import com.example.devwebtalk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FriendsGroupRepository extends JpaRepository<FriendsGroup, Long>, FriendsGroupRepositoryCustom {
	boolean existsByUserAndGroupName(User user, String groupName);
	List<FriendsGroup> findAllByUserAndGroupName(User user, String groupName);
}

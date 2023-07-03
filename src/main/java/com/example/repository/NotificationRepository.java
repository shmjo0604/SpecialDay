package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    
    List<Notification> findTop10ByMember_idOrderByNoDesc(String id);

    @Query(
        value="SELECT * FROM (SELECT n.*, ROW_NUMBER() OVER(ORDER BY n.no DESC) rnum FROM NOTIFICATION n WHERE n.receiver = :id ) n WHERE rnum BETWEEN 1 AND 6 ORDER BY n.rnum ASC ", 
        nativeQuery = true) // :name (nativequery) = #{name} (mybatis) 
    List<Notification> selectByMemberidOrderByNoDescLimit10(@Param("id") String id);

    int countByMember_idAndChk(String id, int chk);

}

package com.example.team1capstone.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {

    @CreationTimestamp
    @Column(updatable = false)  //수정 시 관여 x
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(insertable = false) //입력 시 관여 x
    private LocalDateTime updateTime;
}

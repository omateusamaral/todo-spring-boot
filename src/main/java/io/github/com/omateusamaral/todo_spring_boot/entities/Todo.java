package io.github.com.omateusamaral.todo_spring_boot.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Todo {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private  String todoId;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Boolean completed = false;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private  LocalDateTime updatedAt;

}

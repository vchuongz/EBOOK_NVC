package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @Column(name="created_at")
    private LocalDateTime createAt;

    @Column(name="updated_at")
    private LocalDateTime updateAt;

//    @Column(name = "downloaded_at")
//    private LocalDateTime downloadedAt;

    @PrePersist
    protected void onCreate(){
        createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }

    @PreUpdate
    protected  void onUpdate(){
        updateAt = LocalDateTime.now();
    }
}

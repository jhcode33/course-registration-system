package course_registratio.course_registration_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {
    // JsonFormate, DataFormate 등 AuditingEntityListener과 사용할 시에 적용되지 않는다.

    @CreatedDate
    @Column(name = "created_date")
    private String createdDate;

    @LastModifiedDate
    @Column(name = "modified_date")
    private String modifiedDate;

    @PrePersist //해당 엔티티 저장하기 전
    void onPrePersist(){
        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.modifiedDate = createdDate;
    }

    @PreUpdate //해당 엔티티 수정 하기 전
    void onPreUpdate(){
        this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}

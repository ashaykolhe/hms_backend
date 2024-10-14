package com.kolhe.hms.model.dms;

import com.kolhe.hms.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    private byte[] documentPhoto;
    private String documentName;
    private Boolean isDeleted;
    private Integer fileSize;
    private String fileType;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    @ManyToOne
    @JoinColumn(name = "created_by_user_id", nullable = false, updatable = false)
    private User createdBy;
    @JoinColumn(name = "deleted_by_user_id", updatable = false)
    @ManyToOne
    private User deletedBy;
}

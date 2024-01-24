package org.project.donkey.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes={
        @Index(name="idx_fileinfo_gid", columnList = "gid"),
        @Index(name="idx_fileinfo_gid_location", columnList = "gid,location")
})
public class FileInfo extends BaseMember {
    @Id
    @GeneratedValue
    private Long id;

    @Transient
    private String filePath; // 실 서버 업로드 경로

    @Transient
    private String fileUrl; // 서버 접속 URL

    @Transient
    private String[] thumbsPath; // 썸네일 이미지 경로

    @Transient
    private String[] thumbsUrl; // 썸네일 이미지 접속 URL

    @Column(length=254, nullable = false)
    private String gid;

    @Column(length=254)
    private String location;

    @Column(length=254)
    private String fileName;

    @Column(length=254)
    private String extension;

    @Column(length=254)
    private String fileType;

    private boolean done; // 작업 완료 여부



}

package org.project.donkey.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentData extends Base {

    @Id @GeneratedValue
    private Long seq;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="boardData_seq")
    private BoardData boardData;

    @JoinColumn(name="userNo")
    private Member member;

    @Column(length=50, nullable = false)
    private String poster;

    @Column(length=65)
    private String guestPw;

    @Lob
    @Column(nullable = false)
    private String content;
}

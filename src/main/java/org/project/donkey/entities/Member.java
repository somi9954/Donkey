package org.project.donkey.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.donkey.commons.constants.MemberType;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member extends Base {

    @Id @GeneratedValue
    private Long userNo;

    @Column(length=40, unique = true, nullable = false)
    private String email;

    @Column(length=40, nullable = false)
    private String password;

    @Column(length=40, nullable=false)
    private String userNm;

    @Enumerated(EnumType.STRING)
    @Column(length=15, nullable = false)
    private MemberType mtype = MemberType.USER;

    @Enumerated(EnumType.STRING)
    @Column(length=15, nullable = false)
    private MemberType agree = MemberType.USER;

    // mtype, agree 추후수정
  }


package org.zerock.ex2.entity;

import lombok.*;

import javax.persistence.*;

@Entity /*해당 클래스의 인스턴스들이 JPA로 관리되는 엔티티 객체라는것을 의미*/
@Table(name = "tbl_memo")/* 엔티티 클래스를 어떠한 테이블로 생성할 것인지에 대한 정보 테이블이름 t_memo로 생성*/
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo {

    @Id /*PK에 해당하는 특정 필드를 지정*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)/* PK를 자동으로 생성하고자 할때 사용 (사용자가 입력X 자동으로 생성되는 번호)*/
    /*키 생성 전략
    Auto : JPA 구현체가 생성방식을 결정(부트에서는 하이버네이트)
    IDENTITY : 사용하는 데이터베이스가 키 생성을 결정 MYSQL, MariaDB의 경우 auto increment방식 이용
    SEQUENCE : 데이터베이스의 sequence를 이용해서 키를 생성, @SequenceGenerator와 같이 사용
    TABLE : 키 생성 전용 테이블을 생성해서 키 생성, @TableGenerator와 함께 사용
        */
    private Long mno;

    @Column(length = 200, nullable = false)
    private String memoText;
}

package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex2.entity.Memo;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass() {
        System.out.println(memoRepository.getClass().getName());
    }

    /* 등록 작업 테스트*/
    @Test
    public void testInsertDummies() {
        /* 100개의 새로운 Memo객체 생성후 MemoRepository를 이용해 insert 하기*/
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample..." + i).build();
            memoRepository.save(memo);
        });
    }

    /* 조회 작업 테스트1 - findById() */
    @Test
    public void testSelect() {
        /* 데이터 베이스에 존재하는 mno*/
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno); // Optional 타입으로 반환

        System.out.println("==================================");

        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println("memo = " + memo);
        }
    }

    /* 조회 작업 테스트2 - getOne() */
    @Transactional
    @Test
    public void testSelect2(){
        /* 데이터 베이스에 존재하는 mno */
        Long mno = 100L;

        Memo memo = memoRepository.getOne(mno);

        System.out.println("==========================");

        System.out.println("memo = " + memo);
    }

    /* 수정 작업 테스트 */
    /* 특정 엔티티 객체가 존재하는지 select로 확인하고 객체가 있다면 update, 없으면 insert를 실행하게 된다.*/
    @Test
    public void testUpdate(){
        Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();
        System.out.println(memoRepository.save(memo));
    }

    /* 삭제 작업 테스트*/
    @Test
    public void testDelete(){
        Long mno = 1L;

        memoRepository.deleteById(mno); // 해당 데이터가 존재하지 않으면 EmptyResultDataAccessException 예외 발생시킴 select -> delete 순서로 실행
    }
}

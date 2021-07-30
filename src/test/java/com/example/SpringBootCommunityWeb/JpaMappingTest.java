package com.example.SpringBootCommunityWeb;


import com.example.SpringBootCommunityWeb.domain.Board;
import com.example.SpringBootCommunityWeb.domain.User;
import com.example.SpringBootCommunityWeb.domain.enums.BoardType;
import com.example.SpringBootCommunityWeb.repository.BoardRepository;
import com.example.SpringBootCommunityWeb.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaMappingTest {


    private final String boardTestTitle = "테스트";
    private final String email = "test@aaa.com";

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Before
    public void init(){

        User user = userRepository.save(
                User.builder()
                        .name("havi")
                        .password("test")
                        .email(email)
                        .createdDate(LocalDateTime.now())
                        .build()
        );

        boardRepository.save(

                Board.builder()
                        .title(boardTestTitle)
                        .subTitle("sub Title")
                        .content("content")
                        .boardType(BoardType.free)
                        .createdDate(LocalDateTime.now())
                        .updatedDate(LocalDateTime.now())
                        .user(user)
                        .build()

        );

    }

    @Test
    public void test1(){

        User user = userRepository.findByEmail(email);

        assertThat(user.getName(), is("havi"));
        assertThat(user.getPassword(), is("test"));
        assertThat(user.getEmail(), is(email));

        Board board = boardRepository.findByUser(user);

        assertThat(board.getTitle(), is(boardTestTitle));
        assertThat(board.getSubTitle(), is("sub Title"));
        assertThat(board.getContent(), is("content"));
        assertThat(board.getBoardType(), is(BoardType.free));

    }


}

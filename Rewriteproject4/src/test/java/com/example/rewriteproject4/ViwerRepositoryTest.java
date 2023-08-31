package com.example.rewriteproject4;

import com.example.rewriteproject4.DTO.ViwerDTO;
import com.example.rewriteproject4.Model.User;
import com.example.rewriteproject4.Model.Viwer;
import com.example.rewriteproject4.Repository.AuthRepository;
import com.example.rewriteproject4.Repository.ViwerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ViwerRepositoryTest {
    @Autowired
    ViwerRepository viwerRepository;

   User user1;
   User user2;
    ViwerDTO viwerDTO;
    ViwerDTO viwerDTO2;

    List<Viwer>viwerList;

Viwer viwers;
    @BeforeEach
    void setUp() {
        user1=new User(1,"sh","Sh231","USER",null);
        user2=new User(2,"mh","Mh231","USER",null);
        viwerDTO=new ViwerDTO(3,"manayer",22,2000,1);
        viwerDTO2=new ViwerDTO(2,"Reem",22,1500,2);
        Viwer viwer11=new Viwer(viwerDTO.getUser_id(),viwerDTO.getName(),viwerDTO.getAge(),viwerDTO.getBalance(),viwerDTO.getAmountoftickets(),user1,null,null);
        Viwer viwer22=new Viwer(viwerDTO2.getUser_id(),viwerDTO2.getName(),viwerDTO2.getAge(),viwerDTO2.getBalance(),viwerDTO2.getAmountoftickets(),user2,null,null);
        viwerRepository.save(viwer11);
        viwerRepository.save(viwer22);
    }

    @Test
    public void findViwerById(){
      viwers=viwerRepository.findViewerById(viwerDTO.getUser_id());
        Assertions.assertThat(viwers.getId()).isEqualTo(viwerDTO.getUser_id());

    }

    @Test
    public void VipViwersTest(){
        viwerList=viwerRepository.vipviwers();
        Assertions.assertThat(viwerList.get(0).getBalance()).isGreaterThan(1000);

    }
}

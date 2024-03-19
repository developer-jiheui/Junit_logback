package com.gdu.prj05;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gdu.prj05.dao.ContactDao;
import com.gdu.prj05.dto.ContactDto;

import java.util.List;

/* 1. JUnit4 를 이용한다. */
@RunWith(SpringJUnit4ClassRunner.class)

/*
 * 2. ContactDaoImpl 클래스의 bean 생성 방법을 작성한다.
 *  1) <bean>      locations="file:src/main/webapp/WEB-INF/spring/root-context.xml"
 *  2) @Bean       classes=AppConfig.class
 *  3) @Component  locations="file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
 *
 * Dao를 bean으로 만들고 root-context.xml으로 바꿔줌
 */
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})

/* 3. 메소드의 이름순으로 테스트를 수행한다.
*/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ContactUnitTest {

  private ContactDao contactDao;

  @Autowired
  public void setContactDao(ContactDao contactDao) {
    this.contactDao = contactDao;
  }

  @Test
  public void test01_등록테스트() {

    ContactDto contact = ContactDto.builder()
            .name("테스트이름")
            .mobile("테스트모바일")
            .email("테스트이메일")
            .address("테스트주소")
            .contactNo(0).build();
    int insertCount = contactDao.registerContact(contact);
    assertEquals(1, insertCount);

  }

  @Test
  public void test02_조회테스트(){
     int contactNO = 1;

     ContactDto contact = contactDao.getContactByNo(contactNO);

     assertNotNull(contact);

  }

  @Test
  public void test03_수정테스트(){

  ContactDto contact = ContactDto.builder()
            .name("[수정]테스트이름")
            .mobile("[수정]모바일")
            .email("[수정]테스트이메일")
            .address("[수정]테스트주소")
            .contactNo(1)
            .build();
    int updateCount = contactDao.modifyContact(contact);
    assertEquals(1, updateCount);

  }

  @Test
  public void test04_목록(){
    List<ContactDto> contactList = contactDao.getContactList();

    //contactList에 들어잇는 리스트 개수가 한개이길 기대한다
    assertEquals(1,contactList.size());

  }

  @Test
  public void test05_삭제(){

    int contactNo = 1;
    int deleteCount = contactDao.removeContact(contactNo);

    assertEquals(1,deleteCount);

  }
}

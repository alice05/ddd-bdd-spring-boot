package com.santoshkc.leaser.unit.infratstructure.database.freelancer;

import com.santoshkc.leaser.infrastructure.database.freelancer.FreelancerDAO;
import com.santoshkc.leaser.infrastructure.database.freelancer.FreelancerProjection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.PersistenceException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class) // provides bridge between springboot test features and junit
@ContextConfiguration
@DataJpaTest
public class FreelancerProjectionTest {
    @Autowired
    private FreelancerDAO dao;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void whenSave_thenAutoGenerateTheId() {
        //given
        FreelancerProjection projection = new FreelancerProjection();
        projection.setFirstName("test firstname");
        projection.setLastName("test lastname");
        projection.setCity("test city");
        projection.setZipCode("test zipcode");


        // when
        FreelancerProjection entity = dao.save(projection);

        //then
        assertThat(entity.getId()).isNotNull();

    }

    @Test(expected = PersistenceException.class) // then
    public void whenSave_ShouldNotAllowBlankFirstName() {
        //given
        FreelancerProjection projection = new FreelancerProjection();
        projection.setFirstName(null);
        projection.setLastName("test lastname");
        projection.setCity("test city");
        projection.setZipCode("test zipcode");

        //when
        entityManager.persist(projection);
        entityManager.flush();
    }

}
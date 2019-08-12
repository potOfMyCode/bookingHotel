package dao;

import model.dao.ApplicationDao;
import model.dao.DaoFactory;
import model.dao.exception.DaoException;
import model.entity.Application;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertNotNull;

public class JDBCApplicationDaoTest {
    private ApplicationDao factory;

    @Before
    public void init(){
        factory = DaoFactory.getInstance().createApplicationDao();
    }

    @After
    public void after() {
        factory.close();
    }

    @Test
    public void getEntityByIdTest() throws DaoException {
        List<Application> applications = factory.getAll();
        int id = -1;
        for(Application a : applications){
            id = a.getId();
            assertNotNull(factory.getEntityById(id));
        }
    }

}

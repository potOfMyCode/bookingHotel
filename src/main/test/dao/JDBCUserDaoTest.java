package dao;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dao.exception.DaoException;
import model.dao.exception.NoSuchUserException;
import model.entity.User;
import model.util.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class JDBCUserDaoTest {
    private UserDao factory;

    @Before
    public void init(){
        factory = DaoFactory.getInstance().createUserDao();
    }

    @After
    public void after() {
        factory.close();
    }

    @Test
    public void createTest() throws DaoException, NoSuchUserException {
        User user = new User();
        user.setUsername("Misha");
        user.setRole(Role.USER);

        user.setEmail("misha@gmail.com");
        user.setPassword("password");

        factory.create(user);

        assertNotNull(factory.getEntityByUsername("Misha"));
    }

    @Test(expected = DaoException.class)
    public void createWithExceptionTest() throws DaoException {
        User user = new User();
        user.setUsername("Misha");
        user.setRole(Role.USER);

        user.setEmail("misha@gmail.com");
        user.setPassword("password");
        factory.create(user);
    }

    @Test
    public void getEntityByUserNameTest() throws  NoSuchUserException {
        User user = new User();
        user.setUsername("dima");
        user.setRole(Role.USER);

        user.setEmail("dimas@gmail.com");
        user.setPassword("password");
        user.setId(2);
        assertNotNull(factory.getEntityByUsername("dima"));
    }

    @Test
    public void getAllTest()  {
        assertNotNull(factory.getAll());
    }

    @Test
    public void getEntityByEmailNameTest() throws  NoSuchUserException {
        assertNotNull(factory.getEntityByEmail("dimas@gmail.com"));
    }

    @Test(expected = NoSuchUserException.class)
    public void getEntityByUserNameWithExceptionTest() throws  NoSuchUserException {
        factory.getEntityByUsername("dog");
    }

    @Test(expected = NoSuchUserException.class)
    public void getEntityByEmailWithExceptionTest() throws  NoSuchUserException {
        assertNull(factory.getEntityByEmail("kot@gmail.com"));
    }



}

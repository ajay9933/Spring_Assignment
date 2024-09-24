package hibernate_mvc.dao;



import hibernate_mvc.entity.Author;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AuthorDAOImpl implements AuthorDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void saveAuthor(Author author) {
        sessionFactory.getCurrentSession().saveOrUpdate(author);
    }

    @Override
    @Transactional
    public List<Author> getAllAuthors() {
        return sessionFactory.getCurrentSession().createQuery("from Author", Author.class).list();
    }

    @Override
    @Transactional
    public Author getAuthorById(Long id) {
        return sessionFactory.getCurrentSession().get(Author.class, id);
    }

    @Override
    @Transactional
    public void updateAuthor(Author author) {
        sessionFactory.getCurrentSession().update(author);
    }

    @Override
    @Transactional
    public void deleteAuthor(Long id) {
        Author author = sessionFactory.getCurrentSession().get(Author.class, id);
        if (author != null) {
            sessionFactory.getCurrentSession().delete(author);
        }
    }
}


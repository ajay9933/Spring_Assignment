package hibernate_mvc.dao;

import hibernate_mvc.entity.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void saveBook(Book book) {
        sessionFactory.getCurrentSession().saveOrUpdate(book);
    }

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return sessionFactory.getCurrentSession().createQuery("from Book", Book.class).list();
    }

    @Override
    @Transactional
    public Book getBookById(Long id) {
        return sessionFactory.getCurrentSession().get(Book.class, id);
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        sessionFactory.getCurrentSession().update(book);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        Book book = sessionFactory.getCurrentSession().get(Book.class, id);
        if (book != null) {
            sessionFactory.getCurrentSession().delete(book);
        }
    }
}

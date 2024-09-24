package hibernate_mvc.dao;


import hibernate_mvc.entity.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void saveCategory(Category category) {
        sessionFactory.getCurrentSession().saveOrUpdate(category);
    }

    @Override
    @Transactional
    public List<Category> getAllCategories() {
        return sessionFactory.getCurrentSession().createQuery("from Category", Category.class).list();
    }

    @Override
    @Transactional
    public Category getCategoryById(Long id) {
        return sessionFactory.getCurrentSession().get(Category.class, id);
    }

    @Override
    @Transactional
    public void updateCategory(Category category) {
        sessionFactory.getCurrentSession().update(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category category = sessionFactory.getCurrentSession().get(Category.class, id);
        if (category != null) {
            sessionFactory.getCurrentSession().delete(category);
        }
    }
}


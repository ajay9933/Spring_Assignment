package hibernate_mvc.service;



import hibernate_mvc.dao.CategoryDAO;
import hibernate_mvc.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    @Transactional
    public void saveCategory(Category category) {
        categoryDAO.saveCategory(category);
    }

    @Override
    @Transactional
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    @Override
    @Transactional
    public Category getCategoryById(Long id) {
        return categoryDAO.getCategoryById(id);
    }

    @Override
    @Transactional
    public void updateCategory(Category category) {
        categoryDAO.updateCategory(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        categoryDAO.deleteCategory(id);
    }
}

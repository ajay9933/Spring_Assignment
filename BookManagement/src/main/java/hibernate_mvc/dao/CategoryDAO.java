package hibernate_mvc.dao;



import hibernate_mvc.entity.Category;
import java.util.List;

public interface CategoryDAO {
    void saveCategory(Category category);
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    void updateCategory(Category category);
    void deleteCategory(Long id);
}

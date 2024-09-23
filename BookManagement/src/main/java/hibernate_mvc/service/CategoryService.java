package hibernate_mvc.service;


import hibernate_mvc.entity.Category;
import java.util.List;

public interface CategoryService {
    void saveCategory(Category category);
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    void updateCategory(Category category);
    void deleteCategory(Long id);
}

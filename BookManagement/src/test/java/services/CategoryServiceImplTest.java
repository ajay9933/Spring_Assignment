package services;

import hibernate_mvc.dao.CategoryDAO;
import hibernate_mvc.entity.Category;
import hibernate_mvc.service.CategoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryDAO categoryDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCategory() {
        Category category = new Category();
        categoryService.saveCategory(category);
        verify(categoryDAO, times(1)).saveCategory(category);
    }

    @Test
    public void testGetAllCategories() {
        List<Category> categories = Arrays.asList(new Category(), new Category());
        when(categoryDAO.getAllCategories()).thenReturn(categories);
        List<Category> result = categoryService.getAllCategories();
        assertEquals(2, result.size());
        verify(categoryDAO, times(1)).getAllCategories();
    }

    @Test
    public void testGetCategoryById() {
        Category category = new Category();
        when(categoryDAO.getCategoryById(1L)).thenReturn(category);
        Category result = categoryService.getCategoryById(1L);
        assertEquals(category, result);
        verify(categoryDAO, times(1)).getCategoryById(1L);
    }

    @Test
    public void testUpdateCategory() {
        Category category = new Category();
        categoryService.updateCategory(category);
        verify(categoryDAO, times(1)).updateCategory(category);
    }

    @Test
    public void testDeleteCategory() {
        categoryService.deleteCategory(1L);
        verify(categoryDAO, times(1)).deleteCategory(1L);
    }
}

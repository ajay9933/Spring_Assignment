package controllers;

import hibernate_mvc.controller.CategoryController;
import hibernate_mvc.entity.Category;
import hibernate_mvc.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void testInitBinder() {
        WebDataBinder binder = mock(WebDataBinder.class);
        categoryController.initBinder(binder);
        verify(binder, times(1)).registerCustomEditor(eq(String.class), any(StringTrimmerEditor.class));
    }


    @Test
    public void testListCategories() {
        when(categoryService.getAllCategories()).thenReturn(Arrays.asList(new Category()));
        String view = categoryController.listCategories(model);
        assertEquals("category", view);
        verify(categoryService, times(1)).getAllCategories();
        verify(model, times(1)).addAttribute(eq("categories"), anyList());
    }

    @Test
    public void testShowFormForAdd() {
        String view = categoryController.showFormForAdd(model);
        assertEquals("category-form", view);
        verify(model, times(1)).addAttribute(eq("category"), any(Category.class));
    }

    @Test
    public void testSaveCategory_WithErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);
        Category category = new Category();
        String view = categoryController.saveCategory(category, bindingResult);
        assertEquals("category-form", view);
        verify(categoryService, never()).saveCategory(any(Category.class));
    }

    @Test
    public void testSaveCategory_NoErrors() {
        when(bindingResult.hasErrors()).thenReturn(false);
        Category category = new Category();
        String view = categoryController.saveCategory(category, bindingResult);
        assertEquals("redirect:/categories/list", view);
        verify(categoryService, times(1)).saveCategory(category);
    }

    @Test
    public void testShowFormForUpdate() {
        Category category = new Category();
        when(categoryService.getCategoryById(1L)).thenReturn(category);
        String view = categoryController.showFormForUpdate(1L, model);
        assertEquals("category-form", view);
        verify(categoryService, times(1)).getCategoryById(1L);
        verify(model, times(1)).addAttribute(eq("category"), eq(category));
    }

    @Test
    public void testDeleteCategory() {
        String view = categoryController.deleteCategory(1L);
        assertEquals("redirect:/categories/list", view);
        verify(categoryService, times(1)).deleteCategory(1L);
    }
}

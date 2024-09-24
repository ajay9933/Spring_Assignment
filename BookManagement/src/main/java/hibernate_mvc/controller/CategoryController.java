package hibernate_mvc.controller;

import hibernate_mvc.entity.Category;
import hibernate_mvc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {


    private final CategoryService categoryService;

    private static final String CATEGORY_VIEW = "category";
    private static final String CATEGORY_FORM_VIEW = "category-form";
    private static final String CATEGORY_ATTRIBUTE = "category";
    private static final String CATEGORIES = "categories";


    @Autowired
    public CategoryController(CategoryService categoryService) {

        this.categoryService=categoryService;
    }
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder)
    {
        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }
    @GetMapping("/list")
    public String listCategories(Model model) {
        model.addAttribute(CATEGORIES, categoryService.getAllCategories());
        return CATEGORY_VIEW;
    }

    @GetMapping("/add")
    public String showFormForAdd(Model model) {
        model.addAttribute(CATEGORY_ATTRIBUTE, new Category());
        return CATEGORY_FORM_VIEW;
    }


    @PostMapping("/save")
    public String saveCategory(@Valid @ModelAttribute(CATEGORY_ATTRIBUTE) Category category, BindingResult theBindingResult) {

        if (theBindingResult.hasErrors()) {
            return CATEGORY_FORM_VIEW;
        }

        categoryService.saveCategory(category);
        return "redirect:/categories/list";
    }

    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute(CATEGORY_ATTRIBUTE, category);
        return CATEGORY_FORM_VIEW;
    }

    @DeleteMapping("/delete")
    public String deleteCategory(@RequestParam("id") Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories/list";
    }
}


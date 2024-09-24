package hibernate_mvc.controller;


import hibernate_mvc.entity.Book;
import hibernate_mvc.entity.Category;
import hibernate_mvc.service.BookService;
import hibernate_mvc.service.CategoryService;
import hibernate_mvc.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/books")
public class BookController {


    private final BookService bookService;

    private final AuthorService authorService;

    private final CategoryService categoryService;



    private static final String bookView="book";

    private static final String bookformView="book-form";

    private static final String bookAttribute="book";

    private static final String books="books";

    private static final  String authors="authors";

    private static final  String categories="categories";


    @Autowired
    public BookController(AuthorService authorService,BookService bookService,CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService=bookService;
        this.categoryService=categoryService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder)
    {
        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }


    @GetMapping("/list")
    public String listBooks(Model model) {
        model.addAttribute(books, bookService.getAllBooks());
        return bookView;
    }

    @GetMapping("/add")
    public String showFormForAdd(Model model) {
        model.addAttribute(bookAttribute, new Book());
        model.addAttribute(authors, authorService.getAllAuthors());
        model.addAttribute(categories, categoryService.getAllCategories());
        return bookformView;
    }


    @PostMapping("/save")
    public String saveBook(@Valid @ModelAttribute(bookAttribute) Book book, BindingResult theBindingResult,
                           @RequestParam(value = "categoryIds", required = false) Long[] categoryIds,
                           Model model) {
        if (theBindingResult.hasErrors()) {
            model.addAttribute(authors, authorService.getAllAuthors());
            model.addAttribute(categories, categoryService.getAllCategories());
            return bookformView;
        }


        Set<Category> categories = new HashSet<>();
        if (categoryIds != null) {
            for (Long categoryId : categoryIds) {
                categories.add(categoryService.getCategoryById(categoryId));
            }
        }
        book.setCategories(categories);

        bookService.saveBook(book);
        return "redirect:/books/list";
    }

    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("id") Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute(bookAttribute, book);
        model.addAttribute(authors, authorService.getAllAuthors());
        model.addAttribute(categories, categoryService.getAllCategories());
        return bookformView;
    }

    @DeleteMapping ("/delete")
    public String deleteBook(@RequestParam("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/books/list";
    }
}

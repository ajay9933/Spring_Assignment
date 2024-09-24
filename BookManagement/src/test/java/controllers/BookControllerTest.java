package controllers;

import hibernate_mvc.controller.BookController;
import hibernate_mvc.entity.Book;
import hibernate_mvc.entity.Category;
import hibernate_mvc.entity.Author;
import hibernate_mvc.service.BookService;
import hibernate_mvc.service.AuthorService;
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
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;



public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Mock
    private AuthorService authorService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInitBinder() {
        WebDataBinder binder = mock(WebDataBinder.class);
        bookController.initBinder(binder);
        verify(binder, times(1)).registerCustomEditor(eq(String.class), any(StringTrimmerEditor.class));
    }
    @Test
    public void testListBooks() {
        List<Book> books = Arrays.asList(new Book());
        when(bookService.getAllBooks()).thenReturn(books);

        String view = bookController.listBooks(model);

        assertEquals("book", view);
        verify(bookService, times(1)).getAllBooks();
        verify(model, times(1)).addAttribute("books", books);
    }

    @Test
    public void testShowFormForAdd() {
        when(authorService.getAllAuthors()).thenReturn(Arrays.asList(new Author()));
        when(categoryService.getAllCategories()).thenReturn(Arrays.asList(new Category()));

        String view = bookController.showFormForAdd(model);

        assertEquals("book-form", view);
        verify(model, times(1)).addAttribute(eq("book"), any(Book.class));
        verify(model, times(1)).addAttribute("authors", authorService.getAllAuthors());
        verify(model, times(1)).addAttribute("categories", categoryService.getAllCategories());
    }

    @Test
    public void testSaveBook_Valid_NoCategories() {
        when(bindingResult.hasErrors()).thenReturn(false);

        Book book = new Book();
        Long[] categoryIds = null;

        String view = bookController.saveBook(book, bindingResult, categoryIds, model);

        assertEquals("redirect:/books/list", view);
        verify(bookService, times(1)).saveBook(book);
        assertEquals(0, book.getCategories().size());
    }

    @Test
    public void testSaveBook_WithValidationErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);
        when(authorService.getAllAuthors()).thenReturn(Arrays.asList(new Author()));
        when(categoryService.getAllCategories()).thenReturn(Arrays.asList(new Category()));

        Book book = new Book();
        String view = bookController.saveBook(book, bindingResult, null, model);

        assertEquals("book-form", view);
        verify(bookService, never()).saveBook(book);
        verify(model, times(1)).addAttribute("authors", authorService.getAllAuthors());
        verify(model, times(1)).addAttribute("categories", categoryService.getAllCategories());
    }

    @Test
    public void testSaveBook_Valid() {
        when(bindingResult.hasErrors()).thenReturn(false);

        Book book = new Book();
        Long[] categoryIds = {1L, 2L};


        when(categoryService.getCategoryById(1L)).thenReturn(new Category());
        when(categoryService.getCategoryById(2L)).thenReturn(new Category());

        String view = bookController.saveBook(book, bindingResult, categoryIds, model);

        assertEquals("redirect:/books/list", view);
        verify(bookService, times(1)).saveBook(book);
    }



    @Test
    public void testShowFormForUpdate() {
        Book book = new Book();
        when(bookService.getBookById(1L)).thenReturn(book);
        when(authorService.getAllAuthors()).thenReturn(Arrays.asList(new Author()));
        when(categoryService.getAllCategories()).thenReturn(Arrays.asList(new Category()));

        String view = bookController.showFormForUpdate(1L, model);

        assertEquals("book-form", view);
        verify(bookService, times(1)).getBookById(1L);
        verify(model, times(1)).addAttribute("book", book);
        verify(model, times(1)).addAttribute("authors", authorService.getAllAuthors());
        verify(model, times(1)).addAttribute("categories", categoryService.getAllCategories());
    }

    @Test
    public void testDeleteBook() {
        String view = bookController.deleteBook(1L);

        assertEquals("redirect:/books/list", view);
        verify(bookService, times(1)).deleteBook(1L);
    }

    @Test
    public void testSaveBook_Valid_WithCategories() {
        when(bindingResult.hasErrors()).thenReturn(false);

        Book book = new Book();
        Long[] categoryIds = {1L, 2L};
        Category category1 = new Category();
        Category category2 = new Category();

        when(categoryService.getCategoryById(1L)).thenReturn(category1);
        when(categoryService.getCategoryById(2L)).thenReturn(category2);

        String view = bookController.saveBook(book, bindingResult, categoryIds, model);

        assertEquals("redirect:/books/list", view);
        verify(bookService, times(1)).saveBook(book);
        assertEquals(2, book.getCategories().size());
    }

    @Test
    public void testSaveBookWithValidationErrors() {
        Book invalidBook = new Book();
        invalidBook.setTitle("Sh");
        invalidBook.setIsbn("123456789012");

        when(bindingResult.hasErrors()).thenReturn(true);

        String view = bookController.saveBook(invalidBook, bindingResult, new Long[]{1L}, model);

        assertEquals("book-form", view);
        verify(bookService, never()).saveBook(any(Book.class));
        verify(model, times(1)).addAttribute(eq("authors"), anyList());
        verify(model, times(1)).addAttribute(eq("categories"), anyList());
    }

    @Test
    public void testSaveBookWithoutCategories() {
        Book validBook = new Book();
        validBook.setTitle("Valid Book Title");
        validBook.setIsbn("1234567890123");

        when(bindingResult.hasErrors()).thenReturn(false);

        String view = bookController.saveBook(validBook, bindingResult, null, model);

        assertEquals("redirect:/books/list", view);
        verify(bookService, times(1)).saveBook(validBook);
    }





}

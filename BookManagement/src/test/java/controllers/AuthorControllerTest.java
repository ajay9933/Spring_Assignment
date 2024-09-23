package controllers;

import hibernate_mvc.controller.AuthorController;
import hibernate_mvc.entity.Author;
import hibernate_mvc.service.AuthorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AuthorControllerTest {

    @InjectMocks
    private AuthorController authorController;

    @Mock
    private AuthorService authorService;

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
        authorController.initBinder(binder);
        verify(binder, times(1)).registerCustomEditor(eq(String.class), any(StringTrimmerEditor.class));
    }

    @Test
    public void testListAuthors() {
        String view = authorController.listAuthors(model);
        assertEquals("author", view);
        verify(model, times(1)).addAttribute(eq("authors"), anyList());
    }


    @Test
    public void testShowFormForAdd() {
        String view = authorController.showFormForAdd(model);
        assertEquals("author-form", view);
        verify(model, times(1)).addAttribute(eq("author"), any(Author.class));
    }


    @Test
    public void testSaveAuthorWithValidationErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);

        Author author = new Author();
        String view = authorController.saveAuthor(author, bindingResult);

        assertEquals("author-form", view);
        verify(authorService, never()).saveAuthor(any(Author.class));
    }


    @Test
    public void testSaveAuthorWithValidData() {
        when(bindingResult.hasErrors()).thenReturn(false);

        Author author = new Author();
        String view = authorController.saveAuthor(author, bindingResult);

        assertEquals("redirect:/authors/list", view);
        verify(authorService, times(1)).saveAuthor(author);
    }


    @Test
    public void testShowUpdateWithExistingAuthor() {
        Author author = new Author();
        author.setId(1L);
        when(authorService.getAuthorById(1L)).thenReturn(author);

        String view = authorController.showUpdate(1L, model);

        assertEquals("author-form", view);
        verify(authorService, times(1)).getAuthorById(1L);
        verify(model, times(1)).addAttribute("author", author);
    }

    @Test
    public void testDeleteAuthor() {
        String view = authorController.deleteAuthor(1L);

        assertEquals("redirect:/authors/list", view);
        verify(authorService, times(1)).deleteAuthor(1L);
    }


}

package services;

import hibernate_mvc.dao.AuthorDAO;
import hibernate_mvc.entity.Author;
import hibernate_mvc.service.AuthorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AuthorServiceImplTest {

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Mock
    private AuthorDAO authorDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testSaveAuthor() {
        Author author = new Author();
        authorService.saveAuthor(author);
        verify(authorDAO, times(1)).saveAuthor(author);
    }


    @Test
    public void testGetAllAuthors() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author());

        when(authorDAO.getAllAuthors()).thenReturn(authors);

        List<Author> result = authorService.getAllAuthors();
        assertEquals(1, result.size());
        verify(authorDAO, times(1)).getAllAuthors();
    }

    @Test
    public void testGetAuthorById() {
        Author author = new Author();
        author.setId(1L);

        when(authorDAO.getAuthorById(1L)).thenReturn(author);

        Author result = authorService.getAuthorById(1L);
        assertEquals(1L, result.getId().longValue());
        verify(authorDAO, times(1)).getAuthorById(1L);
    }


    @Test
    public void testUpdateAuthor() {
        Author author = new Author();
        authorService.updateAuthor(author);
        verify(authorDAO, times(1)).updateAuthor(author);
    }


    @Test
    public void testDeleteAuthor() {
        Long authorId = 1L;
        authorService.deleteAuthor(authorId);
        verify(authorDAO, times(1)).deleteAuthor(authorId);
    }
}

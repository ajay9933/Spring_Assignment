package services;

import hibernate_mvc.dao.BookDAO;
import hibernate_mvc.entity.Book;
import hibernate_mvc.service.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookDAO bookDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveBook() {
        Book book = new Book();
        bookService.saveBook(book);
        verify(bookDAO, times(1)).saveBook(book);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> books = Arrays.asList(new Book());
        when(bookDAO.getAllBooks()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();
        assertEquals(books, result);
        verify(bookDAO, times(1)).getAllBooks();
    }

    @Test
    public void testGetBookById() {
        Book book = new Book();
        when(bookDAO.getBookById(1L)).thenReturn(book);

        Book result = bookService.getBookById(1L);
        assertEquals(book, result);
        verify(bookDAO, times(1)).getBookById(1L);
    }

    @Test
    public void testUpdateBook() {
        Book book = new Book();
        bookService.updateBook(book);
        verify(bookDAO, times(1)).updateBook(book);
    }

    @Test
    public void testDeleteBook() {
        bookService.deleteBook(1L);
        verify(bookDAO, times(1)).deleteBook(1L);
    }
}

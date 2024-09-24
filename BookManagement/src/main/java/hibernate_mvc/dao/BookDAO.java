
package hibernate_mvc.dao;

import hibernate_mvc.entity.Book;
import java.util.List;

public interface BookDAO {
    void saveBook(Book book);
    List<Book> getAllBooks();
    Book getBookById(Long id);
    void updateBook(Book book);
    void deleteBook(Long id);
}

package hibernate_mvc.dao;



import hibernate_mvc.entity.Author;
import java.util.List;

public interface AuthorDAO {
    void saveAuthor(Author author);
    List<Author> getAllAuthors();
    Author getAuthorById(Long id);
    void updateAuthor(Author author);
    void deleteAuthor(Long id);
}


package hibernate_mvc.service;

import hibernate_mvc.dao.AuthorDAO;
import hibernate_mvc.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorDAO authorDAO;

    @Override
    @Transactional
    public void saveAuthor(Author author) {
        authorDAO.saveAuthor(author);
    }

    @Override
    @Transactional
    public List<Author> getAllAuthors() {
        return authorDAO.getAllAuthors();
    }

    @Override
    @Transactional
    public Author getAuthorById(Long id) {
        return authorDAO.getAuthorById(id);
    }

    @Override
    @Transactional
    public void updateAuthor(Author author) {
        authorDAO.updateAuthor(author);
    }

    @Override
    @Transactional
    public void deleteAuthor(Long id) {
        authorDAO.deleteAuthor(id);
    }
}


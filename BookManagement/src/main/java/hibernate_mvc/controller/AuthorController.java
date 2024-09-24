package hibernate_mvc.controller;


import hibernate_mvc.entity.Author;
import hibernate_mvc.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@Controller
@RequestMapping("/authors")

public class AuthorController {


    private final String authorView="author";

    private final String authorformView="author-form";

   private final String AUTHOR_ATTRIBUTE="author";

   private final String AUTHORS="authors";

    private final AuthorService authorService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder)
    {
        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }


    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/list")
    public String listAuthors(Model model) {
        model.addAttribute(AUTHORS, authorService.getAllAuthors());

        return authorView;
    }

    @GetMapping("/add")
    public String showFormForAdd(Model model) {
        model.addAttribute(AUTHOR_ATTRIBUTE, new Author());
        return authorformView;
    }


    @PostMapping("/save")
    public String saveAuthor(@Valid @ModelAttribute("author") Author author, BindingResult theBindingResult) {


        if (theBindingResult.hasErrors()) {

            return authorformView;
        }
        authorService.saveAuthor(author);
        return "redirect:/authors/list";
    }


    @GetMapping("/update")
    public String showUpdate(@RequestParam("id") Long id,Model model)
    {

        Author author = authorService.getAuthorById(id);
        model.addAttribute(AUTHOR_ATTRIBUTE, author);
        return authorformView;
    }


    @DeleteMapping("/delete")
    public String deleteAuthor(@RequestParam("id") Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/authors/list";
    }

}



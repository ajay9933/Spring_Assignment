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


    @InitBinder
    public void initBinder(WebDataBinder webDataBinder)
    {
        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }


    @Autowired
    private AuthorService authorService;

    @GetMapping("/list")
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "author";
    }

    @GetMapping("/add")
    public String showFormForAdd(Model model) {
        model.addAttribute("author", new Author());
        return "author-form";
    }


    @PostMapping("/save")
    public String saveAuthor(@Valid @ModelAttribute("author") Author author, BindingResult theBindingResult) {

        System.out.println("hello");
        if (theBindingResult.hasErrors()) {

            return "author-form";
        }
        authorService.saveAuthor(author);
        return "redirect:/authors/list";
    }


    @GetMapping("/update")
    public String showUpdate(@RequestParam("id") Long id,Model model)
    {

        Author author = authorService.getAuthorById(id);
        model.addAttribute("author", author);
        return "author-form";
    }


    @DeleteMapping("/delete")
    public String deleteAuthor(@RequestParam("id") Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/authors/list";
    }

}



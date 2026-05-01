package com.library.controller;

import com.library.dao.BookDAO;
import com.library.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    private BookDAO bookDAO = new BookDAO();

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("books", bookDAO.getAllBooks());
        return "index";
    }

    @PostMapping("/add")
    public String addBook(@RequestParam String title,
            @RequestParam String author,
            @RequestParam int quantity) {
        Book book = new Book(0, title, author, quantity, true);
        bookDAO.addBook(book);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        bookDAO.deleteBook(id);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String searchBook(@RequestParam String keyword, Model model) {
        model.addAttribute("books", bookDAO.searchBook(keyword));
        return "index";
    }
}
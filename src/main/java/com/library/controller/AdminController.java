package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookService bookService;

    // Dashboard
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("books", bookService.getAllBooks());

        return "admin-dashboard";
    }

    // Add Book
    @PostMapping("/add-book")
    public String addBook(
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam double price) {

        Book book = new Book();

        book.setTitle(title);
        book.setAuthor(author);
        book.setPrice(price);

        bookService.saveBook(book);

        return "redirect:/admin/dashboard";
    }

    // Delete Book
    @GetMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable Long id) {

        bookService.deleteBook(id);

        return "redirect:/admin/dashboard";
    }

    // Edit Book Page
    @GetMapping("/edit-book/{id}")
    public String editBook(@PathVariable Long id, Model model) {

        Book book = bookService.getBookById(id);

        model.addAttribute("book", book);

        return "edit-book";
    }

    // Update Book
    @PostMapping("/update-book")
    public String updateBook(
            @RequestParam Long id,
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam double price) {

        Book book = new Book();

        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPrice(price);

        bookService.saveBook(book);

        return "redirect:/admin/dashboard";
    }
}
package com.library.controller;

import com.library.model.Book;
import com.library.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // VIEW BOOKS
    @GetMapping
    public String viewBooks(Model model) {

        model.addAttribute("books", bookRepository.findAll());

        return "books";
    }

    // ADD BOOK PAGE
    @GetMapping("/add")
    public String addBookPage(Model model) {

        model.addAttribute("book", new Book());

        return "add-book";
    }

    // SAVE BOOK
    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book) {

        bookRepository.save(book);

        return "redirect:/books";
    }

    // EDIT BOOK PAGE
    @GetMapping("/edit/{id}")
    public String editBook(
            @PathVariable Long id,
            Model model) {

        Book book = bookRepository.findById(id).orElse(null);

        model.addAttribute("book", book);

        return "edit-book";
    }

    // UPDATE BOOK
    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book book) {

        bookRepository.save(book);

        return "redirect:/books";
    }

    // DELETE BOOK
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {

        bookRepository.deleteById(id);

        return "redirect:/books";
    }

    // SEARCH BOOK
    @GetMapping("/search")
    public String searchBook(
            @RequestParam String keyword,
            Model model) {

        model.addAttribute(
                "books",
                bookRepository.findByTitleContainingIgnoreCase(keyword));

        return "books";
    }
}
package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private BookService bookService;

    // Student Dashboard
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("books", bookService.getAllBooks());

        return "student-dashboard";
    }

    // Search Books
    @GetMapping("/search")
    public String searchBooks(
            @RequestParam("keyword") String keyword,
            Model model) {

        model.addAttribute("books", bookService.getAllBooks());

        return "student-dashboard";
    }
}
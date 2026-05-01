package com.library.dao;

import com.library.model.Book;
import com.library.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    Connection conn = DBConnection.getConnection();

    // 1. Add Book
    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, author, quantity, available) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getQuantity());
            ps.setBoolean(4, book.isAvailable());

            ps.executeUpdate();
            System.out.println("Book Added Successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. Get All Books
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("quantity"),
                        rs.getBoolean("available"));
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    // 3. Update Book
    public void updateBook(Book book) {
        String sql = "UPDATE books SET title=?, author=?, quantity=?, available=? WHERE id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getQuantity());
            ps.setBoolean(4, book.isAvailable());
            ps.setInt(5, book.getId());

            ps.executeUpdate();
            System.out.println("Book Updated Successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4. Delete Book
    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Book Deleted Successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 5. Search Book by Title or Author
    public List<Book> searchBook(String keyword) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("quantity"),
                        rs.getBoolean("available"));
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
}
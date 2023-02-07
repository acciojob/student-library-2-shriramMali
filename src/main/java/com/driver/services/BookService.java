package com.driver.services;

import com.driver.models.Author;
import com.driver.models.Book;
import com.driver.repositories.AuthorRepository;
import com.driver.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {


    @Autowired
    BookRepository bookRepository2;

    @Autowired
    AuthorRepository authorRepository;


    public void createBook(Book book){
        Author author=book.getAuthor();

        List<Book> list=author.getBooksWritten();

        if(list==null) {
            list=new ArrayList<>();
        }

        list.add(book);

        author.setBooksWritten(list);

        book.setAuthor(author);

        //authorRepository.save(author);

        bookRepository2.save(book);
    }

    public List<Book> getBooks(String genre, boolean available, String author){
        List<Book> books;

        if(genre != null && author != null){
            books = bookRepository2.findBooksByGenreAuthor(genre, author, available);
        }else if(genre != null){
            books = bookRepository2.findBooksByGenre(genre, available);
        }else if(author != null){
            books = bookRepository2.findBooksByAuthor(author, available);
        }else{
            books = bookRepository2.findByAvailability(available);
        }
        if(books==null){
            books=new ArrayList<>();
        }
        return books;
    }

}
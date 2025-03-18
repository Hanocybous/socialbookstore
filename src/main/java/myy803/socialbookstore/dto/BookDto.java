package myy803.socialbookstore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myy803.socialbookstore.mapper.BookAuthorMapper;
import myy803.socialbookstore.mapper.BookCategoryMapper;
import myy803.socialbookstore.model.entities.Book;
import myy803.socialbookstore.model.entities.BookAuthor;
import myy803.socialbookstore.model.entities.BookCategory;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Data transfer object for Book entity.
 * Used to transfer data between the controller and the service.
 * Contains the book's title, authors, category, rating and search strategy.
 *
 * @see Book
 * @see BookAuthor
 * @see BookCategory
 * @see BookAuthorMapper
 * @see BookCategoryMapper
 */
@Getter
@Setter
@NoArgsConstructor
@Component
public class BookDto {
    private int id;
    private String title;
    private String authors;
    private String category;
    private double rating;
    private String searchStrategy;

    public BookDto(int offerId, String bookTitle, String bookAuthors, String bookCategory, double rating) {
        this.id = offerId;
        this.title = bookTitle;
        this.authors = bookAuthors;
        this.category = bookCategory;
        this.rating = rating;
    }

    public BookDto(int offerId, String title, @NotNull String authors, String category) {
        this.id = offerId;
        this.title = title;
        this.authors = authors;
        this.category = category;
    }

    @Override
    public String toString() {
        return "BookOfferDto [offerId=" + id + ", bookTitle=" + title + ", bookAuthors=" + authors
                + ", bookCategory=" + category + ", rating=" + rating + "]";
    }

    public Book buildBookOffer(BookAuthorMapper bookAuthorMapper, @NotNull BookCategoryMapper bookCategoryMapper) {
        Book bookOffer = new Book(title);
        bookOffer.setRating(rating);

        List<BookCategory> bookCategories = bookCategoryMapper.findByName(category);
        if (bookCategories.isEmpty()) {
            bookCategories = bookCategoryMapper.findByName("Other");
        }

        bookOffer.setCategory(bookCategories.get(0));

        String[] bookAuthorsArray = authors.split(",");
        for (String s : bookAuthorsArray) {
            List<BookAuthor> bookAuthors = bookAuthorMapper.findByName(s.trim());
            if (!bookAuthors.isEmpty())
                bookOffer.addAuthor(bookAuthors.get(0));
            else
                bookOffer.addAuthor(new BookAuthor(s.trim()));
        }

        return bookOffer;
    }
}

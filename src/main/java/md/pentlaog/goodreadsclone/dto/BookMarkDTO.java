package md.pentlaog.goodreadsclone.dto;

import lombok.Data;
import md.pentlaog.goodreadsclone.model.Book;
import md.pentlaog.goodreadsclone.model.BookMark;
import md.pentlaog.goodreadsclone.model.User;

@Data
public class BookMarkDTO {

    private Long id;
    private User user;
    private Book book;
    private int mark;

    public BookMark toBookMark() {
        BookMark bookMark = new BookMark();
        bookMark.setId(id);
        bookMark.setUser(user);
        bookMark.setBook(book);
        bookMark.setMark(mark);
        return bookMark;
    }

    public static BookMarkDTO fromBookMark(BookMark bookMark) {
        BookMarkDTO bookMarkDTO = new BookMarkDTO();
        bookMarkDTO.setId(bookMark.getId());
        if (bookMark.getUser() != null)
            bookMarkDTO.setUser(bookMark.getUser());
        if (bookMark.getBook() != null)
            bookMarkDTO.setBook(bookMark.getBook());
        bookMarkDTO.setMark(bookMark.getMark());
        return bookMarkDTO;
    }
}

package md.pentlaog.goodreadsclone.rest.book;

import md.pentlaog.goodreadsclone.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class BookAdminControllerV1Test {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookAdminControllerV1 bookAdminControllerV1;

    @Test
    @DisplayName("")
    void addNewBook() {

    }

    @Test
    void removeBook() {
    }
}
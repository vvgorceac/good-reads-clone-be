package md.pentlaog.goodreadsclone.dto;

import lombok.Data;
import md.pentlaog.goodreadsclone.enums.BookFilterField;
import md.pentlaog.goodreadsclone.enums.BookSoring;

import java.util.Map;

@Data
public class BookRequestDTO {
    private BookSoring sorting;
    private Map<BookFilterField, String> filters;
}

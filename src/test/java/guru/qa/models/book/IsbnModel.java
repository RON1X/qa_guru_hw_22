package guru.qa.models.book;

import lombok.Data;

@Data
public class IsbnModel {

    String isbn;

    public IsbnModel(String isbn) {
        this.isbn = isbn;
    }
}

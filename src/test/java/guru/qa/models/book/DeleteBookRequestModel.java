package guru.qa.models.book;

import lombok.Data;

@Data
public class DeleteBookRequestModel {

    String isbn, userId;

    public DeleteBookRequestModel(String isbn, String userId) {
        this.isbn = isbn;
        this.userId = userId;
    }
}
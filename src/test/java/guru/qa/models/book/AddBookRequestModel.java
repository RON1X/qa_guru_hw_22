package guru.qa.models.book;

import lombok.Data;

import java.util.List;

@Data
public class AddBookRequestModel {

    String userId;
    List<IsbnModel> collectionOfIsbns;

    public AddBookRequestModel(String userId, List<IsbnModel> collectionOfIsbns) {
        this.userId = userId;
        this.collectionOfIsbns = collectionOfIsbns;
    }
}
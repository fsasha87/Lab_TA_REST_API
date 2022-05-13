package entities;
import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {
    private int authorId;
    private AuthorName authorName;
    private String nationality;
    private Birth birth;
    private String authorDescription;

    @Data
    @Builder
    public static class AuthorName {
        private String first;
        private String second;
    }

    @Data
    @Builder
    public static class Birth {
        private String date;
        private String country;
        private String city;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
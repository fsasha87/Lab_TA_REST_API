package entities;

import com.google.gson.Gson;

import java.util.Objects;

public class Genre {

    public int genreId;
    public String genreName;
    public String genreDescription;

    public Genre(int genreId, String genreName, String genreDescription) {
        this.genreId = genreId;
        this.genreName = genreName;
        this.genreDescription = genreDescription;
    }

    public Genre() {
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
    //2й способ
//    public String toString() {
//        return new GsonBuilder().setPrettyPrinting().create().toJson(this, pojo.Genre.class);
//    }

        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return this.genreId == genre.genreId && this.genreName.equals(genre.genreName)
        && this.genreDescription.equals(genre.genreDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreId, genreName, genreDescription);
    }


}

package entities;

public class Option {

    private String orderType;
    private int page;
    private boolean pagination;
    private int size;
    private String sortBy;

    public Option() {
        this.orderType = "ASC";
        this.page = 1;
        this.pagination = true;
        this.size = 10;
        this.sortBy = "authorId";
    }

    @Override
    public String toString() {
        return
                "orderType='" + orderType + '\'' +
                ", page=" + page +
                ", pagination=" + pagination +
                ", size=" + size +
                ", sortBy='" + sortBy + '\''
               ;
    }
}

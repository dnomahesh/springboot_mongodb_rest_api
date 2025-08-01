package work.mywork.scm.spring_boot.payload;

public class ApiResponse<T> {
    private T data;
    private Object page;

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(T data, Object page) {
        this.data = data;
        this.page = page;
    }

    public T getData() {
        return data;
    }

    public Object getPage() {
        return page;
    }
}
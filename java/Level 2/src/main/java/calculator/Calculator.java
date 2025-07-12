package calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator<T> {
    private final List<T> results;

    public Calculator() {
        this.results = new ArrayList<>();
    }

    public List<T> getResults() {
        return results;
    }

    public void setResult(T n) {
        results.add(n);
    }

    public void removeResult(){
        results.remove(0);
    }

    public void inquiryResults(){
        results.forEach(x -> System.out.print(x + " "));
        System.out.println();
    }
}

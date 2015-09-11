import rx.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan on 11/09/15.
 */
public class ApiService {

    public Observable<List<String>> getCountries() {

        List<String> countries = new ArrayList<>();
        countries.add("USA");
        countries.add("China");
        countries.add("Atlantis");

        return Observable.just(countries);
    }
}

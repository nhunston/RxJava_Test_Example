import rx.Observable;

/**
 * Created by Nathan on 11/09/15.
 */
public class ViewModel {

    private ApiService apiService;
    private boolean isRequesting;

    public ViewModel(ApiService apiService) {

        this.apiService = apiService;
        isRequesting = false;
    }

    public Observable<String> getCountriesObservable() {

        return apiService.getCountries()
                .doOnSubscribe(() -> isRequesting = true)
                .doOnTerminate(() -> isRequesting = false)
                .flatMapIterable(countries -> countries)
                .filter(country -> !country.equalsIgnoreCase("Atlantis"));
    }

    public boolean isRequesting() {

        return isRequesting;
    }
}

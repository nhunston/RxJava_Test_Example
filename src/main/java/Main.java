import rx.Subscriber;

/**
 * Created by Nathan on 11/09/15.
 */
public class Main {

    public static void main(String[] args) {

        ViewModel viewModel = new ViewModel(new ApiService());

        viewModel.getCountriesObservable().subscribe(new Subscriber<String>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String country) {

                System.out.println(country);
            }
        });
    }
}

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Observable;
import rx.observers.TestSubscriber;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

public class ViewModelTest {

    @Mock
    ApiService mockApiService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getCountriesObservable_FiltersOutAtlantis() throws Exception {

        List<String> inputCountries = new ArrayList<>();
        inputCountries.add("USA");
        inputCountries.add("China");
        inputCountries.add("Atlantis");

        Observable<List<String>> mockObservable = Observable.just(inputCountries);

        doReturn(mockObservable).when(mockApiService).getCountries();

        List<String> expectedCountries = new ArrayList<>();
        expectedCountries.add("USA");
        expectedCountries.add("China");

        ViewModel viewModel = new ViewModel(mockApiService);
        TestSubscriber<String> testSubscriber = new TestSubscriber<>();

        viewModel.getCountriesObservable().subscribe(testSubscriber);

        testSubscriber.assertReceivedOnNext(expectedCountries);
    }

    @Test
    public void getCountriesObservable_StartsRequestWhenSubscribedTo() throws Exception {

        //Never will never complete so you can use it to keep prevent onComplete or onError being called
        Observable<List<String>> mockObservable = Observable.never();

        doReturn(mockObservable).when(mockApiService).getCountries();

        ViewModel viewModel = new ViewModel(mockApiService);

        TestSubscriber<String> testSubscriber = new TestSubscriber<>();

        viewModel.getCountriesObservable().subscribe(testSubscriber);

        assertTrue(viewModel.isRequesting());
    }

    @Test
    public void getCountriesObservable_FinishesRequestWhenCompleted() throws Exception {

        //Empty will onComplete right away with no events emitted
        Observable<List<String>> mockObservable = Observable.empty();

        doReturn(mockObservable).when(mockApiService).getCountries();

        ViewModel viewModel = new ViewModel(mockApiService);
        TestSubscriber<String> testSubscriber = new TestSubscriber<>();

        viewModel.getCountriesObservable().subscribe(testSubscriber);

        assertFalse(viewModel.isRequesting());
    }
}
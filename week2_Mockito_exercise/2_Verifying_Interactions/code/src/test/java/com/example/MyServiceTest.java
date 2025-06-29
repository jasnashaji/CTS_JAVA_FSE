package com.example;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class MyServiceTest {

    @Test
    public void testVerifyGetDataCalled() {
        ExternalApi mockApi = mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.fetchData();

        // ✅ Verifies getData() was called once
        verify(mockApi).getData();
    }

    @Test
    public void testVerifySendDataCalledWithArgument() {
        ExternalApi mockApi = mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.sendCustomData("Hello World");

        // ✅ Verifies sendData() was called with "Hello World"
        verify(mockApi).sendData("Hello World");
    }
}

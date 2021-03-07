package com.ap.ectswebsite;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import com.ap.ectswebsite.services.HomeService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class HomeServiceTest {
    @Test
    public void HomeServiceResponseEntityAcademiejarenTest() throws IOException {
        // Arrange
        final Set<String> expectedAcademieJaren = Collections.singleton("test-123");
        final RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        ResponseEntity reMock = Mockito.mock(ResponseEntity.class);

        when(reMock.getBody()).thenReturn(restTemplateMock);

        //final HomeService sut = new HomeService(restTemplateMock);
        final ResponseEntity<Set<String>> response = ResponseEntity.ok(expectedAcademieJaren);

        /*
        Mockito.doReturn(response).when(restTemplateMock).exchange("",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Set<String>>(){});
*/

        when(restTemplateMock.exchange("",HttpMethod.GET,null,HomeService.class)).thenReturn(reMock);
        // Act
         //Set<String> academiejaren = sut.getAcademiejaren();

        // Assert
        //assertThat(academiejaren).containsExactly("test-123");

        // extra check, but not REALLY needed
        Mockito.verify(restTemplateMock, Mockito.times(1))
                .exchange("",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Set<String>>(){});
    }
}

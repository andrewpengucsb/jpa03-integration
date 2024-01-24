package edu.ucsb.cs156.example.web;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import edu.ucsb.cs156.example.CaptureStateTransformer;
import edu.ucsb.cs156.example.ExampleApplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import edu.ucsb.cs156.example.testconfig.IntegrationTestConfig;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;

import com.microsoft.playwright.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { ExampleApplication.class, IntegrationTestConfig.class })
@ActiveProfiles("integration")
public class WebsiteIntegrationTests2 {

    private Browser browser;
    private Page page;
    private WireMockServer wireMockServer;

//     @Autowired
//     private WebApplicationContext context;

//     private WebClient webClient;

    @BeforeEach
    public void setup() {

        WireMockConfiguration wireMockConfiguration = WireMockConfiguration.options()
                .extensions(CaptureStateTransformer.class);

        wireMockServer = new WireMockServer(options().port(8090));
        wireMockServer.start();

        // set up a Mock OAuth server
        stubFor(get(urlPathMatching("/oauth/authorize.*"))
                .willReturn(aResponse()
                        .withStatus(302)
                        .withHeader("Location", "http://localhost:8080/login/oauth2/code/my-oauth-client?code=my-acccess-code&state=${state}")
                        .withTransformers("CaptureStateTransformer")
                )
        );

        stubFor(post(urlPathMatching("/oauth/token"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"access_token\":\"my-access-token\"" +
                                ", \"token_type\":\"Bearer\"" +
                                ", \"expires_in\":\"3600\"" +
                                "}")
                )
        );

        stubFor(get(urlPathMatching("/userinfo"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"sub\":\"my-user-id\"" +
                                ",\"name\":\"Mark Hoogenboom\"" +
                                ", \"email\":\"mark.hoogenboom@example.com\"" +
                                "}")
                )
        );

        browser = Playwright.create().chromium().launch();
        BrowserContext context = browser.newContext();
        page = context.newPage();

        // webClient = MockMvcWebClientBuilder
        // .webAppContextSetup(context, springSecurity())
        // .build();
    }

    @AfterEach
    public void teardown() {
        wireMockServer.stop();

        browser.close();
    }

    @Test
    public void testGreeting() throws Exception {

        page.navigate("http://localhost:8080/");

        // HtmlPage page = webClient.getPage("http://localhost:8080/");
        // assertEquals("Welcome\r\n Hello, Mark Hoogenboom!", page.asText());
    }
}

package com.example.spring.controllers;

import com.example.spring.DemoFeignApplicationTest;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DemoFeignApplicationTest.class})
@WebAppConfiguration
public class PersonControllerTest {

    @Inject
    private WebApplicationContext webApplicationContext;
    private static WireMockServer wireMockServer;

    private MockMvc mockMvc;

    private static final int wireMockPort = 8089;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        wireMockServer = new WireMockServer(wireMockPort);
        wireMockServer.start();
    }

    @AfterClass
    public static void tearDownAfter() throws Exception {
        wireMockServer.shutdown();
        wireMockServer.stop();
    }

    @Test
    public void findById() throws Exception {

        wireMockServer.stubFor(get(urlPathMatching("/persons/1234"))
                .willReturn(aResponse()
                        .withStatus(HttpResponseStatus.OK.code())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\":\"1234\",\"name\":\"mirtinha\",\"dni\":\"the-dni\",\"phone\":\"4567888\"}")));


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/persons/1234");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1234")));
    }

    @Test
    @Ignore
    public void findAll() throws Exception {

    }

    @Test
    @Ignore
    public void create() throws Exception {

    }

    @Test
    @Ignore
    public void delete() throws Exception {

    }

    @Test
    @Ignore
    public void update() throws Exception {

    }

    @Test
    @Ignore
    public void handleAllException() throws Exception {

    }

}
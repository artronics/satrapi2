package artronics.satrapi.server.controller;

import artronics.satrapi.server.services.exceptions.ModelAlreadyExistsException;
import artronics.satrapi.server.model.Account;
import artronics.satrapi.server.services.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AccountControllerTest
{
    @InjectMocks
    private AccountController controller;

    @Mock
    private AccountService service;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void create_account() throws Exception
    {
        Account acc = new Account();
        acc.setId(1L);
        acc.setEmail("jalalhosseiny@gmail.com");
        acc.setPassword("foo");

        when(service.createAccount(any(Account.class))).thenReturn(acc);

        mockMvc.perform(post("/accounts")
                                .content("{\"name\":\"test\",\"password\":\"test\"}")
                                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated());
    }

    @Test
    public void create_already_existed_acc() throws Exception
    {
        when(service.createAccount(any(Account.class))).thenThrow(new ModelAlreadyExistsException
                                                                          ());

        mockMvc.perform(post("/accounts")
                                .content("{}")
                                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isConflict());
    }
}
package io.github.arturgaleno.service;

import io.github.arturgaleno.Application;
import io.github.arturgaleno.TextContext;
import io.github.arturgaleno.model.SusDoctors;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by artur on 17/04/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TextContext.class)
public class SusDoctorsServiceTest {

    @Autowired
    private SusDoctorsService susDoctorsService;

    @Test
    public void shoudReturnValuesFromWebResource() {
        SusDoctors next = susDoctorsService.getNext();
        Assert.assertNotNull(next);
    }

}

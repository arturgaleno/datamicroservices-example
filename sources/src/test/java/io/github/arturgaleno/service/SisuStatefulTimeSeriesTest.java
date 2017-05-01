package io.github.arturgaleno.service;

import io.github.arturgaleno.TestContext;
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
@ContextConfiguration(classes = TestContext.class)
public class SisuStatefulTimeSeriesTest {

    @Autowired
    private MecTimeSeriesClient mecTimeSeriesClient;

    @Test
    public void shoudReturnValuesFromWebResource() {
        StatefulTimeSeriesService statefulTimeSeriesService = new StatefulTimeSeriesService(
                mecTimeSeriesClient.getSisuTimeSeries().getValues()
        );
        Assert.assertNotNull(statefulTimeSeriesService.getNext());
    }

}

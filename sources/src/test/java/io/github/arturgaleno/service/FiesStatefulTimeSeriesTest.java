package io.github.arturgaleno.service;

import io.github.arturgaleno.TestContext;
import io.github.arturgaleno.model.ApiValue;
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
public class FiesStatefulTimeSeriesTest {

    @Autowired
    private MecTimeSeriesClient mecTimeSeriesClient;

    @Test
    public void shoudReturnValuesFromWebResource() {
        StatefulTimeSeriesService statefulTimeSeriesService = new StatefulTimeSeriesService(
                mecTimeSeriesClient.getFiesTimeSeries().getValues()
        );
        ApiValue value1 = statefulTimeSeriesService.getNext();
        Assert.assertNotNull(value1);

        ApiValue value2 = statefulTimeSeriesService.getNext();
        Assert.assertNotNull(value2);

        ApiValue value3 = statefulTimeSeriesService.getNext();
        Assert.assertNotNull(value3);

        Assert.assertNotEquals(value1.getMunicipalityId(), value2.getMunicipalityId());
        Assert.assertNotEquals(value2.getMunicipalityId(), value3.getMunicipalityId());
    }

}

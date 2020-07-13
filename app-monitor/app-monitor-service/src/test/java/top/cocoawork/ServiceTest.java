package top.cocoawork;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.cocoawork.entity.AppOutlineEntity;
import top.cocoawork.entity.GenreEntity;
import top.cocoawork.mapper.AppOutlineMapper;
import top.cocoawork.model.AppOutline;
import top.cocoawork.model.Country;
import top.cocoawork.model.Genre;
import top.cocoawork.service.AppOutlineService;
import top.cocoawork.service.CountryService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceTest {

    @Autowired
    private AppOutlineMapper appOutlineMapper;

    @Autowired
    private CountryService countryService;

    @Autowired
    private AppOutlineService appOutlineService;

    @Test
    public void contextLoad(){}

    @Test
    public void testCountryService() {
//        List<Country> countries = countryService.selectAllCountry();
//        System.out.println(countries);

        AppOutlineEntity entity = new AppOutlineEntity();
        entity.setAppId("1001909623");
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setGenreId("1315465");
        genreEntity.setName("wodefenlei ");

        GenreEntity genreEntity1 = new GenreEntity();
        genreEntity1.setGenreId("23");
        genreEntity1.setName("wodefenlei ");
        List list = new ArrayList();
        list.add(genreEntity);
        list.add(genreEntity1);
        entity.setGenres(list);
        entity.setName("cocoawork");
        appOutlineMapper.updateAppOutline(entity);

    }

    @Test
    public void test1() {
        List<AppOutline> list = appOutlineService.selectAppOutlinesByPage(1, 10);
        System.out.println(list);
    }

}

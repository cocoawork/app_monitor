package com.cocoawork.appstore;

import com.cocoawork.appstore.constant.Constant;
import com.cocoawork.appstore.entity.*;
import com.cocoawork.appstore.mapper.CountryMapper;
import com.cocoawork.appstore.mapper.RoleMapper;
import com.cocoawork.appstore.mapper.UserMapper;
import com.cocoawork.appstore.mapper.UserRoleMapper;
import com.cocoawork.appstore.service.AppService;
import com.cocoawork.appstore.service.UserService;
import com.cocoawork.appstore.task.AppTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppstoreApplicationTests {

    @Autowired
    AppService appService;

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private AppTask appTask;

    @Test
    public void contextLoads() {
    }

    @Test
    public void appServiceTest() throws Exception {
        appService.fetchAppOutlinesFromRemote("cn", Constant.MediaType.IOS_APP, Constant.FeedType.NEW_GAME_WE_LOVE);
    }

    @Test
    public void insertCountry() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:script/country.txt");

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String textRead = null;
            while ((textRead = bufferedReader.readLine()) != null) {

                List keys = this.match(textRead, "option", "value");
                List values = this.matchContent(textRead, "option");

                Country country = new Country();

                if (keys.size() > 0) {
                    String key = (String) keys.get(0);
                    country.setCountryCode(key);

                }
                if (values.size() > 0) {
                    String value = (String) values.get(0);
                    country.setCountryName(value);

                }
                countryMapper.addCountry(country);
            }
            String content = stringBuilder.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void appTask() {

        appTask.fetchApp();


    }


    @Autowired
    private RoleMapper roleMapper;
    @Test
    public void testRoleInsert() {
        Role role = new Role();
        role.setRoleName("user");
        role.setRoleTitle("用户");

        Permission p1 = new Permission();
        p1.setId(335);
        p1.setPerAuth("update");
        p1.setPerTitle("update");

        Permission p2 = new Permission();
        p2.setId(888);
        p2.setPerAuth("delete");
        p2.setPerTitle("delete");

        List pers = new ArrayList();
        pers.add(p1);
        pers.add(p2);
        role.setPermissions(pers);
        roleMapper.addRole(role);

    }



    @Autowired
    private UserMapper userMapper;
    @Test
    public void addUserTest() {

        Role role = new Role();
        role.setId(45);
        role.setRoleName("user");
        role.setRoleTitle("用户");

        Permission p1 = new Permission();
        p1.setId(335);
        p1.setPerAuth("update");
        p1.setPerTitle("update");

        Permission p2 = new Permission();
        p2.setId(888);
        p2.setPerAuth("delete");
        p2.setPerTitle("delete");

        List pers = new ArrayList();
        pers.add(p1);
        pers.add(p2);
        role.setPermissions(pers);
        roleMapper.addRole(role);

        List roles = new ArrayList();
        roles.add(role);

        User user = new User();
        user.setAge(20);
        user.setGender(0);
        user.setPassword("fffff");
        user.setPhoneNum("155555");
        user.setUserName("usernamemmm");
        userMapper.addUser(user);

    }


    @Autowired
    UserService userService;
    @Test
    public void testUserLogin(){
        User cocoawork = userService.getUser("cocoawork", "123456");
        System.out.println(cocoawork);
    }


    @Autowired
    private UserRoleMapper userRoleMapper;
    @Test
    public void testUserRoleInsert() {
        Role role = new Role();
        role.setId(48);
        role.setRoleName("user");
        role.setRoleTitle("用户");

        Permission p1 = new Permission();
        p1.setId(335);
        p1.setPerAuth("update");
        p1.setPerTitle("update");

        Permission p2 = new Permission();
        p2.setId(888);
        p2.setPerAuth("delete");
        p2.setPerTitle("delete");

        List pers = new ArrayList();
        pers.add(p1);
        pers.add(p2);
        role.setPermissions(pers);
        roleMapper.addRole(role);

        List roles = new ArrayList();
        roles.add(role);


        UserRole userRole = new UserRole();
        userRole.setUid("e10d1e2a879b11eaadee84e6f20a5baa");
        userRole.setRoles(roles);
        UserRole e10d1e2a879b11eaadee84e6f20a5baa = userRoleMapper.getUserRoleById("e10d1e2a879b11eaadee84e6f20a5baa");
        System.out.println(e10d1e2a879b11eaadee84e6f20a5baa);
    }


    public List<String> match(String source, String element, String attr) {
        List<String> result = new ArrayList<String>();
        String reg = "<" + element + "[^<>]*?\\s?" + attr + "=['\"]?(.*?)['\"]?[(\\s.*?)>]";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(1);
            result.add(r);
        }
        return result;
    }

    public List<String> matchContent(String source, String element) {
        List<String> result = new ArrayList<String>();
        String reg = "<" + element +"[^>]*>([^<]*)</" + element +">";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(1);
            result.add(r);
        }
        return result;
    }

}

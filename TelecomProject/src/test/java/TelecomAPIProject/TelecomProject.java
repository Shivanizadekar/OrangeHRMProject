package TelecomAPIProject;

import io.restassured.response.Response;
import listeners.ExtentManager;

import static io.restassured.RestAssured.*;
import java.util.HashMap;

import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import org.testng.annotations.Listeners;

@Listeners(TelecomAPIProject.ExtentITestNGListeners.class)
public class TelecomProject {

    ExtentReports extent = ExtentManager.getInstance();
    ExtentTest test;

    String tokenvalue;
    String logintoken;
    String id;

    String email = GlobalData.email;

    @Test(priority = 1)
    public void addNewUser() {
        test = extent.createTest("Add New User");

        HashMap<String, String> data = new HashMap<>();
        data.put("firstName", "Shivani");
        data.put("lastName", "Zadekar");
        data.put("email", email);
        data.put("password", "Shivani123");

        Response res = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(data)
                .when().post("https://thinking-tester-contact-list.herokuapp.com/users");

        res.then().log().body();
        tokenvalue = res.jsonPath().getString("token");
        test.pass("User created with token: " + tokenvalue);
    }

    @Test(priority = 2, dependsOnMethods = "addNewUser")
    public void getProfile() {
        test = extent.createTest("Get User Profile");

        Response res = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokenvalue)
                .when().get("https://thinking-tester-contact-list.herokuapp.com/users/me");

        res.then().log().body();
        test.pass("Profile retrieved with status: " + res.statusCode());
    }

    @Test(priority = 3)
    public void updateUser() {
        test = extent.createTest("Update User");

        HashMap<String, String> updateData = new HashMap<>();
        updateData.put("firstName", "Radhe");
        updateData.put("lastName", "Pawar");
        updateData.put("email", email);
        updateData.put("password", "test123");

        Response res = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokenvalue)
                .body(updateData)
                .when().patch("https://thinking-tester-contact-list.herokuapp.com/users/me");

        res.then().log().body();
        test.pass("User updated with status: " + res.statusCode());
    }

    @Test(priority = 4)
    public void loginUser() {
        test = extent.createTest("Login User");

        HashMap<String, String> loginData = new HashMap<>();
        loginData.put("email", email);
        loginData.put("password", "test123");

        Response res = given()
                .header("Content-Type", "application/json")
                .body(loginData)
                .when().post("https://thinking-tester-contact-list.herokuapp.com/users/login");

        res.then().log().body();
        logintoken = res.jsonPath().getString("token");
        test.pass("User logged in with token: " + logintoken);
    }

    @Test(priority = 5)
    public void addContact() {
        test = extent.createTest("Add Contact");

        HashMap<String, Object> contactData = new HashMap<>();
        contactData.put("firstName", "John");
        contactData.put("lastName", "Doe");
        contactData.put("birthdate", "1970-01-01");
        contactData.put("email", "jdoe@fake.com");
        contactData.put("phone", "8005555555");
        contactData.put("street1", "1 Main St.");
        contactData.put("street2", "Apartment A");
        contactData.put("city", "Anytown");
        contactData.put("stateProvince", "KS");
        contactData.put("postalCode", "12345");
        contactData.put("country", "USA");

        Response res = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + logintoken)
                .body(contactData)
                .when().post("https://thinking-tester-contact-list.herokuapp.com/contacts");

        res.then().log().body();
        id = res.jsonPath().getString("_id");
        test.pass("Contact added with ID: " + id);
    }

    @Test(priority = 6)
    public void getContactList() {
        test = extent.createTest("Get Contact List");

        Response res = given()
                .header("Authorization", "Bearer " + logintoken)
                .when().get("https://thinking-tester-contact-list.herokuapp.com/contacts");

        res.then().log().body();
        test.pass("Contact list retrieved");
    }

    @Test(priority = 7)
    public void getContact() {
        test = extent.createTest("Get Single Contact");

        Response res = given()
                .header("Authorization", "Bearer " + logintoken)
                .when().get("https://thinking-tester-contact-list.herokuapp.com/contacts/" + id);

        res.then().log().body();
        test.pass("Contact retrieved with ID: " + id);
    }

    @Test(priority = 8)
    public void updateContact() {
        test = extent.createTest("Full Update Contact");

        HashMap<String, Object> fullUpdate = new HashMap<>();
        fullUpdate.put("firstName", "Amy");
        fullUpdate.put("lastName", "Miller");
        fullUpdate.put("birthdate", "1992-02-02");
        fullUpdate.put("email", "amiller@fake.com");
        fullUpdate.put("phone", "8005554242");
        fullUpdate.put("street1", "13 School St.");
        fullUpdate.put("street2", "Apt. 5");
        fullUpdate.put("city", "Washington");
        fullUpdate.put("stateProvince", "QC");
        fullUpdate.put("postalCode", "A1A1A1");
        fullUpdate.put("country", "Canada");

        Response res = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + logintoken)
                .body(fullUpdate)
                .when().put("https://thinking-tester-contact-list.herokuapp.com/contacts/" + id);

        res.then().log().body();
        test.pass("Contact updated with PUT request");
    }

    @Test(priority = 9)
    public void updateContactPatch() {
        test = extent.createTest("Patch Update Contact");

        HashMap<String, String> patchData = new HashMap<>();
        patchData.put("firstName", "Amy");
        patchData.put("lastName", "Miller");

        Response res = given()
                .header("Authorization", "Bearer " + logintoken)
                .body(patchData)
                .when().patch("https://thinking-tester-contact-list.herokuapp.com/contacts/" + id);

        res.then().log().body();
        test.pass("Contact patched successfully");
    }

    @Test(priority = 10)
    public void logoutUser() {
        test = extent.createTest("Logout User");

        Response res = given()
                .header("Authorization", "Bearer " + logintoken)
                .when().post("https://thinking-tester-contact-list.herokuapp.com/users/logout");

        res.then().log().body();
        test.pass("User logged out");
    }
}

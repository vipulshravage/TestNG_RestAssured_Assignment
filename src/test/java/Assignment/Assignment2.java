package Assignment;

import Reusable.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Assignment2
{
    public String id1;
    Reusable RA;

    @BeforeClass
    public void setUp()
    {
        RA=new Reusable();
    }

    @Parameters({"baseurl","name1","year1","DOB1","Address1","Salary1"})
    @Test(priority = 0)
    public void post_call(String url,String name1,String year1,String DOB1,String Address1,String Salary1)
    {
        Response response = given().
                contentType(ContentType.JSON).
                body(RA.CreatePostJson(name1,year1,DOB1,Address1,Salary1)).
                when().
                post(url);

        int statusCode = response.getStatusCode();
        System.out.println("POST Response Status Code: " + statusCode);

        String responseBody = response.getBody().asString();
        System.out.println("POST Response Body: " + responseBody);

        id1=response.getBody().jsonPath().getString("id");
        System.out.println("Id :"+id1);

    }

    @Parameters({"baseurl","name1","year1","DOB1","Address1","Salary1"})
    @Test(priority = 1)
    public void get_call(String baseurl,String name1,String year1,String DOB1,String Address1,String Salary1)
    {
            Response response = get(baseurl+"?id="+id1);

            String responseBody1= response.getBody().asString();
            System.out.println("GET Response Body: " + responseBody1);

            int statusCode = response.getStatusCode();
            System.out.println("GET Response Status Code: " + statusCode);

            //String status_code = String.valueOf(response.statusCode());
            //Assert.assertEquals(status_code, "200");


            //System.out.println("Name Value is : "+response.getBody().jsonPath().getString("name"));

    }

    @Parameters({"baseurl","name1","year1","DOB1","UpdatedAddress","Salary1"})
    @Test(priority = 2)
    public void put_call(String url,String name1,String year1,String DOB1,String Address1,String Salary1)
    {

        Response response = given().
                contentType(ContentType.JSON).
                body(RA.CreatePostJson(name1,year1,DOB1,Address1,Salary1)).
                when().
                put(url+"/"+id1);

        int statusCode = response.getStatusCode();
        System.out.println("PUT Response Status Code: " + statusCode);

        Response response1 = get(url+"?id="+id1);

        String Address = response1.getBody().jsonPath().getString("data[0].Address");
        System.out.println("Updated Address is "+Address);
        Assert.assertEquals(Address,Address1);

        String responseBody2 = response.getBody().asString();
        System.out.println("PUT Response Body: " + responseBody2);
    }

}

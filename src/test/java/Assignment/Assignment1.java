package Assignment;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.get;

public class Assignment1
{

    @Parameters({"url"})
    @Test
    public void get_call(String baseurl)
    {
        try {
            Response response = get(baseurl);

            int statusCode = response.getStatusCode();
            System.out.println("Response Status Code: " + statusCode);

            //String status_code = String.valueOf(response.statusCode());
            //Assert.assertEquals(status_code, "200");

            String responseBody= response.getBody().asString();
            System.out.println("Response Body: " + responseBody);

            System.out.println("Title Value is : "+response.getBody().jsonPath().getString("title"));
            System.out.println("Price is : "+response.getBody().jsonPath().getString("price"));
            System.out.println("Brand is : "+response.getBody().jsonPath().getString("brand"));

            int imagecount = response.getBody().jsonPath().getList("images").size();
            System.out.println("Total Image count is : "+imagecount);

            for(int i=0;i<imagecount;i++)
            {
                String images =response.getBody().jsonPath().getString("images["+i+"]");
                System.out.println("Images .jpg file: "+images);
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

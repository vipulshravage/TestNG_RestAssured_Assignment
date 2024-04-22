package Reusable;

public class Reusable
{
    public String CreatePostJson(String name1,String year1,String DOB1,String Address1,String Salary1)
    {
        String body ="{\n" +
                "    \"name\":\""+name1+"\",\n" +
                "    \"data\":{\n" +
                "        \"year\": "+year1+",\n" +
                "        \"DOB\": \""+DOB1+"\",\n" +
                "        \"Address\": \""+Address1+"\",\n" +
                "        \"Salary\": \""+Salary1+"\"\n" +
                "    }\n" +
                "}";
        return body;
    }

}

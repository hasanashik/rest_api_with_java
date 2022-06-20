package files;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args){
        JsonPath js = new JsonPath(payload.CoursePrice());

        int count = js.getInt("courses.size()");
        System.out.println("1. Print No of courses returned by API: " + count);
        System.out.println("2. Print Purchase Amount: " + js.getInt("dashboard.purchaseAmount"));
        System.out.println("3. Print Title of the first course: " + js.getString("courses[0].title"));
        //4. Print All course titles and their respective Prices
        System.out.println("4. Print All course titles and their respective Prices");
        for(int i = 0; i < count; i++){

            System.out.println("    Course title : " + js.getString("courses["+ i + "].title") + " and price: "+ js.getInt("courses["+ i + "].price"));

        }
        // 5. Print no of copies sold by RPA Course
        for(int i = 0; i < count; i++){
            String courseTitle =  js.getString("courses["+ i + "].title");
            if (courseTitle.equalsIgnoreCase("RPA")){
                System.out.println("5. Print no of copies sold by RPA Course: "+ js.getString("courses["+ i + "].copies"));
                break;


            }
        }

        // 6. Verify if Sum of all Course prices matches with Purchase Amount
        System.out.println("6. Verify if Sum of all Course prices matches with Purchase Amount");
        System.out.println("Purchase Amount: " + js.getInt("dashboard.purchaseAmount"));
        int multiplied_result = 0 ;
        for(int i = 0; i < count; i++){
            multiplied_result = multiplied_result + js.getInt("courses["+ i + "].price") * js.getInt("courses["+ i + "].copies");
        }
        System.out.println("multiplied_result = "+ multiplied_result);
    }
}

package Model;

import java.util.HashMap;
import java.util.Iterator;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;

public class loginDatabaseMethods {
	//to work with Control login class
	//primary objective is to isolate the userID based off of user/pass
	
	//Query database based off of username and password
	//returns the integer value that represents the user ID
	//returns -1 if no user is found with the passed credentials
	public int getUserID(String user, String pass) {
		//@Ethan, however you can get the querying to work
		
		ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
        try {
            credentialsProvider.getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (/Users/johnmortensen/.aws/credentials), and is in valid format.",
                    e);
        }
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
        	.withCredentials(credentialsProvider)
            .withRegion("us-west-2")
            .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Memories");

        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#user", "User");
        
        
        HashMap<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put(":us", user);
        
        QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#user = :us").withNameMap(nameMap)
                .withValueMap(valueMap);
        

        ItemCollection<QueryOutcome> items = null;
        Iterator<Item> iterator = null;
        Item item = null;
        
        valueMap.put(":us", user);
        valueMap.put(":inf", "Info");
        
        querySpec
        .withKeyConditionExpression("#user = :us and Specs = :inf").withNameMap(nameMap)
        .withValueMap(valueMap);

        try {
            items = table.query(querySpec);

            iterator = items.iterator();
            while (iterator.hasNext()) {
                item = iterator.next();
            }

        }
        catch (Exception e) {
            System.err.println("Unable to query Sim1 for Stats#b0");
            System.err.println(e.getMessage());
        }
        
        
        if(item != null && item.get("password").equals(pass))
        	return (int) item.getInt("UserID");
        else
        	return -1;
	}
}

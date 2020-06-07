package Model;

import java.util.Arrays;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

public class CreateTable {
	public void main(String args[])
	{
		CreateTable work = new CreateTable();
		work.init();   
	}

	public void init()
	{
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

	        String tableName = "hello";

	        
	            System.out.println("Attempting to create table; please wait...");
	            Table table = dynamoDB.createTable(tableName,
	                Arrays.asList(new KeySchemaElement("User", KeyType.HASH), // Partition
	                                                                          // key
	                    new KeySchemaElement("Specs", KeyType.RANGE)), // Sort key
	                Arrays.asList(new AttributeDefinition("SimID", ScalarAttributeType.N),
	                    new AttributeDefinition("Stats", ScalarAttributeType.S)),
	                new ProvisionedThroughput(10L, 10L));
	            try {
					table.waitForActive();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());

	}
}

package Model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

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
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import Control.DateSort;
import Control.loginControl;
import Control.Driver;

public class memoryDatabaseMethods {
	//primary objective is to use the userID that was previously obtained
	//to create Memory objects out of the data that is pulled from the DynamoDB
	static int userID;
	ArrayList<Memory> memList = new ArrayList<Memory>();
	
	public memoryDatabaseMethods(int userID) {
		this.userID = userID;
	}
	
	static ProfileCredentialsProvider connect()
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
        
        
        return credentialsProvider;
	}
	
	static AmazonS3 connectS3()
	{
		 AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                 .withCredentials(connect())
                 .withRegion("us-west-2")
                 .build();
		 
		 return s3Client;
	}
	
	static Table connectDB()
	{
		
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
        	.withCredentials(connect())
            .withRegion("us-west-2")
            .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Memories");
        
        return table;
	}
	
	public static String incremmentName()
	{
		String name = "mem0000";
		
		ArrayList<Item>its = query(loginControl.username, loginControl.password);
		
		int num = 0;
		
		for(Item i:its)
		{
			String parse = i.getString("Specs");
			char test = parse.charAt(parse.length()-1);
			int hold = Character.getNumericValue(test);
			if(hold>num)
				num = hold;
			
		}
		
		if(num!=9)
		{
			num++;
			name+=num;
		}
		
		return name;
	}
	
	public static void uploadMemory(Memory meme)
	{
		String name = incremmentName();
		Table table = connectDB();
		
		Random q = new Random();
		int r = q.nextInt(10000);
        String ObjKeyName = name+r;
		
		Item upload = new Item()
				.withPrimaryKey("User", loginControl.username, "Specs", name)
				.withString("Caption", meme.caption)
				.withString("dateUploaded", meme.getTime().toString())
				.withString("pathname", meme.getPhoto().toString())
				.withString("name", name)
				.withString("ObjName", ObjKeyName);
		
		
		table.putItem(upload);
		AmazonS3 bucket = connectS3();;
		if(!bucket.doesBucketExistV2(name))
		{
			bucket.createBucket(new CreateBucketRequest(name));
		}
		String bucketName = name;
		
		
        PutObjectRequest request = new PutObjectRequest(bucketName, ObjKeyName, meme.getPhoto());
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/jpeg");
        metadata.addUserMetadata("title", "someTitle");
        request.setMetadata(metadata);
        bucket.putObject(request);
        
	}
	
	public static ArrayList<Item> query(String user, String pass)
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

        Table table = dynamoDB.getTable("Memories");

        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#sID", "User");
        
        //nameMap.put("#stats", "Stats"); //Only need this line when querying off partition and sort key

        HashMap<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put(":id", user);
        //valueMap.put(":Sts", "STATS#b3"); //Only need this line when querying off partition and sort key

        QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#sID = :id").withNameMap(nameMap)
            .withValueMap(valueMap); //Queries off of partition Key

        ItemCollection<QueryOutcome> items = null;
        Iterator<Item> iterator = null;
        Item item = null;
        items = table.query(querySpec);
        ArrayList<Item>it = new ArrayList<Item>();
        iterator = items.iterator();
        while (iterator.hasNext()) {
            item = iterator.next();
            if(item.hasAttribute("Caption"))
            	it.add(item);
        }
        return it;
        
	}
	
	//@Ethan needs implementation
	public ArrayList<Memory> queryDB() {
		//connect to the database
		//select based off of the userID field
		//will call buildMem() for each returned row
		System.out.println("here");
		ArrayList<Item>iter = query(loginControl.username, loginControl.password);
		System.out.println(iter.size());
		for(Item itemboi:iter)
		{

			System.out.println(itemboi.getString("Caption"));
			Memory memGal = buildMem(itemboi);
			memList.add(memGal);
		}
		
		return memList; //return the built list to Driver
	}
	
	public static ArrayList<File>deleteAfter = new ArrayList<File>();
	
	//@Ethan needs implementation
	private Memory buildMem(Item boi) {
		//Memory tempMem;
		//Fill tempMem with row's information
		//pull File from s3 Instance
		//convert Time string into TimeDate using TimeDate.parse
		//store caption String
		
		Memory memDude;
		
		String date;
		String caption;
		
		LocalDate dateing = null;
		
		caption = boi.getString("Caption");
		date = boi.getString("dateUploaded");

		dateing = LocalDate.parse(date);
		
		String pathname = boi.getString("pathname");
		pathname = pathname.substring(0, pathname.lastIndexOf('/')+1);
		pathname+=loginControl.username;
		Random q = new Random();
		int r = q.nextInt(1000);
		pathname+=r;
		pathname+=".jpg";
		
		
		File file = new File(pathname);
		
		deleteAfter.add(file);

		
		memDude = new Memory(file, dateing, caption);
		
		AmazonS3 s3Client = connectS3();
		
		S3Object fullObject = null, objectPortion = null, headerOverrideObject = null;
		fullObject = s3Client.getObject(new GetObjectRequest(boi.getString("name"), boi.getString("ObjName")));
		System.out.println("Content-Type: " + fullObject.getObjectMetadata().getContentType());
        System.out.println("Content: ");
        try {
			displayTextInputStream(fullObject.getObjectContent(), pathname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//memList.add(tempMem); //add to the arraylist
		memList.sort(new DateSort()); //sort the arraylist from TimeDate field
		return memDude;
	}
	
	public void displayTextInputStream(InputStream input, String pathname) throws IOException {
        // Read the text input stream one line at a time and display each line.
		//String fileName
		Driver.ByteArrayToImage(pathname, input);
	}
	
	private LocalDate stringToTimeDate(String str) {
		return LocalDate.parse(str);
	}
}

package com.turreta.aws.sqs;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.*;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class DemoSQS {
	public static void main(String[] args) {
		
        AmazonSQSClientBuilder f = AmazonSQSClient.builder();
        f.setRegion(Regions.AP_SOUTHEAST_1.getName());
        
        // Build the SQS Client
        AmazonSQS amazonSQS = f.build();
        
        // Create a message request
        SendMessageRequest req = new SendMessageRequest();
        
        // Specify the URL of our queue
        req.setQueueUrl("https://sqs.ap-southeast-1.amazonaws.com/854968627112/TURRETA-QUE001");
        
        // Specify the message content
        
        req.setMessageBody("Hello Turreta.com!");
        req.setDelaySeconds(10);
       
        // Send the message
        amazonSQS.sendMessage(req);
        
        System.out.println("Message sent!");
	}
}

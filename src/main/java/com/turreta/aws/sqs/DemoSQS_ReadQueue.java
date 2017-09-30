package com.turreta.aws.sqs;
import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.*;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityRequest;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

public class DemoSQS_ReadQueue {
	public static void main(String[] args) {
		
		String queueUrl = "https://sqs.ap-southeast-1.amazonaws.com/854968627112/TURRETA-QUE001";
		
        AmazonSQSClientBuilder f = AmazonSQSClient.builder();
        f.setRegion(Regions.AP_SOUTHEAST_1.getName());
        
        // Build the SQS Client
        AmazonSQS amazonSQS = f.build();
        
        System.out.println("Receive messages from queue:");
        ReceiveMessageRequest rcvMsgReq = new ReceiveMessageRequest();
        rcvMsgReq.setQueueUrl(queueUrl);
        rcvMsgReq.setMaxNumberOfMessages(5);
        List<Message> messages= amazonSQS.receiveMessage(rcvMsgReq).getMessages();
        for(Message msg: messages)
        {        	
        	System.out.println(msg.getBody());
        	        	
            ChangeMessageVisibilityRequest cmvReq = new ChangeMessageVisibilityRequest();
            cmvReq.setQueueUrl(queueUrl);
            cmvReq.setReceiptHandle(msg.getReceiptHandle());
            cmvReq.setVisibilityTimeout(10);
            amazonSQS.changeMessageVisibility(cmvReq);
            
            // Delete the current message
            DeleteMessageRequest dmReq = new DeleteMessageRequest();
            dmReq.setQueueUrl(queueUrl);
            dmReq.setReceiptHandle(msg.getReceiptHandle());
            amazonSQS.deleteMessage(dmReq);
        }   
	}
}

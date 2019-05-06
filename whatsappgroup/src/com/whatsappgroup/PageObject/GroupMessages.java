package com.whatsappgroup.PageObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.whatsappgroup.CommonProperties.BaseTest;
import com.whatsappgroup.Locators.Locators;



public class GroupMessages extends BaseTest
{


    static String grpname = sGrpName;
    static int count;
    static List<String> newMessages = new ArrayList<>();
    public static int newMessageCountInAGroup()
    {


	int new_Messages = driver.findElements(By.xpath("//div[@id='side']//span[@title='"+grpname+"']/../../../..//span[@class='OUeyt']")).size();
	return new_Messages;

    }


    public static void readNewMessageInAGroup()
    {
	try
	{	

	    String Message_count = driver.findElement(By.xpath("//div[@id='side']//span[@title='"+grpname+"']/../../../..//span[@class='OUeyt']")).getText();
	    count =Integer.parseInt(Message_count);

	    if(count > 0)
	    {
		System.out.println("Number of new Message have arrived in group :" + count);

		driver.findElement(By.xpath("//div[@id='side']//span[@title='"+grpname+"']")).click();

		try {
		    Thread.sleep(50000);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		String typemessage  = "//div[text() = 'Type a message']";
		WebElement Messagebox = (new WebDriverWait(driver, 10))
			.until(ExpectedConditions.presenceOfElementLocated(By.xpath(typemessage)));

		int group_Message_Count = driver.findElements(By.xpath(Locators.WhatsAppWeb.totalMessagesXpath)).size();
		int old_Message_count = group_Message_Count - count;
		System.out.println("old message" + old_Message_count);
		for(int i=old_Message_count+1;i<=group_Message_Count;i++)
		{


		    String new_Message_Text= driver.findElement(By.xpath("(//span[@class='selectable-text invisible-space copyable-text'])["+i+"]")).getText();

		    System.out.println("New Message in Group"+new_Message_Text);

		    newMessages.add(new_Message_Text);

		}

	    }

	    else
		System.out.println("No Messages to read");

	}

	catch (NoSuchElementException e) {
	    System.out.println(e.getMessage());
	}
    }
    public static void copyNewMessages()
    {
	BufferedWriter writer = null;
	try
	{
	    writer = new BufferedWriter( new FileWriter( "C:\\Users\\Sai\\Desktop\\NewMessages"));
	    for(int i =0 ; i<newMessages.size();i++)
		writer.write( newMessages.get(i));
	    writer.newLine();

	}
	catch ( IOException e)
	{
	    
	}
	finally
	{
	    try
	    {
		if ( writer != null)
		    writer.close( );
	    }
	    catch ( IOException e)
	    {
	    }
	}  
    }
}




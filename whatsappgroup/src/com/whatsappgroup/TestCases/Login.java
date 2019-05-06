package com.whatsappgroup.TestCases;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.whatsappgroup.CommonProperties.BaseTest;
import com.whatsappgroup.PageObject.GroupMessages;

public class Login extends BaseTest
{
int newMessageCount;

@BeforeMethod()
public void Login() throws Exception 
{

driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
BaseTest.logIn();
}

@Test()
public void ReadNewMessageFromGroup()
{


newMessageCount = GroupMessages.newMessageCountInAGroup();
if(newMessageCount>0)
{
    
    GroupMessages.readNewMessageInAGroup();
}

else
    System.out.println("No New Message arrived");

GroupMessages.copyNewMessages();

}


}

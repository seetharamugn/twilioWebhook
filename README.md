# twilioWebhook

For the incoming response to the Twilio number system, we capture the body, from the media and then send it to preferred mail.

When Twilio calls your webhook, it sends a number of parameters about the message you just received. Using some of these parameters we extract the incoming phone number, body of the message, and images then we download the message and images then forward them to the preferred mail id and then send back a confirmation message to the users like your query received and we will get back to you.

**Step 1**

Create a spring boot project with some dependencies are Twilio, spring-web, Postgres, java-mail, and some external dependencies we directly add to the pom file.

**Step 2**

Add some configuration details in the properties file

            server.port=8080
            spring.mail.host=domain.net
            spring.mail.properties.mail.smtp.auth=false
            spring.mail.properties.mail.smtp.starttls.enable=false
            spring.mail.properties.mail.smtp.ssl.enable=false
            spring.mail.from=*****@gmail.net
            spring.mail.to=****@gmail.net
            spring.mail.response=We have received your query and will get back to you shortly.
            email.api.url=****/reportsnew/addjobstoemailqueue/
            app.type=30
            spring.datasource.url=jdbc:postgresql://******/tristar_migration
            spring.datasource.username=****
            spring.datasource.password=******
            spring.jpa.show-sql=true

**The SQL dialect makes Hibernate generate better SQL for the chosen database**

    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
**Hibernate ddl auto (create, create-drop, validate, update)**

    spring.jpa.hibernate.ddl-auto=update

**Step 3**

Create an endpoint for generating the webhook API
In the parameter section, I use the @requestParam annotation to capture some of the parameters from the incoming SMS and MMS.  For more information use the below link to explore
    
    https://www.twilio.com/docs/sms/api/message-resource#message-properties


**Step 4**

Check the SMS or MMS is coming from the user based on that we have to perform

if (Integer.parseInt(numMediastr)>0)

If this condition is satisfied the incoming message is MMS, otherwise, its SMS, once statement work we have to go for adding the message to the email section we need more parameters like to, from, subject, and body attach files so we are going to create an email section. Use the add job email queue for scheduling the email it will schedule the email to send to the preferred email 


**more info visit the website to understand**
    
    https://www.twilio.com/docs/usage/webhooks/sms-webhooks

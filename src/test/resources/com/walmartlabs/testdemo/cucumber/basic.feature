Feature: Hello World Feature
        In order to ensure that my installation works
        As a Developer
        I want to run a quick Cuke4Duke test

        Scenario: Hello World Scenario
                Given The Action is Hello
                When The Subject is Ninja
                Then The Greeting is Hello, Ninja.
                
        Scenario: rest get
                When the request GET /hello/echo/SierraTangoNevada
                Then the response is SierraTangoNevada
                
         Scenario: Post a cat message
                When I post cat payload from file cat.xml to /hello/diffmessage
                Then the post response for cat is 200
         
         Scenario: Post a dog message
                When I post dog payload from file dog.xml to /hello/diffmessage
                Then the post response for dog is 200
                
         Scenario: Manual review notify
                When I post manual notify payload from file fraud_mr_notify_us.xml to /fraudmrnotify/accertify
                Then the response from manual notify is 200
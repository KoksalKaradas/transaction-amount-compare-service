# Test task

## Initial conditions:

1) Redis with stored transaction:

- key - transaction id
- value - metadata with json string

Commands to populate Redis:

- `set 123 '{"amount":100.05,"metadata":{"originatorId":1,"destinationId":2}}'`
- `set 124 '{"amount":150.75,"metadata":{"originatorId":10,"destinationId":20}}'`
- `set 125 '{"amount":1010.00,"metadata":{"originatorId":20,"destinationId":30}}'`
- `set 126 '{"amount":15.5,"metadata":{"originatorId":30,"destinationId":40}}'`

2) You receive transactions from Kafka topic. Example of a message in Kafka:
    ```json
    [
        {
          "PID": 123,
          "PAMOUNT": 94.7,
          "PDATA": 20160101120000
        },
        {   
          "PID": 123,
          "PAMOUNT": 94.7,
          "PDATA": 20160101120000
        },
        {   
          "PID": 124,
          "PAMOUNT": 150.75,
          "PDATA": 20160101120001
        },
        {   
          "PID": 125,
          "PAMOUNT": 1020.2,
          "PDATA": 20160101120002
        },
        {   
          "PID": 126,
          "PAMOUNT": 15.5,
          "PDATA": 20160101120003
        },
        {   
          "PID": 127,
          "PAMOUNT": 120.74,
          "PDATA": 20160101120004
        }
    ]
    ```

## Task:

You have to compare the `amount` field of the received transaction against the transaction in Redis.
You should query Redis by transaction id. Comparison has to be sent to another kafka topic, the
message should contain enough information to understand the result.
 
## Requirements:

Take into account, that in future you:

* Might receive transactions not only from Kafka topic
* Might have to support several result message formats
* Might need to send check results via different channels, e.g. make an API call

## Expected result:

* Source code of Spring Boot 3 application, using Java 17+
* Maven should be able to package code to jar
* Convenient way to check that application works

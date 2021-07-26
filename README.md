# solr-index-ui
Solr-index-ui

- This project consists of three parts, i.e Data Processing, Frontend and Backend.
- Clone the Repository.

## Steps to Run
---
### 1. Data Cleanser
---
  - Create two folders namely Cleaned_Data and Uncleaned_Data in the same directory where the Data Cleanser package is located.
  - Insert your dataset into the Uncleaned_Data folder.
  - Go to Data_Cleanser/src/config/config.json and fill out all the required paths.
  - Download and start Solr Cloud using ```solr start -e cloud``` inside the bin folder of solr directory.
  - Select all the default settings and provide a name for the collection.
  - You can check your collection at ``` http://localhost:8983/solr/#/ ```
  - Provide the collection name and the zookeeper host in Data_Cleanser/src/config/config.json (go to cloud->ZK Status on ``` http://localhost:8983/solr/#/ ```)
  - With all the above steps done, you can start using it.
  - The following are the 3 ways of using it,
	- Clean the data
		``` python main.py -c "path to your config.json file" -cf True -if False ``` 
	- Index the Data into Solr (in case you already have cleaned file in the Cleaned_Data folder)
      ``` python main.py -c "path to your config.json file" -cf False -if True ``` 
    - First clean and then index the data 
      ``` python main.py -c "path to your config.json file" ```	
  
---
### 2. SpringBoot Application 
---
- Open the SpringBoot Application in any IDE which supports java.
- Provide the name of the collection in Document.java class of model package.
- Now open TestConfig.java class and run it. It will run your application.
- It will start running on `http://localhost:8080`. 
- Use Postman or any browser to see the results.
	-  For instance, `http://localhost:8080//getAll` will fetch you all the records.
- You can use other APIs as per your requirement as well which are present in (controller->controller.java)

---
### 3. Angular FrontEnd  
---
 - Open Solr UI in Visual Studio Code or any other IDE as per your choice.
 - Open terminal and run `ng serve` 
 - The application starts running on  `localhost:4200` 
 - The details of a particular record can be seen by clicking on a row of AG-Grid.
 - Also, filters can be applied which makes the application more convenient to use. 
 



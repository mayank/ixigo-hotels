# ixigo-hotels
---------------------

Matches similar hotels using GPS coordinates and levhensteins' distance in name of hotels.
Sample data collected from Ibibo, Make my Trip, Expedia etc.


### Assumptions 
* Data will be coming in JSON objects from different service providers
* Collected Data from Make My Trip, Ibibo and Expedia websites
* Checking if there are any common hotels exists in all the data.

### Architecture <Class Diagram>

![Class Diagram](/snap.png "Class Diagram")

### How it works 

1. There is a mapping file (mapping.json) that contains API url and recrusive mapping of specific fields that are required by Hotel Object.
2. At first, **DataSourceReaderService** reads the data from mapping file and generates an object of **APIMap** which contains mapping and api urls.
3. From **APIMap** we pass the URL to **APIDataService** which returns the API results containing hotel data.
4. After this, **MappingFormatterService** formats the data using a recursive parser into the desired format from the mapping given in **APIMap**.
5. This formatted maps are passed to **HotelGeneratorService** to create a hotel object.
6. **Hotel** and **Address** object’s equal method is overriden as to ensure if the address are equal by a margin then the two addresses must be same. And *levensthein’s distance* is applied to check if any typo errors or single word changes are there.


### How to Run

1. Goto `dist` folder
2. run 
```
java -jar ixigo-runnable.jar
```

> Output
```
Crystal Sarovar Premiere, Make My Trip matches with Crystal Sarovar Premiere, Go Ibibo
Clark's Shiraz, Make My Trip matches with Clark's Shiraz, Go Ibibo
Hotel Mansingh Palace, Make My Trip matches with Hotel Mansingh Palace, Go Ibibo
Hotel Mansingh Palace, Make My Trip matches with Hotel Mansingh Palace, Expedia
....

```

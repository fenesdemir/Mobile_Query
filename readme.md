
# Problem definition
**Purpose**

In this project we are expected to develop an application on the android platform using cloud computing and google map api. An application in which different queries can be made on the android platform by using the taxi trajectory data.

# Trajectory Data
The trajectory data includes the positions of objects in motion and other motion-related information.
The New York City Taxi and Limousine Commission (TLC) deals with yellow taxi, green taxi, rental cars. TLC regularly records information on each completed taxi ride. As part of this project, yellow taxi data published in December 2020 will be used.

File address: https://www1.nyc.gov/site/tlc/about/tlc-trip-record-data.page

Filename 1: yellow tripdata 2020-12.csv

Filename 2: taxi+ zone lookup.csv

# Preliminary Information
- The data should be stored in the cloud environment. The stored data can be filtered and limited to a minimum of 1000 records.
- Different locations (at least 30) must be included in the restricted data.
- There should be records from different days (at least 15 days) in the data.
- The data should be arranged in such a way as to observe the following queries.
- In the application, you are expected to perform one query from each query type specified in the next title by pulling data from the cloud environment.
- Google map api should be used in queries related to the map.

# Requirements

## Queries

### Type 1

**A solution must be found for one of the questions below.**

- List the 5 days with the highest number of passengers and the total number of passengers.
- Find the most traveled day and travel length below a certain distance (distance should be selectable).
- List the days and distances of the 5 longest-distance trips.

### Type 2
**A solution must be found for one of the questions below.**

- What is the number of vehicles moving from a certain location between two dates? (dates and location can be selected)
- According to the average wages paid per daily trip; List the average daily wages between the two minimum paid dates.
- Which of the 5 journeys with the least distance traveled between two dates? (dates can be selected)

### Type 3
**A solution must be found for one of the questions below.**

- Draw the route on the map of the longest trip on a given day (Day can be selected). The starting and final locations should be accepted as the center of the location and a road should be found according to the distance.
- Draw the path of 5 random vehicles moving from the same location on a given day (Day and location should be selectable). The starting and final locations should be accepted as the center of the location and a road should be found according to the distance.
- Draw the shortest and longest distance route from trips with at least 3 passengers. The starting and final locations should be accepted as the center of the location and a road should be found according to the distance.

# Ease of Use 

- When you run the program, you will see 3 options: Query 1, Query 2 and Query 3.

- After clicking on Query 1, you don't need to do anything else, it will list the 5 longest-distance trips directly to you.

- If you choose Query 2, you must enter 2 dates. The field on the left is the passenger's boarding time and the disembarkation date. When choosing these dates, you should choose them taking into account that when the dates are converted to milliseconds, they will show 00:00:00 on that day.

- If you choose Query 3 because I couldn't do it, you will see a blank page.

